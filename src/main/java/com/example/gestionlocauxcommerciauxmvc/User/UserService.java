package com.example.gestionlocauxcommerciauxmvc.User;

import com.example.gestionlocauxcommerciauxmvc.Dto.UserDto.GottenUser;
import com.example.gestionlocauxcommerciauxmvc.exceptions.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
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

    public void updateUser(GottenUser updatedUser)
    {

        Optional<UserGl> userOptional = userRepository.findByUsername(updatedUser.getUsername());
        if(userOptional.isEmpty())
        {
            throw new AppException("Not Found user", HttpStatus.BAD_REQUEST);
        }
        UserGl userGl = userOptional.get();
        userGl.setFirstName(updatedUser.getFirstName());
        userGl.setLastName(updatedUser.getLastName());
        userGl.setEmail(userGl.getEmail());
        userGl.setUpdatedAt(LocalDateTime.now());
        userRepository.save(userGl);
    }


}
