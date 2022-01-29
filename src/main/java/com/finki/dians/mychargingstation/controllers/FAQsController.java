package com.finki.dians.mychargingstation.controllers;

import com.finki.dians.mychargingstation.models.MCSUser;
import com.finki.dians.mychargingstation.services.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/faq")
public class FAQsController {

    private final UserDetailsService userService;

    public FAQsController(UserDetailsService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getFAQsPage(Model model, HttpServletRequest request) {
        model.addAttribute("bodyContent", "faq");
        MCSUser user = userService.findUserByEmail(request.getRemoteUser());
        model.addAttribute("user", user);
        return "master-template";
    }
}
