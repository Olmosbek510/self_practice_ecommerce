package uz.pdp.ecommerce.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import uz.pdp.ecommerce.entity.Basket;
import uz.pdp.ecommerce.repo.BasketProductRepo;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name="IncreaseAmount", value = "/product/increase/amount")
public class IncAmountBasketPrdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UUID uuid = UUID.fromString(req.getParameter("id"));
        HttpSession session = req.getSession();
        Object o = session.getAttribute("basket");
        if(o!=null){
            Basket basket = (Basket) o;
            Basket basket1 = BasketProductRepo.setAmount(1,uuid, basket);
            req.setAttribute("basket",basket1);
        }
        resp.sendRedirect("/ecommerce/user/basket.jsp");
    }
}
