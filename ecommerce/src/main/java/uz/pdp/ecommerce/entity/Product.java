package uz.pdp.ecommerce.entity;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import uz.pdp.ecommerce.repo.CategoryRepo;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class Product {
    private UUID id;
    private String name;
    private int price;
    private UUID categoryId;

    public Product(HttpServletRequest req) {
        name = req.getParameter("name");
        price = Integer.parseInt(req.getParameter("price"));
        categoryId = UUID.fromString(req.getParameter("categoryId"));
    }

    public String getCategory() {
        Category byId = CategoryRepo.findById(categoryId);
        return byId.getName();
    }
}
