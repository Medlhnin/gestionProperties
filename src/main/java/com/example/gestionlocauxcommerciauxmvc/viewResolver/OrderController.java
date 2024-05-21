package com.example.gestionlocauxcommerciauxmvc.viewResolver;

import com.example.gestionlocauxcommerciauxmvc.Dto.OrderDto;
import com.example.gestionlocauxcommerciauxmvc.Order.UserOrder;
import com.example.gestionlocauxcommerciauxmvc.Property.Property;
import com.example.gestionlocauxcommerciauxmvc.Property.PropertyService;
import com.example.gestionlocauxcommerciauxmvc.Security.MyUserDetails;
import com.example.gestionlocauxcommerciauxmvc.User.UserGl;
import com.example.gestionlocauxcommerciauxmvc.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.gestionlocauxcommerciauxmvc.Order.OrderService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public String orders(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            Object principal = auth.getPrincipal();
            if (principal instanceof MyUserDetails) {
                MyUserDetails userDetails = (MyUserDetails) principal;
                model.addAttribute("username", userDetails.getUsername());
                model.addAttribute("email", userDetails.getEmail());
                model.addAttribute("orders", orderService.getOrdersByUserId(userDetails.getId()));
            } else {
                throw new UsernameNotFoundException("User details not found");
            }
        }
        return "orders";
    }

}
