package com.finki.dians.mychargingstation.services;

import com.finki.dians.mychargingstation.models.MCSUser;
import com.finki.dians.mychargingstation.models.enums.Role;
import com.finki.dians.mychargingstation.models.exceptions.EmailAlreadySignedUpException;
import com.finki.dians.mychargingstation.models.exceptions.EmailNotFoundException;
import com.finki.dians.mychargingstation.models.exceptions.InvalidArgumentsException;
import com.finki.dians.mychargingstation.models.exceptions.PasswordsDoNotMatchException;
import com.finki.dians.mychargingstation.repositories.UserRepository;
import com.finki.dians.mychargingstation.services.serviceinterfaces.UserDetailsServiceInterface;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements UserDetailsServiceInterface {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDetailsService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public MCSUser register(String email, String password, String confirmPassword, String name, String surname, Role role, String phone, String car_model, String car_plate) {

       if(email == null || password == null || name == null || surname == null || role == null || phone == null || car_model == null || car_plate == null ||
          email.isEmpty() || password.isEmpty() || name.isEmpty() || surname.isEmpty() || phone.isEmpty() || car_model.isEmpty() || car_plate.isEmpty()){
           throw new InvalidArgumentsException();
       }

       if(!password.equals(confirmPassword)){
           throw new PasswordsDoNotMatchException();
       }

       if(userRepository.findByEmail(email).isPresent()){
           throw new EmailAlreadySignedUpException(email);
       }

       MCSUser user = new MCSUser(email, passwordEncoder.encode(password), name, surname, phone, car_model, car_plate, role);
       return userRepository.save(user);

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(EmailNotFoundException::new);
    }

}
