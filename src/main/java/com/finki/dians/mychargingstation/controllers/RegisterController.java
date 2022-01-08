package com.finki.dians.mychargingstation.controllers;

import com.finki.dians.mychargingstation.models.enums.Role;
import com.finki.dians.mychargingstation.services.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserDetailsService userService;

    public RegisterController(UserDetailsService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model){
        if(error != null || !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        return "master-template";
    }

    @PostMapping
    public String register(@RequestParam String email,
                           @RequestParam String password,
                           @RequestParam String confirmPassword,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam Role role,
                           @RequestParam String phone,
                           @RequestParam String car_model,
                           @RequestParam String car_plate){
        try{
            userService.register(email, password, confirmPassword, name, surname, role, phone, car_model, car_plate);
            return "redirect:/login";
        } catch(Exception e){
            return "redirect:/register?=error" + e.getMessage();
        }
    }


}
