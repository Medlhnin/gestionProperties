package com.example.gestionlocauxcommerciauxmvc.View;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViewService {
    private ViewRepository viewRepository;

    @Autowired
    public ViewService(ViewRepository viewRepository){
        this.viewRepository = viewRepository;
    }
}
