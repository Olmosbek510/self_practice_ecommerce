package uz.pdp.ecommerce.records;

import uz.pdp.ecommerce.entity.enums.Status;

import java.sql.Timestamp;

public record OrderDetails(int orderId, String productName, int count, int price, int totalPrice, Timestamp dateTime, Status status) {
}
