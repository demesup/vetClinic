package vetClinic.controller;

import vetClinic.enums.AnimalType;
import vetClinic.model.Client;
import vetClinic.model.animal.Animal;
import vetClinic.model.animal.Cat;
import vetClinic.model.animal.Dog;
import vetClinic.model.animal.Hamster;
import vetClinic.model.visit.Vaccination;
import vetClinic.model.visit.Visit;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static vetClinic.Utils.*;
import static vetClinic.controller.MainController.visitController;

public class AnimalController {

    public void chooseAnimal(Client client) throws IOException {
        List<Animal> animals = client.getAnimals();
        System.out.println(listWithTitle(animals));

        System.out.println("Enter number");
        try {
            Animal animal = animals.get(readNumber());
            addVisitToAnimalList(animal);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    public void start(Client client) throws IOException {
        System.out.println("Add new or choose from existing? [new / exist]");

        switch (readStringUpperCaseWithoutSpace()) {
            case "NEW" -> getGeneralParams(client);
            case "EXIST" -> chooseAnimal(client);
            default -> System.out.println("Wrong input");
        }
    }

    public void getGeneralParams(Client client) throws IOException {
        System.out.println("Enter animal name");
        String name = reader.readLine();
        System.out.println("Enter animal age");
        int age = readNumber();
        createAnimal(name, age, client);
    }

    public void createAnimal(String name, int age, Client client) throws IOException {
        System.out.println("Enter animal type\n[cat, dog, hamster]");
        try {
            Animal animal = null;
            switch (AnimalType.valueOf(reader.readLine().toUpperCase(Locale.ROOT))) {
                case CAT -> animal = createCat(name, age);
                case DOG -> animal = createDog(name, age);
                case HAMSTER -> animal = createHamster(name, age);
            }

            client.getAnimals().add(animal);
            addVisitToAnimalList(animal);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            createAnimal(name, age, client);
        }
    }

    private Hamster createHamster(String name, int age) throws IOException {
        System.out.println("Enter yes if your hamster is active");
        boolean isActive = inputEqualsYes();
        return new Hamster(name, age, isActive);
    }

    private Cat createCat(String name, int age) throws IOException {
        System.out.println("If your cat is afraid of water enter yes");
        boolean isAfraidOfWater = inputEqualsYes();

        return new Cat(name, age, isAfraidOfWater);
    }

    private Dog createDog(String name, int age) throws IOException {
        System.out.println("Enter yes if your dog is living outside");
        boolean isLivingOutside = inputEqualsYes();
        System.out.println("Enter yes if your dog is trained");
        boolean isTrained = inputEqualsYes();

        return new Dog(name, age, isLivingOutside, isTrained);
    }

    private void addVisitToAnimalList(Animal animal) throws IOException {
        Visit visit = visitController.newVisit();

        animal.getVisits().add(visit);
        if (visit instanceof Vaccination) animal.getVaccinations().add(((Vaccination) visit).getName());
    }
}

