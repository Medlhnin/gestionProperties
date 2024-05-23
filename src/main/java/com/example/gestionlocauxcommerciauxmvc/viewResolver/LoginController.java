package com.example.gestionlocauxcommerciauxmvc.viewResolver;


import com.example.gestionlocauxcommerciauxmvc.Dto.UserDto.LoginRequest;
import com.example.gestionlocauxcommerciauxmvc.Security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    private final AuthenticationManager authenticationManager;
    @Autowired
    public LoginController(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
    }
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginUser", new LoginRequest());
        return "login";
    }

    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute("loginUser") LoginRequest loginRequest, Model model) {
        try {
            Authentication authenticationRequest = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
            Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);
            // Si l'authentification réussit, ajoute l'authentification au contexte de sécurité
            SecurityContextHolder.getContext().setAuthentication(authenticationResponse);

            // Récupère l'utilisateur authentifié
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            ModelAndView modelAndView = new ModelAndView("redirect:/profile");
            modelAndView.addObject("message", "User login successfully!");
            return modelAndView;
        }
        catch (Exception e) {
            model.addAttribute("error", "Nom d'utilisateur ou mot de passe incorrect");
            ModelAndView modelAndView = new ModelAndView("login");
            modelAndView.addObject("message", "Username or password is invalid!");
            return modelAndView;
        }
    }


}
