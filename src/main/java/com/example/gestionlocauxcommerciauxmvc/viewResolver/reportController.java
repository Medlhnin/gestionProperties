package com.example.gestionlocauxcommerciauxmvc.viewResolver;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class reportController {
    @GetMapping("/reports")
    public String report(){
        return "reports";
    }
}
