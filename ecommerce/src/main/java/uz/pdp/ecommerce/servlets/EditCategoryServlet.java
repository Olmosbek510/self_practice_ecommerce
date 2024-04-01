package uz.pdp.ecommerce.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.ecommerce.entity.Category;
import uz.pdp.ecommerce.repo.CategoryRepo;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name ="categoryEdit",value = "/category/edit")
public class EditCategoryServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        UUID uuid = UUID.fromString(req.getParameter("id"));
        Category category = Category.builder()
                .id(uuid)
                .name(name)
                .build();
        CategoryRepo.updateCategory(category);
        resp.sendRedirect("/ecommerce/admin/category.jsp");
    }
}
