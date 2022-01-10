package com.finki.dians.mychargingstation.controllers;

import com.finki.dians.mychargingstation.models.Location;
import com.finki.dians.mychargingstation.models.LocationRating;
import com.finki.dians.mychargingstation.models.MCSUser;
import com.finki.dians.mychargingstation.models.Rating;
import com.finki.dians.mychargingstation.models.enums.ReservationStatus;
import com.finki.dians.mychargingstation.services.LocationService;
import com.finki.dians.mychargingstation.services.RatingService;
import com.finki.dians.mychargingstation.services.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/analytics")
public class RatingController {

    private final LocationService locationService;
    private final RatingService ratingService;
    private final UserDetailsService userService;

    public RatingController(LocationService locationService, RatingService ratingService, UserDetailsService userService) {
        this.locationService = locationService;
        this.ratingService = ratingService;
        this.userService = userService;
    }

    @GetMapping
    public String getAnalyticsPage(Model model) {
        List<Location> locations = locationService.listAll();
        List<Rating> ratings = ratingService.listAll();
        List<LocationRating> locationRatings = new ArrayList<>();

        for (Location location : locations) {

            LocationRating locationRating = new LocationRating(location);
            float avg = 1f;

            for (Rating rating : ratings) {
                if (location.getLocation_id() == rating.getLocation_id()) {
                    locationRating.setAverageRating((locationRating.getAverageRating() + rating.getRating()) / avg);
                    avg++;
                }
            }
            locationRatings.add(locationRating);
        }

        model.addAttribute("locationRatings", locationRatings);
        model.addAttribute("locations", locations);
        model.addAttribute("ratings", ratings);
        model.addAttribute("bodyContent", "analytics");

        return "master-template";
    }

    @PostMapping("/rate")
    public String rateStation(HttpServletRequest request,
                                  @RequestParam int location_id,
                                  @RequestParam int rating) {
        try {
            MCSUser user;
            if (request.getRemoteUser() != null) {
                user = userService.findUserByEmail(request.getRemoteUser());
                ratingService.create(user.getUser_id(), location_id, rating);
            }
            return "redirect:/analytics#analyticsOverview";
        } catch (Exception e) {
            return "redirect:/analytics" + e.getMessage();
        }
    }

    @GetMapping("/access_denied")
    public String getAccessDeniedPage (Model model) {

        return "master-template";
    }

}
