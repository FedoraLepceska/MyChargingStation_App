package com.finki.dians.mychargingstation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/about")
public class AboutController {

    @GetMapping
    public String getAboutUsPage(Model model, HttpServletRequest request) {
        model.addAttribute("bodyContent", "about-us");

        return "master-template";
    }

}
