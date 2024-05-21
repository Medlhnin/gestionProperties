package com.example.gestionlocauxcommerciauxmvc.Subscription;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor

@Entity
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate beginDate;
    private TypeSubscription typeSubscription;
    private int maxProperties;
    private Float monthlySubscription;
    private boolean isActive;

    public Subscription(TypeSubscription typeSubscription) {
        this.beginDate = LocalDate.now();
        this.typeSubscription = typeSubscription;
        if(this.typeSubscription == TypeSubscription.FAMILIAL)
        {
            this.maxProperties = 1000;
            this.monthlySubscription = 120F;

        }
        else if (this.typeSubscription == TypeSubscription.DE_BASE)
        {
            this.maxProperties = 20;
            this.monthlySubscription = 50F;
        }
        else
        {
            this.maxProperties = 3;
            this.monthlySubscription = 0F;
        }
    }
}
