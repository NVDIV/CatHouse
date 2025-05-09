package org.example.models;

import java.time.LocalDate;

public class FemaleCat extends Cat {
    LocalDate lastPregnancy;

    @Override
    boolean isAvailableForBreeding() {
        // TODO: logika sprawdzania dostępności do krycia (jeśli minęło > niż 4 miesięce od ostatniej ciąży)
        return false;
    }
}
