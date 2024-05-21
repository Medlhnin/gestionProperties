package com.example.gestionlocauxcommerciauxmvc.Property;

import com.example.gestionlocauxcommerciauxmvc.Property.Image.ImageData;
import com.example.gestionlocauxcommerciauxmvc.User.UserGl;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Category category;
    private City city;
    private double price;
    private String address;
    private int zipCode;
    private String description;
    @ManyToOne()
    private UserGl owner;
    // private byte[] imageData;

}
