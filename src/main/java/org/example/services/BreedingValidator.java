package org.example.services;

import org.example.models.Cat;
import org.example.models.FemaleCat;
import org.example.models.MaleCat;

import java.time.LocalDate;

public class BreedingValidator {

    public boolean isAvailableForBreeding(Cat cat) {
        return cat.isAvailableForBreeding();
    }

    public boolean isValidPair(MaleCat male, FemaleCat female) {
        return isAvailableForBreeding(male) && isAvailableForBreeding(female);
    }
}
