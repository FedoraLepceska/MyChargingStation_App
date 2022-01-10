package com.finki.dians.mychargingstation.repositories;

import com.finki.dians.mychargingstation.models.Location;
import com.finki.dians.mychargingstation.models.enums.ChargerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

}

