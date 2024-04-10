package uz.pdp.ecommerce.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(name = "Logout",value = "/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("currentUser");
        session.invalidate();
        for (Cookie cookie : req.getCookies()) {
            cookie.setMaxAge(0);
            cookie.setPath("/");
            cookie.setSecure(false);
            resp.addCookie(cookie);
        }
        resp.sendRedirect("/ecommerce/index.jsp");
    }
}
