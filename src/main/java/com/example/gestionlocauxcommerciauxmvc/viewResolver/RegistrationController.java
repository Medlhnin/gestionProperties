package com.example.gestionlocauxcommerciauxmvc.viewResolver;


import com.example.gestionlocauxcommerciauxmvc.Dto.UserDto.GottenUser;
import com.example.gestionlocauxcommerciauxmvc.Subscription.Subscription;
import com.example.gestionlocauxcommerciauxmvc.Subscription.SubscriptionService;
import com.example.gestionlocauxcommerciauxmvc.Subscription.TypeSubscription;
import com.example.gestionlocauxcommerciauxmvc.User.UserGl;
import com.example.gestionlocauxcommerciauxmvc.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Controller
public class RegistrationController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final SubscriptionService subscriptionService;
    @Autowired
    public RegistrationController(UserService userService
            , PasswordEncoder passwordEncoder, SubscriptionService subscriptionService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.subscriptionService = subscriptionService;
    }
    @GetMapping("/register")
    String register(Model model) {
        model.addAttribute("user", new GottenUser());
        return "register";
    }

    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute("user") GottenUser newUser) {
        UserGl userGl = new UserGl();
        String hashedPassword = passwordEncoder.encode(newUser.getPassword());
        userGl.setFirstName(newUser.getFirstName());
        userGl.setLastName(newUser.getLastName());
        userGl.setUsername(newUser.getUsername());
        userGl.setPassword(hashedPassword);
        userGl.setEmail(newUser.getEmail());
        userGl.setCreatedAt(java.time.LocalDateTime.now());
        userGl.setUpdatedAt(java.time.LocalDateTime.now());
        if (newUser.getRole().contains("OWNER")) {
            userGl.setRoles("ROLE_OWNER");
        } else {
            userGl.setRoles("ROLE_USER");
        }
        if(Objects.equals(userGl.getRoles(), "ROLE_OWNER")){
            Subscription subscription = new Subscription(TypeSubscription.GRATUIT);
            subscriptionService.save(subscription);
            userGl.setSubscription(subscription);
        }
        else {
            userGl.setSubscription(null);
        }
        userService.saveUser(userGl);
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("message", "User registered successfully!");
        return modelAndView;
    }}
