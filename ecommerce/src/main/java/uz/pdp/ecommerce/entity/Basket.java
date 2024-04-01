package uz.pdp.ecommerce.entity;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Basket {
    private UUID id;
    private UUID userId;
}
