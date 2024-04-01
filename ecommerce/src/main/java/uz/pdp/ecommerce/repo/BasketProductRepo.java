package uz.pdp.ecommerce.repo;

import lombok.SneakyThrows;
import uz.pdp.ecommerce.config.ConnectionPoolManager;
import uz.pdp.ecommerce.entity.BasketProduct;
import uz.pdp.ecommerce.temp.TemporaryVariables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BasketProductRepo {

    @SneakyThrows
    public static void save(BasketProduct basketProduct) {
        try (Connection connection = ConnectionPoolManager.DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into basketproduct(basket_id,product_id,amount) values (?,?,?)")
        ) {
            preparedStatement.setObject(1, basketProduct.getBasketId());
            preparedStatement.setObject(2, basketProduct.getProductId());
            preparedStatement.setInt(3, basketProduct.getAmount());
            preparedStatement.executeUpdate();
        }
    }

    @SneakyThrows
    public static List<BasketProduct> findAll() {
        try (Connection connection = ConnectionPoolManager.DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from basketproduct order by product_id")
        ) {
            List<BasketProduct> basketProducts = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                basketProducts.add(
                        BasketProduct.builder()
                                .basketId(UUID.fromString(resultSet.getString("basket_id")))
                                .productId(UUID.fromString(resultSet.getString("product_id")))
                                .amount(resultSet.getInt("amount"))
                                .build()
                );
            }
            return basketProducts;
        }
    }

    @SneakyThrows
    public static boolean inBasket(UUID productId) {
        for (BasketProduct basketProduct : findAll()) {
            if (TemporaryVariables.CURRENT_BASKET != null && basketProduct.getProductId().equals(productId) && basketProduct.getBasketId().equals(TemporaryVariables.CURRENT_BASKET.getId()))
                return true;
        }
        return false;
    }

    @SneakyThrows
    public static void setAmount(int i, UUID uuid) {
        try (Connection connection = ConnectionPoolManager.DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("update basketproduct set amount = amount + ? where product_id = ? and amount >= 1 and basket_id = ?")
        ) {
            preparedStatement.setInt(1, i);
            preparedStatement.setObject(2, uuid);
            preparedStatement.setObject(3, TemporaryVariables.CURRENT_BASKET.getId());
            preparedStatement.executeUpdate();
        }
        try (Connection connection = ConnectionPoolManager.DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("delete from basketproduct where amount = 0")
        ) {
            preparedStatement.executeUpdate();
        }
    }

    @SneakyThrows
    public static List<BasketProduct> findByBasketId(UUID id) {
        try (Connection connection = ConnectionPoolManager.DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from basketproduct where basket_id = ? order by product_id")
        ) {
            List<BasketProduct> basketProducts = new ArrayList<>();
            preparedStatement.setObject(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                basketProducts.add(
                        BasketProduct.builder()
                                .basketId(UUID.fromString(resultSet.getString("basket_id")))
                                .productId(UUID.fromString(resultSet.getString("product_id")))
                                .amount(resultSet.getInt("amount"))
                                .build()
                );
            }
            return basketProducts;
        }
    }

    @SneakyThrows
    public static void delete(BasketProduct basketProduct) {
        try (Connection connection = ConnectionPoolManager.DATA_SOURCE.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from basketproduct where product_id = ? and basket_id = ?")
        ) {
            preparedStatement.setObject(1,basketProduct.getProductId());
            preparedStatement.setObject(2,basketProduct.getBasketId());
            preparedStatement.executeUpdate();
        }
    }
}
