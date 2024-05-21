package com.example.gestionlocauxcommerciauxmvc.Security;

import com.example.gestionlocauxcommerciauxmvc.User.UserGl;
import com.example.gestionlocauxcommerciauxmvc.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserGl> userGl = userRepository.findByUsername(username);
        return new MyUserDetails(userGl.get());
    }
}
