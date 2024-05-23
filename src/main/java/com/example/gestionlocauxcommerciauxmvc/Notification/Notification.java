package com.example.gestionlocauxcommerciauxmvc.Notification;

import com.example.gestionlocauxcommerciauxmvc.User.UserGl;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private UserGl userGl;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String message;
    private boolean seen;
    private LocalDateTime date;

    public Notification(UserGl userGl, String email, String message) {
        this.userGl = userGl;
        this.email = email;
        this.message = message;
        this.seen = false;
    }

}
