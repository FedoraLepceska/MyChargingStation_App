package com.finki.dians.mychargingstation.controllers;


import com.finki.dians.mychargingstation.models.Car;
import com.finki.dians.mychargingstation.models.MCSUser;
import com.finki.dians.mychargingstation.models.Reservation;
import com.finki.dians.mychargingstation.models.enums.ReservationStatus;
import com.finki.dians.mychargingstation.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value={"/","/home"})
public class HomeController {

    private final LocationService locationService;
    private final ReservationService reservationService;
    private final UserDetailsService userService;
    private final CarService carService;

    public HomeController(LocationService locationService, ReservationService reservationService, UserDetailsService userService, CarService carService) {
        this.locationService = locationService;
        this.reservationService = reservationService;
        this.userService = userService;
        this.carService = carService;
    }

    @GetMapping
    public String getHomePage(HttpServletRequest request, Model model) {
        if (request.getRemoteUser() != null) {
            MCSUser user = userService.findUserByEmail(request.getRemoteUser());

            List<Car> cars = carService.listAll();
            List<Car> userCars = cars.stream()
                    .filter(c -> c.getUser_id() == user.getUser_id())
                    .collect(Collectors.toList());
            model.addAttribute("cars", userCars);

            List<Reservation> reservations = reservationService.listAll();
            List<Reservation> userReservation = reservations.stream()
                    .filter(c -> c.getUser_id() == user.getUser_id())
                    .collect(Collectors.toList());
            model.addAttribute("reservations", userReservation);
        }

        model.addAttribute("locations", locationService.listAll());
        model.addAttribute("bodyContent", "reservation");

        return "master-template";
    }

    @PostMapping("/reserve")
    public String makeReservation(HttpServletRequest request,
                                  @RequestParam int location_id,
                                  @RequestParam int car_id,
                                  @RequestParam String date_time) {
        try {
            MCSUser user;
            if (request.getRemoteUser() != null) {
                user = userService.findUserByEmail(request.getRemoteUser());
                LocalDateTime localDateTime = LocalDateTime.parse(date_time);
                Timestamp reservationDate = Timestamp.valueOf(localDateTime);
                reservationService.create(user.getUser_id(), location_id, car_id, reservationDate, ReservationStatus.ACTIVE);
            }
            return "redirect:/home#myReservations";
        } catch (Exception e) {
            return "redirect:/home#reserve?=error" + e.getMessage();
        }
    }

    @GetMapping("/cancel-reservation/{reservation_id}")
    public String cancelReservation(@PathVariable int reservation_id) {
        if (!reservationService.findById(reservation_id).isPresent()) {
            return "redirect:/home#myReservations?=error" + "reservationIsNotFound";
        }

        Reservation reservation = reservationService.findById(reservation_id).get();
        reservationService.update(reservation_id,
                reservation.getUser_id(),
                reservation.getLocation_id(),
                reservation.getCar_id(),
                reservation.getDate_time(),
                ReservationStatus.CANCELED
        );

        return "redirect:/home#myReservations";
    }

    @GetMapping("/access_denied")
    public String getAccessDeniedPage(Model model) {

        return "master-template";
    }

}
