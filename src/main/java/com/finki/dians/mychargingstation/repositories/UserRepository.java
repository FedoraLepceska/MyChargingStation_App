package com.finki.dians.mychargingstation.repositories;

import com.finki.dians.mychargingstation.models.MCSUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<MCSUser, Integer> {

    Optional<MCSUser> findByEmailAndPassword(String email, String password);
    Optional<MCSUser> findByEmail(String username);

}
