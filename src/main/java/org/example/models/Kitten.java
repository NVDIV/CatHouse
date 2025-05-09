package org.example.models;

import java.time.LocalDate;

public class Kitten extends Cat {
    String gender;

    @Override
    boolean isAvailableForBreeding() {
        return false; // Always returns false
    }

    boolean isAdult(LocalDate currentDate) {
        // TODO: logika sprawdzania wieku kociaka (jeśli minęło > niż 12 miesięcy -> return true)
        return false;
    }
}
