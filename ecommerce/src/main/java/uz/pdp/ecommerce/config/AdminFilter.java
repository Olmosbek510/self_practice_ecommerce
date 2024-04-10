package uz.pdp.ecommerce.config;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import uz.pdp.ecommerce.entity.WebUser;
import uz.pdp.ecommerce.entity.enums.Role;
import uz.pdp.ecommerce.repo.UserRepo;

import java.io.IOException;
import java.util.UUID;

@WebFilter(urlPatterns = "/admin/*")
public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        WebUser currentUser;
        Object o = session.getAttribute("currentUser");
        if(o==null){
            response.sendRedirect("/ecommerce/404.jsp");
        }else{
            currentUser = (WebUser)o;
            if(currentUser.getRole().equals(Role.ADMIN)){
                filterChain.doFilter(servletRequest,servletResponse);
            }else {
               response.sendRedirect("/404.jsp");
            }
        }
    }
}
