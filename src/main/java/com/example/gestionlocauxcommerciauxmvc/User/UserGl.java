package com.example.gestionlocauxcommerciauxmvc.User;

import com.example.gestionlocauxcommerciauxmvc.Property.Property;
import com.example.gestionlocauxcommerciauxmvc.Subscription.Subscription;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
/* @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING) */
public class UserGl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @OneToMany
    private List<Property> properties;
    @OneToOne
    private Subscription subscription;
    private String roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserGl userGl = (UserGl) o;
        return Objects.equals(id, userGl.id);
    }

}
