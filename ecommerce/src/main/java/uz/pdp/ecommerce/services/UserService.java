package uz.pdp.ecommerce.services;

import uz.pdp.ecommerce.entity.BasketProduct;
import uz.pdp.ecommerce.entity.Order;
import uz.pdp.ecommerce.entity.OrderProduct;
import uz.pdp.ecommerce.repo.BasketProductRepo;
import uz.pdp.ecommerce.repo.OrderProductRepo;
import uz.pdp.ecommerce.repo.OrderRepo;
import uz.pdp.ecommerce.temp.TemporaryVariables;

import java.util.List;

public class UserService{

    public static void placeOrder() {
        List<BasketProduct> basketProducts = BasketProductRepo.findByBasketId(TemporaryVariables.CURRENT_BASKET.getId());
        if(basketProducts.isEmpty()){
            TemporaryVariables.CURRENT_BASKET = null;
            return;
        }
        Order order = OrderRepo.create();
        for (BasketProduct basketProduct : basketProducts) {
            if(basketProduct.getBasketId().equals(TemporaryVariables.CURRENT_BASKET.getId())){
                OrderProduct orderProduct = OrderProduct.builder()
                        .orderId(order.getId())
                        .amount(basketProduct.getAmount())
                        .productId(basketProduct.getProductId())
                        .build();
                OrderProductRepo.save(orderProduct);
            }
        }
        TemporaryVariables.CURRENT_BASKET = null;
    }
}
