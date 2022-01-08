package com.finki.dians.mychargingstation.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rating_id;
    private int user_id;
    private int location_id;
    private int rating;

    public Rating() {
    }

    public Rating(int user_id, int location_id, int rating) {
        this.user_id = user_id;
        this.location_id = location_id;
        this.rating = rating;
    }

}
