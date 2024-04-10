package uz.pdp.ecommerce.entity;

import lombok.Builder;
import lombok.Data;
import uz.pdp.ecommerce.entity.enums.Role;

import java.util.UUID;

@Data
@Builder
public class WebUser {
    private UUID id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private Role role;
}
