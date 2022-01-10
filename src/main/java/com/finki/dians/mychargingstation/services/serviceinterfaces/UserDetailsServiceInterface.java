package com.finki.dians.mychargingstation.services.serviceinterfaces;

import com.finki.dians.mychargingstation.models.MCSUser;
import com.finki.dians.mychargingstation.models.enums.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserDetailsServiceInterface extends UserDetailsService {

    MCSUser register (String email, String password, String confirmPassword, String name, String surname, Role role, String phone);
    MCSUser findUserByEmail(String email);
    MCSUser findById(int user_id);

}
