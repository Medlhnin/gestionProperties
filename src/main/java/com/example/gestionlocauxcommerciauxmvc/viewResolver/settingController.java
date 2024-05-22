package com.example.gestionlocauxcommerciauxmvc.viewResolver;

import com.example.gestionlocauxcommerciauxmvc.Dto.UserDto.GottenUser;
import com.example.gestionlocauxcommerciauxmvc.Security.MyUserDetails;
import com.example.gestionlocauxcommerciauxmvc.User.UserService;
import com.example.gestionlocauxcommerciauxmvc.exceptions.AppException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
@RequiredArgsConstructor
public class settingController {

    private final UserService userService;

    @GetMapping("/setting")
    public String setting(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            Object principal = auth.getPrincipal();
            if (principal instanceof MyUserDetails) {
                MyUserDetails userDetails = (MyUserDetails) principal;
                model.addAttribute("GottenUser", new GottenUser(userDetails.getUsername(), userDetails.getFirstName(), userDetails.getLastName(), userDetails.getEmail()));
            } else {
                throw new UsernameNotFoundException("User details not found");
            }
        }
        return "setting";
    }

    @PostMapping("/setting")
    public String updateSetting(@ModelAttribute("GottenUser") GottenUser updatedUser){
        if(updatedUser.getUsername() == null)
        {
            throw new AppException("Username Abscent", HttpStatus.BAD_REQUEST);
        }

        userService.updateUser(updatedUser);
        return "redirect:/profile";
    }
}
