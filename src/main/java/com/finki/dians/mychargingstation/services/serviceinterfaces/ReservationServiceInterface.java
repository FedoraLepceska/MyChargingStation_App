package com.finki.dians.mychargingstation.services.serviceinterfaces;

import com.finki.dians.mychargingstation.models.Reservation;
import com.finki.dians.mychargingstation.models.enums.ReservationStatus;

import java.util.Date;
import java.util.List;

public interface ReservationServiceInterface {

    List<Reservation> listAllByUserId(int user_id);

    Reservation create(int user_id, int location_id, Date date_time, ReservationStatus reservationStatus);

    Reservation update(int reservation_id, int user_id, int location_id, Date date_time, ReservationStatus reservationStatus);

    void deleteById(int reservation_id);

}
