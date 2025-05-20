package org.example.repositories;

import org.example.models.Breed;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

public interface IBreedRepository {
    Optional<Breed> findById(String id);
    Map<String, Breed> findByUserId(String userId);
    void save(Breed breed);
    void deleteById(String id);
}
