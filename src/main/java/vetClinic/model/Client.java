package vetClinic.model;

import lombok.Data;
import vetClinic.enums.Model;
import vetClinic.model.animal.Animal;

import java.util.*;

import static vetClinic.Utils.listInSeparatedLines;


@Data
public class Client implements Model{
    private String name;
    private List<Animal> animals = new ArrayList<>();

    public Client(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", animals=" + listInSeparatedLines(animals) +
                '}';
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }
}
