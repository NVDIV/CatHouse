package org.example.repositories;

import org.example.models.Litter;

import java.util.ArrayList;
import java.util.Optional;

public interface ILitterRepository {
    Optional<Litter> findById(String id);
    ArrayList<Litter> findAll();
    void save(Litter litter);
    void deleteById(String id);
}
