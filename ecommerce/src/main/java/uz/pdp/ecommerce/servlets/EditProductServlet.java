package uz.pdp.ecommerce.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.ecommerce.entity.Product;
import uz.pdp.ecommerce.repo.ProductRepo;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "editProduct", value = "/product/edit")
public class EditProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        UUID uuid = UUID.fromString(req.getParameter("id"));
        UUID cat_id = UUID.fromString(req.getParameter("categoryId"));
        int price = Integer.parseInt(req.getParameter("price"));
        Product product = Product.builder()
                .id(uuid)
                .name(name)
                .price(price)
                .categoryId(cat_id)
                .build();
        ProductRepo.updateProduct(product);
        resp.sendRedirect("/ecommerce/admin/product.jsp");
    }
}
