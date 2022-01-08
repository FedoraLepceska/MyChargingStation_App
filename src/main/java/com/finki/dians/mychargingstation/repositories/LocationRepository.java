package com.finki.dians.mychargingstation.repositories;

import com.finki.dians.mychargingstation.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

}

