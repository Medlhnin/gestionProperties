package com.example.gestionlocauxcommerciauxmvc.viewResolver;

import com.example.gestionlocauxcommerciauxmvc.Dto.OrderDto;
import com.example.gestionlocauxcommerciauxmvc.Order.OrderService;
import com.example.gestionlocauxcommerciauxmvc.Order.UserOrder;
import com.example.gestionlocauxcommerciauxmvc.Property.Property;
import com.example.gestionlocauxcommerciauxmvc.Property.PropertyService;
import com.example.gestionlocauxcommerciauxmvc.Security.MyUserDetails;
import com.example.gestionlocauxcommerciauxmvc.User.UserGl;
import com.example.gestionlocauxcommerciauxmvc.User.UserService;
import com.example.gestionlocauxcommerciauxmvc.exceptions.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class MarketplaceController {
    private final PropertyService propertyService;
    private final OrderService orderService;
    private final UserService userService;

    @Autowired
    public MarketplaceController(PropertyService propertyService,
                                 UserService userService,
                                 OrderService orderService) {
        this.propertyService = propertyService;
        this.userService = userService;
        this.orderService = orderService;

    }

    @GetMapping("/marketplace")
    public String marketplace(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            Object principal = auth.getPrincipal();
            if (principal instanceof MyUserDetails) {
                MyUserDetails userDetails = (MyUserDetails) principal;
                model.addAttribute("username", userDetails.getUsername());
                model.addAttribute("properties", propertyService.getProperties());
                model.addAttribute("order", new OrderDto());
                model.addAttribute("city","All");
            } else {
                throw new UsernameNotFoundException("User details not found");
            }
        }
        return "marketplace";

    }

    @PostMapping("/marketplace/order")
    public String order(@ModelAttribute("order") OrderDto orderDto) {
        UserOrder order = new UserOrder();
        order.setOrderDate(LocalDateTime.now());
        if(orderDto.getPropertyId() == null)
        {
            throw new AppException("No propertyId", HttpStatus.BAD_REQUEST);
        }
        Property property = propertyService.getPropertyById(orderDto.getPropertyId());
        order.setProperty(property);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            Object principal = auth.getPrincipal();
            if (principal instanceof MyUserDetails) {
                MyUserDetails userDetails = (MyUserDetails) principal;
                UserGl buyer = userService.getUserGl(userDetails.getUsername());
                order.setUser(buyer);

            } else {
                throw new UsernameNotFoundException("User details not found");
            }
        }
        orderService.saveOrder(order);
        return "redirect:/orders";
    }

    @GetMapping("/marketplace/city")
    public String propertiesByCiyty(@RequestParam("city") String city, Model model) {
        List<Property> properties;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            Object principal = auth.getPrincipal();
            if (principal instanceof MyUserDetails) {
                MyUserDetails userDetails = (MyUserDetails) principal;
                if ("All".equalsIgnoreCase(city)) {
                    properties = propertyService.getProperties();
                } else {
                    properties = propertyService.getPropertiesByCity(city);
                }
                model.addAttribute("username", userDetails.getUsername());
                model.addAttribute("properties", properties);
                model.addAttribute("order", new OrderDto());
            } else {
                throw new UsernameNotFoundException("User details not found");
            }
        }
        return "marketplace";

    }



}
