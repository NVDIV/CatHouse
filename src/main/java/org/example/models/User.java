package org.example.models;

import java.util.ArrayList;

public class User {
    String id;
    String login;
    String passwordHash;
    ArrayList<Breed> breeds;

    public User(String id, String login, String passwordHash, ArrayList<Breed> breeds) {
        this.id = id;
        this.login = login;
        this.passwordHash = passwordHash;
        this.breeds = breeds;
    }

    public User() {
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getLogin() {
        return login;
    }
}
