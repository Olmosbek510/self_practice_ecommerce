package uz.pdp.ecommerce.entity;

import lombok.Builder;
import lombok.Data;
import uz.pdp.ecommerce.entity.enums.Status;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Builder
public class Order {
    private int id;
    private Timestamp dateTime;
    private UUID userId;
    private Status status;
}
