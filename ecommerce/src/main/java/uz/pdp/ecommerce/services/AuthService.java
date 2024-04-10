package uz.pdp.ecommerce.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import uz.pdp.ecommerce.entity.Basket;
import uz.pdp.ecommerce.entity.WebUser;
import uz.pdp.ecommerce.repo.UserRepo;

import java.util.Optional;

public class AuthService {

    public static boolean check(String username, String password) {
        Optional<WebUser> byUsername = UserRepo.findByUsername(username);
        if(byUsername.isPresent()){
            WebUser user = byUsername.get();
            return user.getPassword().equals(password);
        }
        return false;
    }

    public static void authenticate(HttpServletRequest req, String username, HttpServletResponse resp) {
        Optional<WebUser> user = UserRepo.findByUsername(username);
        HttpSession session = req.getSession();
        user.ifPresent(webUser -> session.setAttribute("currentUser", webUser));
        Object o = session.getAttribute("basket");
        Basket basket;
        if(o!=null){
            basket = (Basket)o;
        }else{
            basket = new Basket();
        }
        if(!basket.getProducts().isEmpty()){
            user.ifPresent(webUser -> UserService.placeOrder(basket, webUser, session));
        }
    }
}
