package com.example.gestionlocauxcommerciauxmvc.User;

import com.example.gestionlocauxcommerciauxmvc.Dto.UserDto.GottenUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }

    public Optional<GottenUser> getUser(String username)
    {
        Optional<GottenUser> optionalUserGl = userRepository.findUserGlByUsername(username);
        return optionalUserGl;
    }

    public UserGl getUserGl(String username)
    {
        if (userRepository.findByUsername(username).isPresent())
        {
            return userRepository.findByUsername(username).get();
        }
        return null;
    }

    public void saveUser(UserGl user)
    {
        userRepository.save(user);
    }

    public void deleteUser(String username)
    {
        userRepository.deleteUserGlByUsername(username);
    }

    public void updateUser(String username,UserGl user)
    {
        userRepository.save(user);
    }
}
