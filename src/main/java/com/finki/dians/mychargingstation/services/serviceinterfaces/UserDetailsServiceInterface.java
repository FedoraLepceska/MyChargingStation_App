package com.finki.dians.mychargingstation.services.serviceinterfaces;

import com.finki.dians.mychargingstation.models.MCSUser;
import com.finki.dians.mychargingstation.models.enums.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserDetailsServiceInterface extends UserDetailsService {

    List<MCSUser> listAll();
    MCSUser register (String email, String password, String confirmPassword, String name, String surname, Role role, String phone);
    MCSUser findUserByEmail(String email);
    MCSUser findById(int user_id);

}
