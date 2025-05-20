package org.example.models;

import java.time.LocalDate;

public class Kitten extends Cat {
    public String gender;

    public Kitten() {
    }

    @Override
    public boolean isAvailableForBreeding() {
        return false; // Always returns false
    }

    @Override
    boolean isAdult(LocalDate currentDate) {
        return birthDate != null && birthDate.plusMonths(12).isBefore(currentDate);
    }
}
