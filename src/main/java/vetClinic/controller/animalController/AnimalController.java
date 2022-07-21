package vetClinic.controller.animalController;

import vetClinic.enums.AnimalType;
import vetClinic.model.Client;
import vetClinic.model.animal.Animal;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static vetClinic.Utils.*;
import static vetClinic.controller.AllControllers.*;

public class AnimalController implements NewAnimal {

    public void chooseAnimal(Client client) throws IOException {
        List<Animal> animals = client.getAnimals();
        System.out.println(listWithTitle(animals));

        System.out.println("Enter number");
        try {
            Animal animal = animals.get(readNumber());
            visitController.addVisitToAnimal(animal);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    public void start(Client client) throws IOException {
        System.out.println("Add new or choose from existing? [new / exist]");

        switch (readStringUpperCaseWithoutSpace()) {
            case "NEW" -> {
                System.out.println("Enter animal name");
                String name = reader.readLine();
                System.out.println("Enter animal age");
                int age = readNumber();
                newAnimal(name, age, client);
            }
            case "EXIST" -> chooseAnimal(client);
            default -> System.out.println("Wrong input");
        }
    }

    @Override
    public Animal newAnimal(String name, int age, Client client) throws IOException {
        System.out.println("Enter animal type\n[cat, dog, hamster]");
        try {
            AnimalController controller = null;
            switch (AnimalType.valueOf(reader.readLine().toUpperCase(Locale.ROOT))) {
                case CAT -> controller = catController;
                case DOG -> controller = dogController;
                case HAMSTER -> controller = hamsterController;
            }
            controller.newAnimal(name, age, client);
            visitController.addVisitToAnimal(controller.newAnimal(name, age, client));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            newAnimal(name, age, client);
        }
        return null;
    }
}
