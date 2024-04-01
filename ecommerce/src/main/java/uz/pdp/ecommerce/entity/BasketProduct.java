package uz.pdp.ecommerce.entity;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class BasketProduct {
    private UUID productId;
    private UUID basketId;
    private int amount;
}
