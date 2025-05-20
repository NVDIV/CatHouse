package org.example.services;

import org.example.models.Couple;
import org.example.repositories.ICoupleRepository;

import java.util.ArrayList;
import java.util.UUID;

public class CoupleService {
    private final ICoupleRepository coupleRepository;

    public CoupleService(ICoupleRepository repo) {
        this.coupleRepository = repo;
    }

    public void createCouple(String maleId, String femaleId, String breedId) {
        Couple couple = new Couple();
        couple.setId(UUID.randomUUID().toString());
        couple.setMaleId(maleId);
        couple.setFemaleId(femaleId);
        couple.setBreedId(breedId);
        couple.setLitters(new ArrayList<>());

        coupleRepository.save(couple);
    }

    public ArrayList<Couple> getAllPairs() {
        return coupleRepository.findAll();
    }
}
