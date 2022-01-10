package com.finki.dians.mychargingstation.models;

import com.finki.dians.mychargingstation.models.enums.ReservationStatus;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reservation_id;
    private int user_id;
    private int location_id;
    private int car_id;
    private Timestamp date_time;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    public Reservation() {
    }

    public Reservation(int user_id, int location_id, int car_id, Timestamp date_time, ReservationStatus reservationStatus) {
        this.user_id = user_id;
        this.location_id = location_id;
        this.date_time = date_time;
        this.car_id = car_id;
        this.reservationStatus = reservationStatus;
    }
}
