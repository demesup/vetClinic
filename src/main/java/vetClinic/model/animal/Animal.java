package vetClinic.model.animal;

import lombok.Data;
import lombok.NoArgsConstructor;
import vetClinic.enums.Model;
import vetClinic.model.visit.Visit;

import java.util.ArrayList;
import java.util.List;

import static vetClinic.Utils.listInSeparatedLines;


@Data
@NoArgsConstructor
public abstract class Animal implements Model{

    protected String name;
    protected int age;
    protected List<Visit> visits = new ArrayList<>();
    protected List<String> vaccinations = new ArrayList<>();

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", visits=" + listInSeparatedLines(visits) +
                ", vaccinations=" + listInSeparatedLines(vaccinations) +
                '}';
    }
}
