package com.example.gestionlocauxcommerciauxmvc.viewResolver;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class settingController {
    @GetMapping("/setting")
    public String setting(){
        return "setting";
    }
}
