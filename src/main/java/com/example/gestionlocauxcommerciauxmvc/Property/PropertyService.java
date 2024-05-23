package com.example.gestionlocauxcommerciauxmvc.Property;

import com.example.gestionlocauxcommerciauxmvc.User.UserGl;
import com.example.gestionlocauxcommerciauxmvc.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final UserService userService;

    public void saveProperty(Property property) {
        UserGl userGl = userService.getUserGl(property.getOwner().getUsername());
        int maxProperties = userGl.getSubscription().getMaxProperties();
        if(userGl.getProperties().size() >= maxProperties)
        {
            throw new IllegalStateException("You have reached the maximum number of properties allowed by your subscription");
        }
        userGl.getProperties().add(property);
        propertyRepository.save(property);
    }

    public List<Property> getProperties() {
        return propertyRepository.findAll();
    }

    public Property getPropertyById(Long id) {
        return propertyRepository.findById(id).orElse(null);
    }

    public List<Property> getPropertiesByOwner(String username) {
        UserGl userGl = userService.getUserGl(username);
        return propertyRepository.findPropertiesByOwner(userGl);
    }
    public List<Property> getPropertiesByCity(String cityString) {
        City city = City.valueOf(cityString);
        return propertyRepository.findPropertiesByCity(city);
    }
    public void deleteProperty(Long id) {
        UserGl userGl = userService.getUserGl(propertyRepository.findById(id).get().getOwner().getUsername());
        userGl.getProperties().remove(propertyRepository.findById(id).get());
        propertyRepository.deleteById(id);
    }
}
