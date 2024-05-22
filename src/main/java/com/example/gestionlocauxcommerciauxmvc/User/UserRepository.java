package com.example.gestionlocauxcommerciauxmvc.User;


import com.example.gestionlocauxcommerciauxmvc.Dto.UserDto.GottenUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserGl,Long> {
    Optional<UserGl> findByUsername(String username);
    Optional<GottenUser> findUserGlByUsername(String userame);
    void deleteUserGlByUsername(String username);

}
