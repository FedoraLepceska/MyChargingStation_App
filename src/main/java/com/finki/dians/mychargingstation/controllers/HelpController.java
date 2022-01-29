package com.finki.dians.mychargingstation.controllers;

import com.finki.dians.mychargingstation.models.MCSUser;
import com.finki.dians.mychargingstation.services.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/help")
public class HelpController {

    private final UserDetailsService userService;

    public HelpController(UserDetailsService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getHelpPage(Model model, HttpServletRequest request) {
        model.addAttribute("bodyContent", "help");
        MCSUser user = userService.findUserByEmail(request.getRemoteUser());
        model.addAttribute("user", user);
        return "master-template";
    }
}
