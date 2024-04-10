package uz.pdp.ecommerce.repo;

import jdk.jshell.spi.SPIResolutionException;
import lombok.SneakyThrows;
import uz.pdp.ecommerce.config.ConnectionPoolManager;
import uz.pdp.ecommerce.entity.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CategoryRepo {

    public static List<Category> findAll() throws SQLException {
        List<Category> categories = new ArrayList<>();
        String query = "select * from category order by name";
        try (Connection connection = ConnectionPoolManager.DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                categories.add(
                        Category.builder()
                                .id(UUID.fromString(resultSet.getString("id")))
                                .name(resultSet.getString("name"))
                                .build());
            }
            return categories;
        }
    }

    @SneakyThrows
    public static void save(Category category) {
        try (Connection connection = ConnectionPoolManager.DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into category(name) values(?)")
        ) {
            preparedStatement.setString(1, category.getName());
            preparedStatement.executeUpdate();
        }
    }

    @SneakyThrows
    public static void deleteByID(UUID uuid) {
        try (Connection connection = ConnectionPoolManager.DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "delete from category where id = ?")
        ) {
            preparedStatement.setObject(1, uuid);
            preparedStatement.executeUpdate();
        }
    }

    @SneakyThrows
    public static Category findById(UUID uuid) {
        try (Connection connection = ConnectionPoolManager.DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from category where id = ?")
        ) {
            preparedStatement.setObject(1,uuid);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return Category.builder()
                    .name(resultSet.getString("name"))
                    .id(UUID.fromString(resultSet.getString("id")))
                    .build();
        }
    }

    @SneakyThrows
    public static void updateCategory(Category category) {
        try (Connection connection = ConnectionPoolManager.DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "update category set name = ? where id = ?")
        ) {
            preparedStatement.setString(1,category.getName());
            preparedStatement.setObject(2,category.getId());
            preparedStatement.executeUpdate();
        }
    }
}
