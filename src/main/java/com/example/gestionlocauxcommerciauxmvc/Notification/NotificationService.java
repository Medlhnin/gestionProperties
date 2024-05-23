package com.example.gestionlocauxcommerciauxmvc.Notification;

import com.example.gestionlocauxcommerciauxmvc.Dto.NotificationDto;
import com.example.gestionlocauxcommerciauxmvc.User.UserGl;
import com.example.gestionlocauxcommerciauxmvc.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserService userService;

    public void saveNotification(NotificationDto notificationDto){
        UserGl user = userService.getUserGl(notificationDto.getUsername());
        if(user == null)
        {
            throw new RuntimeException("User not found");
        }
        Notification notification = new Notification(user, notificationDto.getEmail(), notificationDto.getMessage());
        notification.setDate(LocalDateTime.now());
        notificationRepository.save(notification);
    }
    public List<Notification> getNotifications(){
        return notificationRepository.findAll();
    }
}
