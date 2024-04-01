package uz.pdp.ecommerce.repo;

import lombok.SneakyThrows;
import uz.pdp.ecommerce.config.ConnectionPoolManager;
import uz.pdp.ecommerce.entity.Category;
import uz.pdp.ecommerce.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductRepo {
    @SneakyThrows
    public static List<Product> findAll() {
        try (Connection connection = ConnectionPoolManager.DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from product order by name")
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                products.add(
                        Product.builder()
                                .id(UUID.fromString(resultSet.getString("id")))
                                .name(resultSet.getString("name"))
                                .price(resultSet.getInt("price"))
                                .categoryId(UUID.fromString(resultSet.getString("categoryId")))
                                .build()
                );
            }
            return products;
        }
    }

    @SneakyThrows
    public static void save(Product product) {
        try (Connection connection = ConnectionPoolManager.DATA_SOURCE.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into product(name,price,categoryid) values (?,?,?)")
        ) {
            preparedStatement.setString(1,product.getName());
            preparedStatement.setInt(2,product.getPrice());
            preparedStatement.setObject(3,product.getCategoryId());
            preparedStatement.executeUpdate();
        }
    }

    @SneakyThrows
    public static void deleteById(UUID uuid) {
        try (Connection connection = ConnectionPoolManager.DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("delete from product where id = ?")
        ) {
            preparedStatement.setObject(1,uuid);
            preparedStatement.executeUpdate();
        }
    }

    @SneakyThrows
    public static Product findById(UUID uuid) {
        return getProduct(uuid);
    }

    @SneakyThrows
    public static void updateProduct(Product product) {
        try (Connection connection = ConnectionPoolManager.DATA_SOURCE.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("update product set name = ?, price = ?, categoryid = ? where id = ?")
        ) {
            preparedStatement.setString(1,product.getName());
            preparedStatement.setInt(2,product.getPrice());
            preparedStatement.setObject(3,product.getCategoryId());
            preparedStatement.setObject(4,product.getId());
            preparedStatement.executeUpdate();
        }
    }
    public static Product getProduct(UUID productId) throws SQLException {
        try (Connection connection = ConnectionPoolManager.DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from product where id = ?")
        ) {
            preparedStatement.setObject(1,productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return Product.builder()
                    .id(UUID.fromString(resultSet.getString("id")))
                    .name(resultSet.getString("name"))
                    .price(resultSet.getInt("price"))
                    .categoryId(UUID.fromString(resultSet.getString("categoryid")))
                    .build();
        }
    }
    public static List<Product> findByCategory(UUID catId) {
        List<Product> products = new ArrayList<>();
        String query = "select * from product where categoryid = ? order by name";
        try (Connection connection = ConnectionPoolManager.DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setObject(1,catId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                products.add(
                        Product.builder()
                                .categoryId(UUID.fromString(resultSet.getString("categoryid")))
                                .price(resultSet.getInt("price"))
                                .name(resultSet.getString("name"))
                                .id(UUID.fromString(resultSet.getString("id")))
                                .build()
                );
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
