package uz.pdp.ecommerce.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import uz.pdp.ecommerce.entity.BasketProduct;
import uz.pdp.ecommerce.repo.BasketProductRepo;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "RemoveProduct", value = "/product/remove")
public class RemoveProductBasketServlet extends HttpServlet {
    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        UUID productId = UUID.fromString(req.getParameter("id"));
        UUID uuid = UUID.fromString(req.getParameter("basketId"));
        BasketProduct basketProduct = BasketProduct.builder()
                .basketId(uuid)
                .productId(productId).build();
        BasketProductRepo.delete(basketProduct);
        resp.sendRedirect("/ecommerce/user/basket.jsp");
    }
}
