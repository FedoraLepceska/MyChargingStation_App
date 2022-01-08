package com.finki.dians.mychargingstation.services.serviceinterfaces;

import com.finki.dians.mychargingstation.models.Location;
import com.finki.dians.mychargingstation.models.enums.ChargerType;

import java.util.List;
import java.util.Optional;

public interface LocationServiceInterface {

    List<Location> listAll();

    Optional<Location> findById(int location_id);

    Location create(long location_number, String address, float latitude, float longitude, ChargerType charger_type);

    Location update(int location_id, long location_number, String address, float latitude, float longitude, ChargerType charger_type);

    void deleteById(int location_id);

}
