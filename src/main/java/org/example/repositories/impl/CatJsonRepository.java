package org.example.repositories.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.models.Cat;
import org.example.repositories.ICatRepository;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class CatJsonRepository implements ICatRepository {

    private final File file;
    private final ObjectMapper mapper;
    private final Map<String, Cat> cats;

    public CatJsonRepository(String filePath) {
        this.file = new File(filePath);
        this.mapper = new ObjectMapper();
        this.mapper.findAndRegisterModules(); // dla LocalDate
        this.cats = loadCats();
    }

    private Map<String, Cat> loadCats() {
        if (!file.exists()) return new HashMap<>();
        try {
            List<Cat> catList = mapper.readValue(file, new TypeReference<List<Cat>>() {});
            Map<String, Cat> catMap = new HashMap<>();
            for (Cat cat : catList) {
                catMap.put(cat.getId(), cat);
            }
            return catMap;
        } catch (IOException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    private void saveCatsToFile() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, new ArrayList<>(cats.values()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Cat> findById(String id) {
        return Optional.ofNullable(cats.get(id));
    }

    @Override
    public ArrayList<Cat> findAll() {
        return new ArrayList<>(cats.values());
    }

    @Override
    public void save(Cat cat) {
        cats.put(cat.getId(), cat);
        saveCatsToFile();
    }

    @Override
    public void deleteById(String id) {
        cats.remove(id);
        saveCatsToFile();
    }
}
