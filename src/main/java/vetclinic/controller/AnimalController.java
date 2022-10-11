package vetclinic.controller;

import org.utils.model.Model;
import vetclinic.model.Client;
import vetclinic.model.animal.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.utils.StringRead.*;
import static org.utils.Utils.findModel;
import static org.utils.Utils.listWithTitle;
import static vetclinic.controller.ClientController.clients;
import static vetclinic.controller.MainController.clientController;

public class AnimalController implements ModelController {
    public static List<Animal> animals = new ArrayList<>();

    public List<Animal> getList() {
        return animals;
    }

    @Override
    public Model chooseInstance() throws IOException {
        Client client = (Client) clientController.chooseInstance();
        List<Animal> animals = client.getAnimals();
        System.out.println(listWithTitle(animals));

        try {
            return animals.get(readNumber());

        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
        return chooseInstance();
    }

    public Animal newOrExisting() throws IOException {
        System.out.println("New client or one from existing? [new, exist]");

        switch (readStringUpperCaseWithoutSpace()) {
            case "NEW" -> {
                return (Animal) newInstance();
            }
            case "EXIST" -> {
                return (Animal) chooseInstance();
            }
            default -> System.out.println("Wrong input");
        }
        return newOrExisting();
    }


    public Object[] getGeneralParams() throws IOException {
        Object[] params = new Object[2];
        System.out.println("Enter animal name");
        params[0] = read();
        System.out.print("Enter animal age. ");
        params[1] = readNumber();
        return params;
    }

    @Override
    public Model newInstance() throws IOException {
        System.out.println("To create animal you also have to choose/create client");

        System.out.println("Enter animal type\n[cat, dog, hamster]");
        try {
            Animal animal = null;
            switch (AnimalType.valueOf(readStringUpperCaseWithoutSpace())) {
                case CAT -> animal = createCat();
                case DOG -> animal = createDog();
                case HAMSTER -> animal = createHamster();
            }
            clientController.newOrExisting().getAnimals().add(animal);
            animals.add(animal);
            return animal;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return newInstance();
        }
    }

    public void delete() {
        try {
            System.out.println(listWithTitle(clients));
            Client client = (Client) findModel(clients, readNumber());

            List<Animal> clientAnimals = client.getAnimals();
            System.out.println(listWithTitle(clientAnimals));
            Animal animal = (Animal) findModel(clientAnimals, readNumber());
            clientAnimals.remove(animal);
            animals.remove(animal);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private Hamster createHamster() throws IOException {
        Object[] params = getGeneralParams();

        System.out.println("Enter yes if your hamster is active");
        boolean isActive = inputEqualsYes();
        return new Hamster((String) params[0], (Integer) params[1], isActive);
    }

    private Cat createCat() throws IOException {
        Object[] params = getGeneralParams();
        System.out.println("If your cat is afraid of water enter yes");
        boolean isAfraidOfWater = inputEqualsYes();

        return new Cat((String) params[0], (Integer) params[1], isAfraidOfWater);
    }

    private Dog createDog() throws IOException {
        Object[] params = getGeneralParams();

        System.out.println("Enter yes if your dog is living outside");
        boolean isLivingOutside = inputEqualsYes();
        System.out.println("Enter yes if your dog is trained");
        boolean isTrained = inputEqualsYes();

        return new Dog((String) params[0], (Integer) params[1], isLivingOutside, isTrained);
    }
}

