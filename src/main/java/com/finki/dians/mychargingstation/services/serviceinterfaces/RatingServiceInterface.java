package com.finki.dians.mychargingstation.services.serviceinterfaces;

import com.finki.dians.mychargingstation.models.Rating;

import java.util.List;
import java.util.Optional;

public interface RatingServiceInterface {

    List<Rating> listAll();

    Optional<Rating> findById(int rating_id);

    Rating create(int user_id, int location_id, int rating);

    Rating update(int rating_id, int user_id, int location_id, int rating);

    void deleteById(int rating_id);

}
