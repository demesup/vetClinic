package vetClinic.controller.animalController;

import vetClinic.model.Client;
import vetClinic.model.animal.Animal;
import vetClinic.model.animal.Cat;

import java.io.IOException;

import static vetClinic.Utils.reader;

public class CatController extends AnimalController implements NewAnimal {

    @Override
    public Animal newAnimal(String name, int age, Client client) throws IOException {
        System.out.println("If your cat is afraid of water enter yes");
        boolean isAfraidOfWater = reader.readLine().equalsIgnoreCase("yes");
        Cat cat = new Cat(name, age, isAfraidOfWater);
        client.getAnimals().add(cat);
        return cat;
    }
}
