package org.example.repositories.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.models.Litter;
import org.example.repositories.ILitterRepository;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class LitterJsonRepository implements ILitterRepository {
    private final File file;
    private final ObjectMapper mapper;
    private final Map<String, Litter> litters;

    public LitterJsonRepository(String path) {
        this.file = new File(path);
        this.mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        this.litters = load();
    }

    private Map<String, Litter> load() {
        if (!file.exists()) return new HashMap<>();
        try {
            List<Litter> list = mapper.readValue(file, new TypeReference<List<Litter>>() {});
            Map<String, Litter> map = new HashMap<>();
            for (Litter litter : list) {
                map.put(litter.getId(), litter);
            }
            return map;
        } catch (IOException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    private void saveToFile() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, new ArrayList<>(litters.values()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Litter> findById(String id) {
        return Optional.ofNullable(litters.get(id));
    }

    @Override
    public ArrayList<Litter> findAll() {
        return new ArrayList<>(litters.values());
    }

    @Override
    public void save(Litter litter) {
        litters.put(litter.getId(), litter);
        saveToFile();
    }

    @Override
    public void deleteById(String id) {
        litters.remove(id);
        saveToFile();
    }
}
