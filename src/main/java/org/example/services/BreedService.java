package org.example.services;

import org.example.models.Breed;
import org.example.repositories.IBreedRepository;

import java.util.ArrayList;
import java.util.Map;

public class BreedService {
    private final IBreedRepository breedRepository;

    public BreedService(IBreedRepository breedRepository) {
        this.breedRepository = breedRepository;
    }

    public void addBreed(Breed breed) {
        breedRepository.save(breed);
    }

    public ArrayList<Breed> getAllBreedsForUser(String userId) {
        Map<String, Breed> breedMap = breedRepository.findByUserId(userId);
        return new ArrayList<>(breedMap.values());
    }

    public void deleteBreed(String id) {
        breedRepository.deleteById(id);
    }
}
