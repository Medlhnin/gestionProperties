package com.example.gestionlocauxcommerciauxmvc.viewResolver;

import com.example.gestionlocauxcommerciauxmvc.Dto.UserDto.newProperty;
import com.example.gestionlocauxcommerciauxmvc.Property.Image.ImageData;
import com.example.gestionlocauxcommerciauxmvc.Property.Image.StorageService;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class newPropertyController {

    private final PropertyService propertyService;
    private final UserService userService;
    private final StorageService storageService;
    @Autowired
    public newPropertyController(PropertyService propertyService, UserService userService, StorageService storageService){
        this.propertyService = propertyService;
        this.userService = userService;
        this.storageService = storageService;
    }
    @GetMapping("/newProperty")
    public String newProperty(Model model){
        model.addAttribute("newProperty", new newProperty());
        return "newProperty";
    }

    @PostMapping("/newProperty")
    public ModelAndView newPropertySubmit(@ModelAttribute("newProperty") newProperty newProperty) throws IOException {
        Property property = new Property();
        property.setAddress(newProperty.getAddress());
        property.setCity(newProperty.getCity());
        property.setCategory(newProperty.getCategory());
        property.setDescription(newProperty.getDescription());
        property.setName(newProperty.getName());
        /* if (!newProperty.getFile().isEmpty()) {
            String uploadImage = storageService.uploadImage(newProperty.getFile());
            property.setImageData(storageService.downloadImage(uploadImage));
        } */
        property.setPrice(newProperty.getPrice());
        property.setZipCode(newProperty.getZipCode());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated()) {
            Object principal = auth.getPrincipal();
            if (principal instanceof MyUserDetails) {
                MyUserDetails userDetails = (MyUserDetails) principal;
                UserGl owner = userService.getUserGl(userDetails.getUsername());
                property.setOwner(owner);
            } else {
                throw new UsernameNotFoundException("User details not found");
            }
        }
        propertyService.saveProperty(property);
        ModelAndView modelAndView = new ModelAndView("profile");
        modelAndView.addObject("message", "property added successfully!");
        return modelAndView;
    }
}
