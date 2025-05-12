package org.example.repositories.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.models.User;
import org.example.repositories.IUserRepository;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class UserJsonRepository implements IUserRepository {

    private final File file;
    private final ObjectMapper objectMapper;
    private final Map<String, User> users;

    public UserJsonRepository(String filePath) {
        this.file = new File(filePath);
        this.objectMapper = new ObjectMapper();
        this.users = loadUsers();
    }

    private Map<String, User> loadUsers() {
        if (!file.exists()) {
            return new HashMap<>();
        }

        try {
            List<User> userList = objectMapper.readValue(file, new TypeReference<List<User>>() {});
            Map<String, User> userMap = new HashMap<>();
            for (User user : userList) {
                userMap.put(user.getId(), user);
            }
            return userMap;
        } catch (IOException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    private void saveUsersToFile() {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, new ArrayList<>(users.values()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return users.values().stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst();
    }

    @Override
    public void save(User user) {
        users.put(user.getId(), user);
        saveUsersToFile();
    }

    @Override
    public void deleteById(String id) {
        users.remove(id);
        saveUsersToFile();
    }
}
