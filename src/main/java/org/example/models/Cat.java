package org.example.models;

import java.time.LocalDate;

abstract public class Cat {
    String id;
    String name;
    String status;
    LocalDate birthDate;
    String litterId;
    String breedId;
    String userId;

    abstract boolean isAvailableForBreeding();
}
