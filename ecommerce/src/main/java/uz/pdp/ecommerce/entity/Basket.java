package uz.pdp.ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.ecommerce.repo.ProductRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Basket {
    private List<BasketProduct> products = new ArrayList<>();

    public int getTotalPrice() {
        return products.stream().mapToInt(item -> item.getAmount() * ProductRepo.findById(item.getProductId()).getPrice()).sum();
    }
}
