package uz.pdp.ecommerce.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.ecommerce.repo.CategoryRepo;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "DeleteCategory",value = "/category/delete")
public class DeleteCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("id"));
        CategoryRepo.deleteByID(uuid);
        resp.sendRedirect("/ecommerce/admin/category.jsp");
    }
}
