package com.finki.dians.mychargingstation.models;

import lombok.Data;

@Data
public class LocationRating {

    private Location location;
    private float averageRating;

    public LocationRating() {
    }

    public LocationRating(Location location) {
        this.location = location;
        this.averageRating = 0f;
    }

    @Override
    public String toString() {
        return "Station: " + location.getAddress() + System.lineSeparator() + "Average rating: " + averageRating;
    }
}
