package com.finki.dians.mychargingstation.models;

import com.finki.dians.mychargingstation.models.enums.ChargerType;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int location_id;
    private String address;
    private float latitude;
    private float longitude;

    @Enumerated(value = EnumType.STRING)
    private ChargerType charger_type;

    public Location() {
    }

    public Location(String address, float latitude, float longitude, ChargerType charger_type) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.charger_type = charger_type;
    }

}
