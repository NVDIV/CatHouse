package org.example.repositories.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.models.Breed;
import org.example.repositories.IBreedRepository;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class BreedJsonRepository implements IBreedRepository {

    private final File file;
    private final ObjectMapper objectMapper;
    private final Map<String, Breed> breeds;

    public BreedJsonRepository(String filePath) {
        this.file = new File(filePath);
        this.objectMapper = new ObjectMapper();
        this.breeds = loadBreeds();
    }

    private Map<String, Breed> loadBreeds() {
        if (!file.exists()) {
            return new HashMap<>();
        }

        try {
            List<Breed> breedList = objectMapper.readValue(file, new TypeReference<List<Breed>>() {});
            Map<String, Breed> breedMap = new HashMap<>();
            for (Breed breed : breedList) {
                breedMap.put(breed.getId(), breed);
            }
            return breedMap;
        } catch (IOException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    private void saveBreedsToFile() {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, new ArrayList<>(breeds.values()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Breed> findById(String id) {
        return Optional.ofNullable(breeds.get(id));
    }

    @Override
    public Map<String, Breed> findByUserId(String userId) {
        Map<String, Breed> result = new HashMap<>();
        for (Map.Entry<String, Breed> entry : breeds.entrySet()) {
            if (entry.getValue().getUserId().equals(userId)) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }

    @Override
    public void save(Breed breed) {
        breeds.put(breed.getId(), breed);
        saveBreedsToFile();
    }

    @Override
    public void deleteById(String id) {
        breeds.remove(id);
        saveBreedsToFile();
    }
}
