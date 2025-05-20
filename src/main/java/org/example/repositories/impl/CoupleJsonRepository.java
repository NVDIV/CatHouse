package org.example.repositories.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.models.Couple;
import org.example.repositories.ICoupleRepository;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class CoupleJsonRepository implements ICoupleRepository {
    private final File file;
    private final ObjectMapper mapper;
    private final Map<String, Couple> couples;

    public CoupleJsonRepository(String path) {
        this.file = new File(path);
        this.mapper = new ObjectMapper().findAndRegisterModules();
        this.couples = load();
    }

    private Map<String, Couple> load() {
        if (!file.exists()) return new HashMap<>();
        try {
            List<Couple> list = mapper.readValue(file, new TypeReference<List<Couple>>() {});
            Map<String, Couple> map = new HashMap<>();
            for (Couple c : list) map.put(c.getId(), c);
            return map;
        } catch (IOException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    private void saveToFile() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, new ArrayList<>(couples.values()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Couple> findById(String id) {
        return Optional.ofNullable(couples.get(id));
    }

    @Override
    public ArrayList<Couple> findAll() {
        return new ArrayList<>(couples.values());
    }

    @Override
    public void save(Couple couple) {
        couples.put(couple.getId(), couple);
        saveToFile();
    }

    @Override
    public void deleteById(String id) {
        couples.remove(id);
        saveToFile();
    }
}
