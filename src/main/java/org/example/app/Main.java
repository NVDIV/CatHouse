package org.example.app;

import org.example.models.User;
import org.example.repositories.IUserRepository;
import org.example.repositories.impl.UserJsonRepository;
import org.example.services.AuthService;

import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        IUserRepository userRepository = new UserJsonRepository("users.json");
        AuthService authService = new AuthService(userRepository);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter login: ");
                    String regLogin = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String regPassword = scanner.nextLine();

                    Optional<User> registeredUser = authService.register(regLogin, regPassword);
                    if (registeredUser.isPresent()) {
                        System.out.println("Registered successfully! Your ID: " + registeredUser.get().getId());
                    } else {
                        System.out.println("Login already taken.");
                    }
                    break;

                case "2":
                    System.out.print("Enter login: ");
                    String logLogin = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String logPassword = scanner.nextLine();

                    Optional<User> loggedInUser = authService.login(logLogin, logPassword);
                    if (loggedInUser.isPresent()) {
                        System.out.println("Login successful! Welcome, " + loggedInUser.get().getLogin());
                    } else {
                        System.out.println("Invalid login or password.");
                    }
                    break;

                case "3":
                    System.out.println("Goodbye!");
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
            }

            System.out.println();
        }
    }
}
