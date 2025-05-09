package org.example.repositories;

import org.example.models.Couple;

import java.util.ArrayList;
import java.util.Optional;

public interface ICoupleRepository {
    Optional<Couple> findById(String id);
    ArrayList<Couple> findAll();
    void save(Couple couple);
    void deleteById(String id);
}
