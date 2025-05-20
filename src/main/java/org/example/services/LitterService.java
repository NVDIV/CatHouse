package org.example.services;

import org.example.models.Litter;
import org.example.repositories.ILitterRepository;

import java.util.ArrayList;

public class LitterService {
    private final ILitterRepository litterRepository;

    public LitterService(ILitterRepository repo) {
        this.litterRepository = repo;
    }

    public void addLitter(Litter litter) {
        litterRepository.save(litter);
    }

    public ArrayList<Litter> getAllLitters() {
        return litterRepository.findAll();
    }
}
