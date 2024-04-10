package uz.pdp.ecommerce.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.ecommerce.entity.WebUser;
import uz.pdp.ecommerce.entity.enums.Role;
import uz.pdp.ecommerce.repo.UserRepo;
import java.io.IOException;

@WebServlet(name = "UserRegister", value = "/user/register")
public class UserRegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
         WebUser user = WebUser.builder()
                .firstname(firstname)
                .lastname(lastname)
                .username(username)
                .password(password)
                 .role(Role.USER)
                .build();
        UserRepo.save(user);
        resp.sendRedirect("/ecommerce/index.jsp");
    }
}
