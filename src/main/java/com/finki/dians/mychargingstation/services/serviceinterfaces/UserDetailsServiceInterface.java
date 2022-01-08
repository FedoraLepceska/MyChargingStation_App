package com.finki.dians.mychargingstation.services.serviceinterfaces;

import com.finki.dians.mychargingstation.models.MCSUser;
import com.finki.dians.mychargingstation.models.enums.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserDetailsServiceInterface extends UserDetailsService {

    MCSUser register (String username, String password, String confirmPassword, String name, String surname, Role role, String phone, String car_model, String car_plate);

}
