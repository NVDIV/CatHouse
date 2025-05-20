package org.example.models;

import java.time.LocalDate;
import java.util.ArrayList;

public class Litter {
    String id;
    String pairId;
    LocalDate matingDate;
    LocalDate birthDate;
    LocalDate expectedBirthDate;
    ArrayList<Cat> kittens;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPairId() {
        return pairId;
    }

    public void setPairId(String pairId) {
        this.pairId = pairId;
    }

    public LocalDate getMatingDate() {
        return matingDate;
    }

    public void setMatingDate(LocalDate matingDate) {
        this.matingDate = matingDate;
    }

    public LocalDate getExpectedBirthDate() {
        return expectedBirthDate;
    }

    public void setExpectedBirthDate(LocalDate expectedBirthDate) {
        this.expectedBirthDate = expectedBirthDate;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public ArrayList<Cat> getKittens() {
        return kittens;
    }

    public void setKittens(ArrayList<Cat> kittens) {
        this.kittens = kittens;
    }
}
