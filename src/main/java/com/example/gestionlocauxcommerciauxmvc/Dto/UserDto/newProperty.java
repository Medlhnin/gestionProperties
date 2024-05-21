package com.example.gestionlocauxcommerciauxmvc.Dto.UserDto;

import com.example.gestionlocauxcommerciauxmvc.Property.Category;
import com.example.gestionlocauxcommerciauxmvc.Property.City;
import com.example.gestionlocauxcommerciauxmvc.Property.Image.ImageData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class newProperty {
    private String name;
    private Category category;
    private City city;
    private double price;
    private String address;
    private int zipCode;
    private String description;
    private MultipartFile file;
}
