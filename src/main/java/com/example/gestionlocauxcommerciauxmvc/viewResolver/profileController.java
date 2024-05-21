package com.example.gestionlocauxcommerciauxmvc.viewResolver;

import com.example.gestionlocauxcommerciauxmvc.Dto.UserDto.LoginRequest;
import com.example.gestionlocauxcommerciauxmvc.Property.PropertyService;
import com.example.gestionlocauxcommerciauxmvc.Security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class profileController {
    private final PropertyService propertyService;
    @Autowired
    public profileController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }


    @GetMapping("/profile")
    public String profile(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            Object principal = auth.getPrincipal();
            if (principal instanceof MyUserDetails) {
                MyUserDetails userDetails = (MyUserDetails) principal;
                model.addAttribute("username", userDetails.getUsername());
                model.addAttribute("authorities", userDetails.getAuthorities());
                model.addAttribute("email", userDetails.getEmail());
                model.addAttribute("userProperties", userDetails.getProperties());
                model.addAttribute("properties", propertyService.getProperties());
            } else {
                throw new UsernameNotFoundException("User details not found");
            }
        }
        return "profile";
    }
}
