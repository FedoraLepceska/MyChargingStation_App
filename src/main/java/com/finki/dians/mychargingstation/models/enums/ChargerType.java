package com.finki.dians.mychargingstation.models.enums;

public enum ChargerType {

    WALLBE_PRO("Wallbe Pro"),
    WALLBE_PRO_PLUS("Wallbe Pro Plus");

    private String name;

    ChargerType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return name;
    }
}
