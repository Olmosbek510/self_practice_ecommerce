package uz.pdp.ecommerce.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.ecommerce.entity.WebUser;
import uz.pdp.ecommerce.entity.enums.Role;
import uz.pdp.ecommerce.repo.UserRepo;
import uz.pdp.ecommerce.services.AuthService;
import uz.pdp.ecommerce.services.UserService;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@WebServlet(name = "userSignIn", value = "/user/signIn")
public class UserSignInServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String rememberMe = req.getParameter("rememberMe");
        boolean check = AuthService.check(username, password);
        if (check) {
            AuthService.authenticate(req, username, resp);
            Optional<WebUser> byUsername = UserRepo.findByUsername(username);
            if (byUsername.isPresent()) {
                WebUser webUser = byUsername.get();
                if (Objects.equals(rememberMe, "on")) {
                    Cookie cookie = new Cookie("userId",webUser.getId().toString());
                    System.out.println(webUser.getId());
                    cookie.setSecure(false);
                    cookie.setPath("/");
                    cookie.setMaxAge(60*60);
                    resp.addCookie(cookie);
                }
                if (webUser.getRole().equals(Role.ADMIN)) {
                    resp.sendRedirect("/ecommerce/admin/admin.jsp");
                } else {
                    resp.sendRedirect("/ecommerce/index.jsp");
                }
            }
        } else {
            resp.sendRedirect("/ecommerce/user/signIn.jsp");
        }
    }
}
