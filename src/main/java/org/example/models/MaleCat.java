package org.example.models;

import java.time.LocalDate;

public class MaleCat extends Cat{
    LocalDate lastMating;

    public MaleCat() {
    }

    @Override
    public boolean isAvailableForBreeding() {
        if (lastMating == null) return true;
        return lastMating.plusMonths(1).isBefore(LocalDate.now());
    }

    @Override
    boolean isAdult(LocalDate currentDate) {
        return true;
    }
}
