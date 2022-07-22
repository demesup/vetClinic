package vetClinic.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vetClinic.enums.Model;
import vetClinic.model.animal.Animal;
import vetClinic.model.animal.Cat;
import vetClinic.model.animal.Dog;
import vetClinic.model.animal.Hamster;

import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.CLASS;
import static vetClinic.Utils.listInSeparatedLines;

@NoArgsConstructor
@Data

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property ="@class" , visible = true)

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
