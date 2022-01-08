package com.finki.dians.mychargingstation.services;

import com.finki.dians.mychargingstation.models.Reservation;
import com.finki.dians.mychargingstation.models.enums.ReservationStatus;
import com.finki.dians.mychargingstation.models.exceptions.InvalidArgumentsException;
import com.finki.dians.mychargingstation.models.exceptions.LocationNotFoundException;
import com.finki.dians.mychargingstation.models.exceptions.ReservationNotFoundException;
import com.finki.dians.mychargingstation.models.exceptions.UserNotFoundException;
import com.finki.dians.mychargingstation.repositories.LocationRepository;
import com.finki.dians.mychargingstation.repositories.ReservationRepository;
import com.finki.dians.mychargingstation.repositories.UserRepository;
import com.finki.dians.mychargingstation.services.serviceinterfaces.ReservationServiceInterface;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReservationService implements ReservationServiceInterface {

    private final ReservationRepository reservationRepository;
    private final LocationRepository locationRepository;
    private final UserRepository userRepository;

    public ReservationService(ReservationRepository reservationRepository, LocationRepository locationRepository, UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.locationRepository = locationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Reservation> listAllByUserId(int user_id) {
        return reservationRepository.findAllByUser_id(user_id);
    }

    @Override
    public Reservation create(int user_id, int location_id, Date date_time, ReservationStatus reservationStatus) {
        if(location_id == 0 || date_time == null || reservationStatus == null){
            throw new InvalidArgumentsException();
        }

        checkUserAndLocation(user_id, location_id);

        Reservation reservation = new Reservation(user_id, location_id, date_time, reservationStatus);
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation update(int reservation_id, int user_id, int location_id, Date date_time, ReservationStatus reservationStatus) {
        if(location_id == 0 || date_time == null || reservationStatus == null){
            throw new InvalidArgumentsException();
        }

        checkUserAndLocation(user_id, location_id);
        if(!reservationRepository.findById(reservation_id).isPresent()){
            throw new ReservationNotFoundException();
        }

        deleteById(reservation_id);
        Reservation reservation = new Reservation(user_id, location_id, date_time, reservationStatus);
        return reservationRepository.save(reservation);
    }

    @Override
    public void deleteById(int reservation_id) {
        reservationRepository.deleteById(reservation_id);
    }

    private void checkUserAndLocation(int user_id, int location_id) {
        if(!locationRepository.findById(location_id).isPresent()){
            throw new LocationNotFoundException();
        }

        if(!userRepository.findById(user_id).isPresent()){
            throw new UserNotFoundException();
        }
    }

}
