package com.finki.dians.mychargingstation.services.serviceinterfaces;

import com.finki.dians.mychargingstation.models.MCSUser;

public interface AuthServiceInterface {

    MCSUser login(String email, String password);

}
