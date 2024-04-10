package uz.pdp.ecommerce.repo;

import lombok.SneakyThrows;
import uz.pdp.ecommerce.config.ConnectionPoolManager;
import uz.pdp.ecommerce.entity.Basket;
import uz.pdp.ecommerce.entity.BasketProduct;
import uz.pdp.ecommerce.temp.TemporaryVariables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BasketProductRepo {
    @SneakyThrows
    public static Basket setAmount(int i, UUID uuid, Basket basket) {
        for (BasketProduct product : basket.getProducts()) {
            if (product.getProductId().equals(uuid)) {
                product.setAmount(product.getAmount() + i);
            }
        }
        basket.getProducts().removeIf(basketProduct -> basketProduct.getAmount() == 0 || basketProduct.getAmount() < 0);
        return basket;
    }
}
