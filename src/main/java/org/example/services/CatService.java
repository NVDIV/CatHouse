package org.example.services;

import org.example.models.Cat;
import org.example.repositories.ICatRepository;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class CatService {
    private final ICatRepository catRepository;

    public CatService(ICatRepository catRepository) {
        this.catRepository = catRepository;
    }

    public void addCat(Cat cat) {
        catRepository.save(cat);
    }

    public void removeCat(String id) {
        catRepository.deleteById(id);
    }

    public ArrayList<Cat> getCatsByBreed(String breedId) {
        return catRepository.findAll().stream()
                .filter(cat -> cat.getBreedId().equals(breedId))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
