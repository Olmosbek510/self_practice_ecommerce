package uz.pdp.ecommerce.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.ecommerce.entity.Category;
import uz.pdp.ecommerce.repo.CategoryRepo;
import java.io.IOException;

@WebServlet(name = "addCategory", value = "/category/add")
public class AddCategoryServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Category category = Category.
                builder()
                .name(name)
                .build();
        CategoryRepo.save(category);
        resp.sendRedirect("/ecommerce/admin/category.jsp");
    }
}