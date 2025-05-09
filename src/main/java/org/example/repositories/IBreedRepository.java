package org.example.repositories;

import org.example.models.Breed;

import java.util.ArrayList;
import java.util.Optional;

public interface IBreedRepository {
    Optional<Breed> findById(String id);
    ArrayList<Breed> findAll();
    void save(Breed breed);
    void deleteById(String id);
}
