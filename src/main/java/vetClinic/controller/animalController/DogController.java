package vetClinic.controller.animalController;

import vetClinic.model.Client;
import vetClinic.model.animal.Animal;
import vetClinic.model.animal.Dog;

import java.io.IOException;

import static vetClinic.Utils.reader;

public class DogController extends AnimalController implements NewAnimal {

    @Override
    public Animal newAnimal(String name, int age, Client client) throws IOException {
        System.out.println("Enter yes if your dog is living outside");
        boolean isLivingOutside = reader.readLine().equalsIgnoreCase("yes");
        System.out.println("Enter yes if your dog is trained");
        boolean isTrained = reader.readLine().equalsIgnoreCase("yes");

        Dog dog = new Dog(name, age, isLivingOutside, isTrained);
        client.getAnimals().add(dog);
        return dog;
    }
}
