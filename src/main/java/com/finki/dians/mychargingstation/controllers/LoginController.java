package com.finki.dians.mychargingstation.controllers;

import com.finki.dians.mychargingstation.models.MCSUser;
import com.finki.dians.mychargingstation.services.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public String login(HttpServletRequest request, Model model) {
        MCSUser user = null;
        try {
            user = authService.login(request.getParameter("email"),
                    request.getParameter("password"));
            request.getSession().setAttribute("user", user);
        } catch(Exception e){
            model.addAttribute("hasError", true);
            model.addAttribute("error", e.getMessage());
            return "login";
        }
        return "redirect:/";
    }

}
