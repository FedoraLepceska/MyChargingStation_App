package com.finki.dians.mychargingstation.services.serviceinterfaces;

import com.finki.dians.mychargingstation.models.Reservation;
import com.finki.dians.mychargingstation.models.enums.ReservationStatus;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface ReservationServiceInterface {

    List<Reservation> listAll();

    Optional<Reservation> findById(int reservation_id);

    Reservation create(int user_id, int location_id, int car_id, Timestamp date_time, ReservationStatus reservationStatus);

    Reservation update(int reservation_id, int user_id, int location_id, int car_id, Timestamp date_time, ReservationStatus reservationStatus);

    void deleteById(int reservation_id);

}
