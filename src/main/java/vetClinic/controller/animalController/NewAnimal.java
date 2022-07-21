package vetClinic.controller.animalController;

import vetClinic.enums.Model;
import vetClinic.model.Client;

import java.io.IOException;

public interface NewAnimal {
    Model newAnimal(String name, int age, Client client) throws IOException;
}
