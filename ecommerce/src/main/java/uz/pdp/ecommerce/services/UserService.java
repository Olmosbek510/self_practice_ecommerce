package uz.pdp.ecommerce.services;

import jakarta.servlet.http.HttpSession;
import uz.pdp.ecommerce.entity.*;
import uz.pdp.ecommerce.repo.OrderProductRepo;
import uz.pdp.ecommerce.repo.OrderRepo;

import java.util.List;

public class UserService {

    public static void placeOrder(Basket basket, WebUser currentUser, HttpSession session) {
        List<BasketProduct> basketProducts = basket.getProducts();
        Order order = OrderRepo.create(currentUser.getId());
        for (BasketProduct basketProduct : basketProducts) {
            OrderProduct orderProduct = OrderProduct.builder()
                    .orderId(order.getId())
                    .amount(basketProduct.getAmount())
                    .productId(basketProduct.getProductId())
                    .build();
            OrderProductRepo.save(orderProduct);
            session.removeAttribute("basket");
        }
    }
}
