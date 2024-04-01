package uz.pdp.ecommerce.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.ecommerce.entity.Product;
import uz.pdp.ecommerce.repo.CategoryRepo;
import uz.pdp.ecommerce.repo.ProductRepo;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "AddProduct", value = "/product/add")
public class AddProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = new Product(req);
        ProductRepo.save(product);
        resp.sendRedirect("/ecommerce/admin/product.jsp");
    }
}
