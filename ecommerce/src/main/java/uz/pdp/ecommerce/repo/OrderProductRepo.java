package uz.pdp.ecommerce.repo;

import lombok.SneakyThrows;
import uz.pdp.ecommerce.config.ConnectionPoolManager;
import uz.pdp.ecommerce.entity.OrderProduct;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class OrderProductRepo {
    @SneakyThrows
    public static void save(OrderProduct orderProduct) {
        try (Connection connection = ConnectionPoolManager.DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into orderproduct(order_id,product_id,amount) values(?,?,?)")
            ) {
            preparedStatement.setObject(1,orderProduct.getOrderId());
            preparedStatement.setObject(2,orderProduct.getProductId());
            preparedStatement.setInt(3,orderProduct.getAmount());
            preparedStatement.executeUpdate();
        }
    }
}
