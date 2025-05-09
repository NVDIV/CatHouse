package org.example.models;

import java.time.LocalDate;

public class MaleCat extends Cat{
    LocalDate lastMating;

    @Override
    boolean isAvailableForBreeding() {
        // TODO: logika sprawdzania dostępności do krycia (jeśli minęło > niż 1 miesiąc od ostatniego krycia)
        return false;
    }
}
