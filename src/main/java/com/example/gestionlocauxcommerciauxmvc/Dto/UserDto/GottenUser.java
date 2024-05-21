package com.example.gestionlocauxcommerciauxmvc.Dto.UserDto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class GottenUser {
    private String username;
    private String password;
    private String email;
    private String role;
}
