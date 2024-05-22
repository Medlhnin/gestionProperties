package com.example.gestionlocauxcommerciauxmvc.Dto.UserDto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class GottenUser {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String role;

    public GottenUser(String username, String firstName, String lastName, String email)
    {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
