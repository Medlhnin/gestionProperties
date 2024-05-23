package com.example.gestionlocauxcommerciauxmvc.Property;

import com.example.gestionlocauxcommerciauxmvc.User.UserGl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long>{
    List<Property> findPropertiesByOwner(UserGl owner);
    List<Property> findPropertiesByCity(City city);
}
