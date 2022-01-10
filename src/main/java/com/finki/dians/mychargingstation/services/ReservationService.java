package com.finki.dians.mychargingstation.services;

import com.finki.dians.mychargingstation.models.Reservation;
import com.finki.dians.mychargingstation.models.enums.ReservationStatus;
import com.finki.dians.mychargingstation.models.exceptions.InvalidArgumentsException;
import com.finki.dians.mychargingstation.models.exceptions.LocationNotFoundException;
import com.finki.dians.mychargingstation.models.exceptions.ReservationNotFoundException;
import com.finki.dians.mychargingstation.models.exceptions.UserNotFoundException;
import com.finki.dians.mychargingstation.repositories.ReservationRepository;
import com.finki.dians.mychargingstation.services.serviceinterfaces.ReservationServiceInterface;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService implements ReservationServiceInterface {

    private final ReservationRepository reservationRepository;
    private final LocationService locationService;
    private final UserDetailsService userService;
    private final CarService carService;

    public ReservationService(ReservationRepository reservationRepository, LocationService locationService, UserDetailsService userService, CarService carService) {
        this.reservationRepository = reservationRepository;
        this.locationService = locationService;
        this.userService = userService;
        this.carService = carService;
    }

    @Override
    public List<Reservation> listAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Optional<Reservation> findById(int reservation_id) {
        return reservationRepository.findById(reservation_id);
    }

    @Override
    public Reservation create(int user_id, int location_id, int car_id, Timestamp date_time, ReservationStatus reservationStatus) {
        if(location_id == 0 || car_id == 0 || date_time == null || reservationStatus == null){
            throw new InvalidArgumentsException();
        }

        checkUserLocationCar(user_id, location_id, car_id);

        Reservation reservation = new Reservation(user_id, location_id, car_id, date_time, reservationStatus);
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation update(int reservation_id, int user_id, int location_id, int car_id, Timestamp date_time, ReservationStatus reservationStatus) {
        if(location_id == 0 || car_id == 0 || date_time == null || reservationStatus == null){
            throw new InvalidArgumentsException();
        }

        checkUserLocationCar(user_id, location_id, car_id);
        if(!reservationRepository.findById(reservation_id).isPresent()){
            throw new ReservationNotFoundException();
        }

        deleteById(reservation_id);
        Reservation reservation = new Reservation(user_id, location_id, car_id, date_time, reservationStatus);
        return reservationRepository.save(reservation);
    }

    @Override
    public void deleteById(int reservation_id) {
        reservationRepository.deleteById(reservation_id);
    }

    private void checkUserLocationCar(int user_id, int location_id, int car_id) {
        if(!locationService.findById(location_id).isPresent()){
            throw new LocationNotFoundException();
        }

        if(userService.findById(user_id) == null){
            throw new UserNotFoundException();
        }

        if(!carService.findById(car_id).isPresent()){
            throw new UserNotFoundException();
        }
    }

}
