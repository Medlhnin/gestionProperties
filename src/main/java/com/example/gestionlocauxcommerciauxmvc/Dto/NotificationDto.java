package com.example.gestionlocauxcommerciauxmvc.Dto;


import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class NotificationDto {
    private String username;
    private String email;
    private String message;

    public NotificationDto(String username,String email) {
        this.username = username;
        this.email = email;
    }
}
