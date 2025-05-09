package org.example.repositories;

import org.example.models.Cat;

import java.util.ArrayList;
import java.util.Optional;

public interface ICatRepository {
    Optional<Cat> findById(String id);
    ArrayList<Cat> findAll();
    void save(Cat cat);
    void deleteById(String id);
}
