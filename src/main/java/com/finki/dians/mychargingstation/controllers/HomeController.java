package com.finki.dians.mychargingstation.controllers;


import com.finki.dians.mychargingstation.services.LocationService;
import com.finki.dians.mychargingstation.services.RatingService;
import com.finki.dians.mychargingstation.services.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/"})
public class HomeController {

    private final LocationService locationService;
    private final ReservationService reservationService;
    private final RatingService ratingService;

    public HomeController(LocationService locationService, ReservationService reservationService, RatingService ratingService) {
        this.locationService = locationService;
        this.reservationService = reservationService;
        this.ratingService = ratingService;
    }


    @GetMapping
    public String getHomePage(Model model) {
        model.addAttribute("locations", locationService.listAll());
        model.addAttribute("ratings", ratingService.listAll());

        return "master-template";
    }

    @GetMapping("/access_denied")
    public String getAccessDeniedPage (Model model) {
        return "master-template";
    }

}
