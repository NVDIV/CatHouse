package org.example.repositories;

import org.example.models.User;

import java.util.Optional;

public interface IUserRepository {
    Optional<User> findById(String id);
    Optional<User> findByLogin(String login);
    void save(User breeder);
    void deleteById(String id);
}
