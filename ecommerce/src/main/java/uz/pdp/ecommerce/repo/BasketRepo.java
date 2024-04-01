package uz.pdp.ecommerce.repo;

import lombok.SneakyThrows;
import uz.pdp.ecommerce.config.ConnectionPoolManager;
import uz.pdp.ecommerce.entity.Basket;
import uz.pdp.ecommerce.temp.TemporaryVariables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

public class BasketRepo {

    @SneakyThrows
    public static Basket create() {
        try (Connection connection = ConnectionPoolManager.DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into basket default values returning id,user_id")
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return Basket.builder()
                    .id(UUID.fromString(resultSet.getString("id")))
                    .build();
        }
    }

    @SneakyThrows
    public static int getTotalPrice() {
        try (Connection connection = ConnectionPoolManager.DATA_SOURCE.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select sum(basketproduct.amount*product.price) as total_price from basketproduct join public.basket b on basketproduct.basket_id = b.id join product on basketproduct.product_id = product.id and b.id = ?;")
        ) {
            if(TemporaryVariables.CURRENT_BASKET!=null)
                preparedStatement.setObject(1,TemporaryVariables.CURRENT_BASKET.getId());
            else
                preparedStatement.setObject(1,UUID.randomUUID());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt("total_price");
        }
    }
}
