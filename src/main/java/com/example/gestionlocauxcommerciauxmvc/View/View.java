package com.example.gestionlocauxcommerciauxmvc.View;

import com.example.gestionlocauxcommerciauxmvc.Property.Property;
import com.example.gestionlocauxcommerciauxmvc.User.UserGl;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class View {
    @Id
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "view_date_time")
    private LocalDateTime localDateTime;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserGl userGl;
    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;
}
