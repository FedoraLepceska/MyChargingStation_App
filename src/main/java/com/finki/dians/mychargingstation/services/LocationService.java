package com.finki.dians.mychargingstation.services;

import com.finki.dians.mychargingstation.models.Location;
import com.finki.dians.mychargingstation.models.enums.ChargerType;
import com.finki.dians.mychargingstation.models.exceptions.InvalidArgumentsException;
import com.finki.dians.mychargingstation.models.exceptions.LocationNotFoundException;
import com.finki.dians.mychargingstation.repositories.LocationRepository;
import com.finki.dians.mychargingstation.services.serviceinterfaces.LocationServiceInterface;
import com.sun.javaws.exceptions.InvalidArgumentException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService implements LocationServiceInterface {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Location> listAll() {
        return locationRepository.findAll();
    }

    @Override
    public Optional<Location> findById(int location_id) {
        return locationRepository.findById(location_id);
    }

    @Override
    public Location create(long location_number, String address, float latitude, float longitude, ChargerType charger_type) {
        if(address == null || address.isEmpty() || latitude == 0.00 || longitude == 0.00 || charger_type == null){
            throw new InvalidArgumentsException();
        }

        Location location = new Location(location_number, address, latitude, longitude, charger_type);
        return locationRepository.save(location);
    }

    @Override
    public Location update(int location_id, long location_number, String address, float latitude, float longitude, ChargerType charger_type) {
        if (location_id == 0 || location_number == 0L || address == null || address.isEmpty() || latitude == 0.00 || longitude == 0.00 || charger_type == null) {
            throw new InvalidArgumentsException();
        }

        if(!locationRepository.findById(location_id).isPresent()) {
            throw new LocationNotFoundException();
        }

        deleteById(location_id);
        Location location = new Location(location_number, address, latitude, longitude, charger_type);
        return locationRepository.save(location);
    }

    @Override
    public void deleteById(int location_id) {
        if(location_id == 0){
            throw new InvalidArgumentsException();
        }

        locationRepository.deleteById(location_id);
    }

}
