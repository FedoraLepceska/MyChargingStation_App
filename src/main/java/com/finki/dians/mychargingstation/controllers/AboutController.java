package com.finki.dians.mychargingstation.controllers;

import com.finki.dians.mychargingstation.models.MCSUser;
import com.finki.dians.mychargingstation.services.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/about")
public class AboutController {

    private final UserDetailsService userService;

    public AboutController(UserDetailsService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAboutUsPage(Model model, HttpServletRequest request) {
        model.addAttribute("bodyContent", "about-us");
        MCSUser user = userService.findUserByEmail(request.getRemoteUser());
        model.addAttribute("user", user);
        return "master-template";
    }

}
