package com.finki.dians.mychargingstation.services;

import com.finki.dians.mychargingstation.models.Rating;
import com.finki.dians.mychargingstation.models.exceptions.InvalidArgumentsException;
import com.finki.dians.mychargingstation.models.exceptions.LocationNotFoundException;
import com.finki.dians.mychargingstation.models.exceptions.RatingNotFoundException;
import com.finki.dians.mychargingstation.models.exceptions.UserNotFoundException;
import com.finki.dians.mychargingstation.repositories.LocationRepository;
import com.finki.dians.mychargingstation.repositories.RatingRepository;
import com.finki.dians.mychargingstation.repositories.UserRepository;
import com.finki.dians.mychargingstation.services.serviceinterfaces.RatingServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService implements RatingServiceInterface {

    private final RatingRepository ratingRepository;
    private final LocationRepository locationRepository;
    private final UserRepository userRepository;

    public RatingService(RatingRepository ratingRepository, LocationRepository locationRepository, UserRepository userRepository) {
        this.ratingRepository = ratingRepository;
        this.locationRepository = locationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Rating> listAll() {
        return ratingRepository.findAll();
    }

    @Override
    public Optional<Rating> findById(int rating_id) {
        return ratingRepository.findById(rating_id);
    }

    @Override
    public Rating create(int user_id, int location_id, int ratingVal) {
        if(user_id == 0 || location_id == 0 || ratingVal == 0){
            throw new InvalidArgumentsException();
        }

        checkUserAndLocation(user_id, location_id);

        Rating rating = new Rating(user_id, location_id, ratingVal);
        return ratingRepository.save(rating);
    }

    @Override
    public Rating update(int rating_id, int user_id, int location_id, int ratingVal) {
        if(rating_id == 0 || user_id == 0 || location_id == 0 || ratingVal == 0) {
            throw new InvalidArgumentsException();
        }

        checkUserAndLocation(user_id, location_id);
        if(!ratingRepository.findById(rating_id).isPresent()){
            throw new RatingNotFoundException();
        }


        deleteById(rating_id);
        Rating rating = new Rating(user_id, location_id, ratingVal);
        return ratingRepository.save(rating);
    }

    private void checkUserAndLocation(int user_id, int location_id) {
        if(!locationRepository.findById(location_id).isPresent()){
            throw new LocationNotFoundException();
        }

        if(!userRepository.findById(user_id).isPresent()){
            throw new UserNotFoundException();
        }
    }

    @Override
    public void deleteById(int rating_id) {
        if(rating_id == 0) {
            throw new InvalidArgumentsException();
        }

        ratingRepository.deleteById(rating_id);
    }

}
