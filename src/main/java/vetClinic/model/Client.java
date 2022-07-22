package vetClinic.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.NoArgsConstructor;
import vetClinic.enums.Model;
import vetClinic.model.animal.Animal;

import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.CLASS;
import static vetClinic.Utils.listInSeparatedLines;

@NoArgsConstructor
@Data

@JsonTypeInfo(use = CLASS, include = PROPERTY, property = "isA", visible = true)

public class Client implements Model {
    String isA = Client.class.getName();
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