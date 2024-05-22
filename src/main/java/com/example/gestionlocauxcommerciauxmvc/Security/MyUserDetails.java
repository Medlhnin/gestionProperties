package com.example.gestionlocauxcommerciauxmvc.Security;

import com.example.gestionlocauxcommerciauxmvc.Property.Property;
import com.example.gestionlocauxcommerciauxmvc.User.UserGl;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter

public class MyUserDetails implements UserDetails {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String username;
    private String password;
    private String email;
    private List<GrantedAuthority> authorities;
    private List<Property> properties;
    public MyUserDetails(UserGl userGl)
    {
        this.id = userGl.getId();
        this.username = userGl.getUsername();
        this.password = userGl.getPassword();
        this.email = userGl.getEmail();
        this.authorities = Arrays.stream(userGl.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        this.properties = userGl.getProperties();
        this.firstName = userGl.getFirstName();
        this.lastName = userGl.getLastName();
        this.createdAt = userGl.getCreatedAt();
        this.updatedAt = userGl.getUpdatedAt();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
