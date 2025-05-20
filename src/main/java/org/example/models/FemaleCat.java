package org.example.models;

import java.time.LocalDate;

public class FemaleCat extends Cat {
    LocalDate lastPregnancy;

    public FemaleCat() {
    }

    @Override
    public boolean isAvailableForBreeding() {
        return lastPregnancy == null || lastPregnancy.plusMonths(4).isBefore(LocalDate.now());
    }

    @Override
    boolean isAdult(LocalDate currentDate) {
        return true;
    }

}
