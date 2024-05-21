package com.example.gestionlocauxcommerciauxmvc.Property.Image;

import com.example.gestionlocauxcommerciauxmvc.Property.Property;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class ImageData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    @Lob
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;
}
