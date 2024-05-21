package com.example.gestionlocauxcommerciauxmvc.Subscription;

import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {
    private SubscriptionRepository subscriptionRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository){
        this.subscriptionRepository = subscriptionRepository;
    }

    public Subscription save(Subscription subscription){
        return subscriptionRepository.save(subscription);
    }
}
