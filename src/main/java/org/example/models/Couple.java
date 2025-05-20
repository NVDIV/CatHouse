package org.example.models;

import java.util.ArrayList;

public class Couple {
    String id;
    String maleId;
    String femaleId;
    String breedId;
    ArrayList<Litter> litters;

    public void setId(String id) {
        this.id = id;
    }

    public void setMaleId(String maleId) {
        this.maleId = maleId;
    }

    public void setBreedId(String breedId) {
        this.breedId = breedId;
    }

    public void setFemaleId(String femaleId) {
        this.femaleId = femaleId;
    }

    public void setLitters(ArrayList<Litter> litters) {
        this.litters = litters;
    }

    public String getBreedId() {
        return breedId;
    }

    public String getId() {
        return id;
    }

    public String getMaleId() {
        return maleId;
    }

    public String getFemaleId() {
        return femaleId;
    }
}
