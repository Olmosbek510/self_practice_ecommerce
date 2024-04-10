package uz.pdp.ecommerce.repo;

import lombok.SneakyThrows;
import uz.pdp.ecommerce.config.ConnectionPoolManager;
import uz.pdp.ecommerce.entity.Order;
import uz.pdp.ecommerce.entity.WebUser;
import uz.pdp.ecommerce.entity.enums.Status;
import uz.pdp.ecommerce.records.OrderDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderRepo {
    @SneakyThrows
    public static Order create(UUID userId) {
        try (Connection connection = ConnectionPoolManager.DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into \"order\"(status,userid) values('PENDING',?) returning id,datetime,status;")
        ) {
            preparedStatement.setObject(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return Order.builder()
                    .dateTime(resultSet.getTimestamp("datetime"))
                    .id(resultSet.getInt("id"))
                    .status(Status.valueOf(resultSet.getString("status")))
                    .build();
        }
    }

    @SneakyThrows
    public static List<Order> findAll() {
        try (Connection connection = ConnectionPoolManager.DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from \"order\"")
        ) {
            List<Order> orders = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orders.add(
                        Order.builder()
                                .status(Status.valueOf(resultSet.getString("status")))
                                .id(resultSet.getInt("id"))
                                .dateTime(resultSet.getTimestamp("datetime"))
                                .build()
                );
            }
            return orders;
        }
    }

    @SneakyThrows
    public static List<OrderDetails> getOrderDetails(int orderId) {
        try (Connection connection = ConnectionPoolManager.DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("""
                     select order_id            as orderId,
                            p.name              as name,
                            orderproduct.amount as amount,
                            p.price             as price,
                            (amount * p.price)  as tot_price,
                            o.datetime          as time,
                            o.status            as status
                     from orderproduct
                              join public.product p on orderproduct.product_id = p.id
                              join public."order" o on o.id = orderproduct.order_id and order_id = ?
                              
                              """)
        ) {
            List<OrderDetails> orderDetails = new ArrayList<>();
            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orderDetails.add(
                        new OrderDetails(
                                resultSet.getInt("orderId"),
                                resultSet.getString("name"),
                                resultSet.getInt("amount"),
                                resultSet.getInt("price"),
                                resultSet.getInt("tot_price"),
                                resultSet.getTimestamp("time"),
                                Status.valueOf(resultSet.getString("status"))
                                )
                );
            }
            return orderDetails;
        }
    }

    public static List<Order> findByUserId(WebUser currentUser) {
        try (Connection connection = ConnectionPoolManager.DATA_SOURCE.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from \"order\" where userid = ?;")
        ) {
            preparedStatement.setObject(1,currentUser.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                orders.add(
                        Order.builder()
                                .userId(UUID.fromString(resultSet.getString("userid")))
                                .dateTime(resultSet.getTimestamp("datetime"))
                                .status(Status.valueOf(resultSet.getString("status")))
                                .id(resultSet.getInt("id"))
                                .build()
                );
            }
            return orders;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
