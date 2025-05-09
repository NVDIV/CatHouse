package org.example.services;

import org.example.models.User;
import org.example.repositories.IUserRepository;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class AuthService {
    private final IUserRepository userRepository;

    public AuthService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> register(String login, String password) {
        if (userRepository.findByLogin(login).isPresent()) {
            return Optional.empty(); // login already taken
        }

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        User newUser = new User(UUID.randomUUID().toString(), login, hashedPassword, new ArrayList<>());

        userRepository.save(newUser);
        return Optional.of(newUser);
    }


    Optional<User> login(String login, String password) {
        Optional<User> userOpt = userRepository.findByLogin(login);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (BCrypt.checkpw(password, user.getPasswordHash())) {
                return Optional.of(user);
            }
        }
        return Optional.empty(); // incorrect login or password
    }
}
