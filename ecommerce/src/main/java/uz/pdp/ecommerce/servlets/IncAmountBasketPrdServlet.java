package uz.pdp.ecommerce.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.ecommerce.repo.BasketProductRepo;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name="IncreaseAmount", value = "/product/increase/amount")
public class IncAmountBasketPrdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UUID uuid = UUID.fromString(req.getParameter("id"));
        BasketProductRepo.setAmount(1,uuid);
        resp.sendRedirect("/ecommerce/user/basket.jsp");
    }
}
