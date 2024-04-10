package uz.pdp.ecommerce.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import uz.pdp.ecommerce.entity.Basket;
import uz.pdp.ecommerce.entity.BasketProduct;
import uz.pdp.ecommerce.entity.Product;
import uz.pdp.ecommerce.repo.ProductRepo;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "AddToBasket", value = "/basket/add")
public class AddProductToBasket extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UUID id = UUID.fromString(req.getParameter("productId"));
        Product product = ProductRepo.findById(id);
        HttpSession session = req.getSession();
        Object object = session.getAttribute("basket");
        Basket basket;
        if(object==null){
            basket = new Basket();
        }else{
            basket = (Basket) object;
        }
        basket.getProducts().add(new BasketProduct(id,1));
        session.setAttribute("basket",basket);
        resp.sendRedirect("/ecommerce/index.jsp?categoryId="+product.getCategoryId());
    }
}
