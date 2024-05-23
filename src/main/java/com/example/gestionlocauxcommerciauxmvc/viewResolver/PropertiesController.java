package com.example.gestionlocauxcommerciauxmvc.viewResolver;

import com.example.gestionlocauxcommerciauxmvc.Property.PropertyService;
import com.example.gestionlocauxcommerciauxmvc.Security.MyUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/properties")
public class PropertiesController {

    private final PropertyService propertyService;
    public PropertiesController(PropertyService propertyService){
        this.propertyService = propertyService;
    }
    @GetMapping
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

    @PostMapping("/delete/{id}")
    public String deleteProperty(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        propertyService.deleteProperty(id);
        redirectAttributes.addFlashAttribute("message", "Property deleted successfully.");
        return "redirect:/properties";
    }
}
