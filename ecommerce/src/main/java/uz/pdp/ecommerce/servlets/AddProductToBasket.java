package uz.pdp.ecommerce.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.ecommerce.entity.BasketProduct;
import uz.pdp.ecommerce.repo.BasketProductRepo;
import uz.pdp.ecommerce.repo.BasketRepo;
import uz.pdp.ecommerce.temp.TemporaryVariables;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "AddToBasket", value = "/product/addToBasket")
public class AddProductToBasket extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("hello");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("id"));
        if (TemporaryVariables.CURRENT_BASKET == null) {
            TemporaryVariables.CURRENT_BASKET = BasketRepo.create();
        }
        BasketProduct basketProduct = BasketProduct.builder()
                .basketId(TemporaryVariables.CURRENT_BASKET.getId())
                .productId(uuid)
                .amount(1)
                .build();
        BasketProductRepo.save(basketProduct);
        resp.sendRedirect("/ecommerce/index.jsp");
    }
}
