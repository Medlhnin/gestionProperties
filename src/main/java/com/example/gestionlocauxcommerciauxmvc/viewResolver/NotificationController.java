package com.example.gestionlocauxcommerciauxmvc.viewResolver;


import com.example.gestionlocauxcommerciauxmvc.Dto.UserDto.GottenUser;
import com.example.gestionlocauxcommerciauxmvc.Notification.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;
    @GetMapping("/notification")
    String register(Model model) {
        model.addAttribute("notifications", notificationService.getNotifications());
        return "notification";
    }
}
