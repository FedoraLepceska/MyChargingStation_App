package com.finki.dians.mychargingstation.models;

import com.finki.dians.mychargingstation.models.enums.ReservationStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reservation_id;
    private int user_id;
    private int location_id;
    private Date date_time;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    public Reservation() {
    }

    public Reservation(int user_id, int location_id, Date date_time, ReservationStatus reservationStatus) {
        this.user_id = user_id;
        this.location_id = location_id;
        this.date_time = date_time;
        this.reservationStatus = reservationStatus;
    }
}
