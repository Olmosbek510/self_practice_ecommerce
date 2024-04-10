package uz.pdp.ecommerce.entity;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class OrderProduct {
    private int orderId;
    private UUID productId;
    private int amount;
}
