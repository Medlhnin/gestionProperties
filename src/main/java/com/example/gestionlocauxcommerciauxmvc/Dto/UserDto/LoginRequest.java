package com.example.gestionlocauxcommerciauxmvc.Dto.UserDto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class LoginRequest {
    private String username;
    private String password;
}
