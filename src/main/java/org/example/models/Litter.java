package org.example.models;

import java.time.LocalDate;
import java.util.ArrayList;

public class Litter {
    String id;
    String pairId;
    LocalDate matingDate;
    LocalDate birthDate;
    LocalDate expectedBirthDate;
    ArrayList<Cat> kittens;
}
