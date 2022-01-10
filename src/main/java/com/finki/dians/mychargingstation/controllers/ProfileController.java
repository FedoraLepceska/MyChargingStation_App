package com.finki.dians.mychargingstation.controllers;

import com.finki.dians.mychargingstation.models.Car;
import com.finki.dians.mychargingstation.models.MCSUser;
import com.finki.dians.mychargingstation.services.CarService;
import com.finki.dians.mychargingstation.services.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final UserDetailsService userService;
    private final CarService carService;

    public ProfileController(UserDetailsService userService, CarService carService) {
        this.userService = userService;
        this.carService = carService;
    }

    @GetMapping
    public String getProfilePage(@RequestParam(required = false) String error, HttpServletRequest request, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        if (request.getRemoteUser() != null) {
            MCSUser user = userService.findUserByEmail(request.getRemoteUser());
            model.addAttribute("user", user);
            List<Car> cars =  carService.listAll();
            List<Car> userCars = cars.stream()
                                            .filter(c -> c.getUser_id() == user.getUser_id())
                                            .collect(Collectors.toList());
            model.addAttribute("cars", userCars);
        }
        model.addAttribute("bodyContent", "my-profile");

        return "master-template";
    }

    @PostMapping("/add-car")
    public String addCar(HttpServletRequest request,
                         @RequestParam String car_plate,
                         @RequestParam String car_model) {
        try {
            MCSUser user;
            if (request.getRemoteUser() != null) {
                user = userService.findUserByEmail(request.getRemoteUser());
                carService.create(user.getUser_id(), car_plate, car_model);
            }
            return "redirect:/profile";
        } catch (Exception e) {
            return "redirect:/profile?=error" + e.getMessage();
        }
    }

}
