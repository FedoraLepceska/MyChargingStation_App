package com.finki.dians.mychargingstation.services;

import com.finki.dians.mychargingstation.models.MCSUser;
import com.finki.dians.mychargingstation.models.enums.Role;
import com.finki.dians.mychargingstation.models.exceptions.*;
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
    public MCSUser register(String email, String password, String confirmPassword, String name, String surname, Role role, String phone) {

       if(email == null || password == null || name == null || surname == null || role == null || phone == null  ||
          email.isEmpty() || password.isEmpty() || name.isEmpty() || surname.isEmpty() || phone.isEmpty()){
           throw new InvalidArgumentsException();
       }

       if(!password.equals(confirmPassword)){
           throw new PasswordsDoNotMatchException();
       }

       if(userRepository.findByEmail(email).isPresent()){
           throw new EmailAlreadySignedUpException(email);
       }

       MCSUser user = new MCSUser(email, passwordEncoder.encode(password), name, surname, phone, role);
       return userRepository.save(user);

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(EmailNotFoundException::new);
    }

   @Override
    public MCSUser findUserByEmail(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(EmailNotFoundException::new);
    }

    @Override
    public MCSUser findById(int user_id) throws UsernameNotFoundException {
        return userRepository.findById(user_id)
                .orElseThrow(UserNotFoundException::new);
    }
}
