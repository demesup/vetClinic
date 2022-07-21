package vetClinic.controller.animalController;

import vetClinic.model.Client;
import vetClinic.model.animal.Animal;
import vetClinic.model.animal.Hamster;

import java.io.IOException;

import static vetClinic.Utils.reader;

public class HamsterController extends AnimalController implements NewAnimal {

    @Override
    public Animal newAnimal(String name, int age, Client client) throws IOException {

        System.out.println("Enter yes if your hamster is active");
        boolean isActive = reader.readLine().equalsIgnoreCase("yes");
        Hamster hamster = new Hamster(name, age, isActive);

        client.getAnimals().add(hamster);
        return hamster;
    }
}
