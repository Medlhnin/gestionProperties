package com.example.gestionlocauxcommerciauxmvc.User;

import com.example.gestionlocauxcommerciauxmvc.Security.MyUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.gestionlocauxcommerciauxmvc.Dto.UserDto.GottenUser;
import com.example.gestionlocauxcommerciauxmvc.Dto.UserDto.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/User")
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/{username}")
    public ResponseEntity<GottenUser> getUser(@PathVariable String username) {
        Optional<GottenUser> optionalUserGl = userService.getUser(username);
        return optionalUserGl.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") UserGl newUser) {
        String hashedPassword = passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(hashedPassword);
        userService.saveUser(newUser);
        return "login";
    }
    @PostMapping("/authenticate")
    public String login(@RequestBody LoginRequest loginRequest, Model model) {
        try {
            Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.getUsername(), loginRequest.getPassword());
            Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);
            // Si l'authentification réussit, ajoute l'authentification au contexte de sécurité
            SecurityContextHolder.getContext().setAuthentication(authenticationResponse);

            // Récupère l'utilisateur authentifié
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null && auth.isAuthenticated()) {
                Object principal = auth.getPrincipal();
                if (principal instanceof MyUserDetails userDetails) {
                    model.addAttribute("username", userDetails.getUsername());
                    model.addAttribute("authorities", userDetails.getAuthorities());
                    model.addAttribute("email", userDetails.getEmail());
                    model.addAttribute("properties", userDetails.getProperties());
                } else {
                    throw new UsernameNotFoundException("User details not found");
                }
            }
            return "profile";
        }
        catch (Exception e) {
            model.addAttribute("error", "Nom d'utilisateur ou mot de passe incorrect");
            return "login";
        }
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{username}")
    public ResponseEntity<Void> updateUser(@PathVariable String username, @RequestBody UserGl user) {
        userService.updateUser(username, user);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/Logout")
    public ResponseEntity<Void> logout() {
        return ResponseEntity.noContent().build();
    }





}
