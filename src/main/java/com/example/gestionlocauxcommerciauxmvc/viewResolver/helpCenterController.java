package com.example.gestionlocauxcommerciauxmvc.viewResolver;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class helpCenterController {
    @GetMapping("/helpCenter")
    public String helpCenter(Model model){
        return "helpCenter";
    }
}
