package uz.pdp.ecommerce.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import uz.pdp.ecommerce.repo.ProductRepo;

import java.util.UUID;

@WebServlet(name = "deleteProduct", value = "/product/delete")
public class DeleteProductServlet extends HttpServlet {
    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        UUID uuid = UUID.fromString(req.getParameter("id"));
        ProductRepo.deleteById(uuid);
        resp.sendRedirect("/ecommerce/admin/product.jsp");
    }
}
