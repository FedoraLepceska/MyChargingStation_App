package com.finki.dians.mychargingstation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class MyChargingStationApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyChargingStationApplication.class, args);
    }

}
