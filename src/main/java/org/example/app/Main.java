package org.example.app;

import org.example.models.*;
import org.example.repositories.IBreedRepository;
import org.example.repositories.IUserRepository;
import org.example.repositories.impl.*;
import org.example.services.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

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

                        IBreedRepository breedRepository = new BreedJsonRepository("breeds.json");
                        BreedService breedService = new BreedService(breedRepository);
                        manageBreeds(loggedInUser.get(), breedService, scanner);
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

    private static void manageBreeds(User user, BreedService breedService, Scanner scanner) {
        while (true) {
            System.out.println("\n--- BREED MANAGEMENT ---");
            System.out.println("1. Add Breed");
            System.out.println("2. View Your Breeds");
            System.out.println("3. Delete Breed");
            System.out.println("4. Choose Breed");
            System.out.println("5. Back");
            System.out.print("Choose option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter breed name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter description: ");
                    String desc = scanner.nextLine();
                    String id = UUID.randomUUID().toString();
                    breedService.addBreed(new Breed(id, name, desc, user.getId()));
                    System.out.println("Breed added.");
                    break;

                case "2":
                    var breeds = breedService.getAllBreedsForUser(user.getId());
                    if (breeds.isEmpty()) {
                        System.out.println("No breeds found.");
                    } else {
                        for (Breed b : breeds) {
                            System.out.println("- " + b.getName() + " (ID: " + b.getId() + ")");
                        }
                    }
                    break;

                case "3":
                    System.out.print("Enter breed ID to delete: ");
                    String delId = scanner.nextLine();
                    breedService.deleteBreed(delId);
                    System.out.println("Breed deleted.");
                    break;

                case "4":
                    System.out.print("Enter breed ID to choose: ");
                    String chId = scanner.nextLine();
                    CatJsonRepository catRepository = new CatJsonRepository("cats.json");
                    CatService catService = new CatService(catRepository);
                    manageCatsForBreed(chId, user.getId(), catService, scanner);
                    break;

                case "5":
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void manageCatsForBreed(String breedId, String userId, CatService catService, Scanner scanner) {
        while (true) {
            System.out.println("\n--- CAT MANAGEMENT ---");
            System.out.println("1. Add Cat");
            System.out.println("2. View Cats");
            System.out.println("3. Delete Cat");
            System.out.println("4. Manage Couples and Litters");
            System.out.println("5. Back");
            System.out.print("Choose option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter birth date (yyyy-MM-dd): ");
                    LocalDate birthDate = LocalDate.parse(scanner.nextLine());

                    System.out.print("Is it male/female/kitten? ");
                    String type = scanner.nextLine().toLowerCase();

                    Cat cat;
                    String id = UUID.randomUUID().toString();

                    if (type.equals("female")) {
                        cat = new FemaleCat();
                    } else if (type.equals("male")) {
                        cat = new MaleCat();
                    } else {
                        cat = new Kitten();
                        System.out.print("Enter gender: ");
                        ((Kitten) cat).gender = scanner.nextLine();
                    }

                    cat.setId(id);
                    cat.setName(name);
                    cat.setBirthDate(birthDate);
                    cat.setBreedId(breedId);
                    cat.setUserId(userId);
                    cat.setStatus("active");

                    catService.addCat(cat);
                    System.out.println("Cat added!");
                    break;

                case "2":
                    var cats = catService.getCatsByBreed(breedId);
                    if (cats.isEmpty()) {
                        System.out.println("No cats in this breed.");
                    } else {
                        for (Cat c : cats) {
                            System.out.println("- " + c.getName() + " (ID: " + c.getId() + ")");
                        }
                    }
                    break;

                case "3":
                    System.out.print("Enter cat ID to delete: ");
                    String delId = scanner.nextLine();
                    catService.removeCat(delId);
                    System.out.println("Cat deleted.");
                    break;

                case "4":
                    CoupleJsonRepository coupleRepository = new CoupleJsonRepository("couples.json");
                    LitterJsonRepository litterJsonRepository = new LitterJsonRepository("litters.json");
                    CoupleService coupleService = new CoupleService(coupleRepository);
                    LitterService litterService = new LitterService(litterJsonRepository);
                    manageCouplesAndLitters(coupleService, litterService, catService, breedId, scanner);
                    break;

                case "5":
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    public static void manageCouplesAndLitters(
            CoupleService coupleService,
            LitterService litterService,
            CatService catService,
            String breedId,
            Scanner scanner
    ) {
        while (true) {
            System.out.println("\n--- BREEDING MENU ---");
            System.out.println("1. Create Couple");
            System.out.println("2. Show Couples");
            System.out.println("3. Add Litter");
            System.out.println("4. Show Litters");
            System.out.println("5. Back");
            System.out.print("Choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter male ID: ");
                    String maleId = scanner.nextLine();
                    System.out.print("Enter female ID: ");
                    String femaleId = scanner.nextLine();
                    coupleService.createCouple(maleId, femaleId, breedId);
                    System.out.println("Couple created.");
                    break;

                case "2":
                    for (Couple c : coupleService.getAllPairs()) {
                        if (c.getBreedId().equals(breedId)) {
                            System.out.println("Couple ID: " + c.getId() + " | Male: " + c.getMaleId() + ", Female: " + c.getFemaleId());
                        }
                    }
                    break;

                case "3":
                    System.out.print("Enter couple ID: ");
                    String coupleId = scanner.nextLine();
                    System.out.print("Enter mating date (yyyy-MM-dd): ");
                    LocalDate mating = LocalDate.parse(scanner.nextLine());

                    Litter litter = new Litter();
                    litter.setId(UUID.randomUUID().toString());
                    litter.setPairId(coupleId);
                    litter.setMatingDate(mating);
                    litter.setExpectedBirthDate(mating.plusDays(63)); // średni czas ciąży

                    litter.setKittens(new ArrayList<>());
                    litterService.addLitter(litter);
                    System.out.println("Litter added. Expected birth: " + litter.getExpectedBirthDate());
                    break;

                case "4":
                    for (Litter l : litterService.getAllLitters()) {
                        System.out.println("Litter ID: " + l.getId() + " | Couple: " + l.getPairId() +
                                " | Mating: " + l.getMatingDate() + " | Birth: " + l.getBirthDate());
                    }
                    break;

                case "5":
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
