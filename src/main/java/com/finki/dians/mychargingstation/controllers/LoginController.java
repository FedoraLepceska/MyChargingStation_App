package com.finki.dians.mychargingstation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String getLoginPage(HttpServletRequest request, Model model) {
        if(request.getRemoteUser() != null){
            return "redirect:/home";
        }

        model.addAttribute("bodyContent", "login");
        return "master-template";
    }

}
