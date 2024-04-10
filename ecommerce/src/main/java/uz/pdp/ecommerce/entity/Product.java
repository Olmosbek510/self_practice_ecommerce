package uz.pdp.ecommerce.entity;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;
import uz.pdp.ecommerce.repo.CategoryRepo;

import java.io.InputStream;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
@MultipartConfig
public class Product {
    private UUID id;
    private String name;
    private int price;
    private UUID categoryId;
    private byte[] photo;

    @SneakyThrows
    public Product(HttpServletRequest req) {
        Part photo = req.getPart("photo");
        InputStream inputStream = photo.getInputStream();
        this.photo = inputStream.readAllBytes();
        name = req.getParameter("name");
        price = Integer.parseInt(req.getParameter("price"));
        categoryId = UUID.fromString(req.getParameter("categoryId"));
    }

    public String getCategory() {
        Category byId = CategoryRepo.findById(categoryId);
        return byId.getName();
    }
}
