package uz.pdp.ecommerce.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;
import uz.pdp.ecommerce.entity.Basket;

import java.util.UUID;

@WebServlet(name = "RemoveProduct", value = "/product/remove")
public class RemoveProductBasketServlet extends HttpServlet {
    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        Object object = session.getAttribute("basket");
        Basket basket = (Basket) object;
        UUID productId = UUID.fromString(req.getParameter("id"));
        basket.getProducts().removeIf(basketProduct -> basketProduct.getProductId().equals(productId));
        resp.sendRedirect("/ecommerce/user/basket.jsp");
    }
}
