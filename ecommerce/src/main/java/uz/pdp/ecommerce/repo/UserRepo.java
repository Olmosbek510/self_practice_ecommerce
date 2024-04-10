package uz.pdp.ecommerce.repo;

import lombok.SneakyThrows;
import uz.pdp.ecommerce.config.ConnectionPoolManager;
import uz.pdp.ecommerce.entity.WebUser;
import uz.pdp.ecommerce.entity.enums.Role;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.UUID;

public class UserRepo {
    @SneakyThrows
    public static void save(WebUser user) {
        if(isExists(user))
            return;
        try (Connection connection = ConnectionPoolManager.DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into users(firstname, lastname, username, password) values(?,?,?,?)")
        ) {
            preparedStatement.setString(1,user.getFirstname());
            preparedStatement.setString(2,user.getLastname());
            preparedStatement.setString(3,user.getUsername());
            preparedStatement.setString(4,user.getPassword());
            preparedStatement.executeUpdate();
        }
    }

    @SneakyThrows
    public static boolean isExists(WebUser user) {
        try (Connection connection = ConnectionPoolManager.DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from users where username = ?")
        ) {
            preparedStatement.setString(1,user.getUsername());
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }
    }

    @SneakyThrows
    public static Optional<WebUser> findByUsername(String username) {
        try (Connection connection = ConnectionPoolManager.DATA_SOURCE.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where username = ?")
        ) {
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return Optional.of(WebUser.builder()
                        .id(UUID.fromString(resultSet.getString("id")))
                        .firstname(resultSet.getString("firstname"))
                        .lastname(resultSet.getString("lastname"))
                        .username(resultSet.getString("username"))
                        .password(resultSet.getString("password"))
                        .role(Role.valueOf(resultSet.getString("role")))
                        .build());
            }else return Optional.empty();
        }
    }

    @SneakyThrows
    public static Optional<WebUser> findById(UUID userId) {
        try (Connection connection = ConnectionPoolManager.DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from users where id = ?")
        ) {
            preparedStatement.setObject(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return Optional.of(WebUser.builder()
                        .id(UUID.fromString(resultSet.getString("id")))
                        .firstname(resultSet.getString("firstname"))
                        .lastname(resultSet.getString("lastname"))
                        .username(resultSet.getString("username"))
                        .password(resultSet.getString("password"))
                        .role(Role.valueOf(resultSet.getString("role")))
                        .build());
            }

        }
        return Optional.empty();
    }
}
