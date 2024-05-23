package com.example.gestionlocauxcommerciauxmvc.viewResolver;

import com.example.gestionlocauxcommerciauxmvc.Dto.NotificationDto;
import com.example.gestionlocauxcommerciauxmvc.Notification.Notification;
import com.example.gestionlocauxcommerciauxmvc.Notification.NotificationService;
import com.example.gestionlocauxcommerciauxmvc.Security.MyUserDetails;
import com.example.gestionlocauxcommerciauxmvc.exceptions.AppException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class helpCenterController {

    private final NotificationService notificationService;
    @GetMapping("/helpCenter")
    public String helpCenter(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            Object principal = auth.getPrincipal();
            if (principal instanceof MyUserDetails) {
                MyUserDetails userDetails = (MyUserDetails) principal;
                model.addAttribute("Notification", new NotificationDto(userDetails.getUsername(),userDetails.getEmail()));
            } else {
                throw new UsernameNotFoundException("User details not found");
            }
        }
        return "helpCenter";
    }

    @PostMapping("/helpCenter")
    public String updateSetting(@ModelAttribute("Notification") NotificationDto notificationDto){
        if(notificationDto.getUsername() == null)
        {
            throw new AppException("Username Abscent", HttpStatus.BAD_REQUEST);
        }
        notificationService.saveNotification(notificationDto);
        return "redirect:/profile";
    }
}
