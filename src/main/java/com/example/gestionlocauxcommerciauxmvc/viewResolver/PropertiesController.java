package com.example.gestionlocauxcommerciauxmvc.viewResolver;

import com.example.gestionlocauxcommerciauxmvc.Property.PropertyService;
import com.example.gestionlocauxcommerciauxmvc.Security.MyUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PropertiesController {

    private final PropertyService propertyService;
    public PropertiesController(PropertyService propertyService){
        this.propertyService = propertyService;
    }
    @GetMapping("/properties")
    public String properties(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            Object principal = auth.getPrincipal();
            if (principal instanceof MyUserDetails) {
                MyUserDetails userDetails = (MyUserDetails) principal;
                model.addAttribute("username", userDetails.getUsername());
                model.addAttribute("properties", propertyService.getPropertiesByOwner(userDetails.getUsername()));
            } else {
                throw new UsernameNotFoundException("User details not found");
            }
        }
        return "properties";
    }
}
