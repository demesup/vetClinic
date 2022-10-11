package vetclinic.model.animal;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.utils.model.Model;
import vetclinic.model.visit.Visit;

import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.CLASS;
import static org.utils.Utils.listInSeparatedLines;

@JsonTypeInfo(use = CLASS, include = PROPERTY, property = "animals", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Cat.class, name = "type"),
        @JsonSubTypes.Type(value = Dog.class, name = "type"),
        @JsonSubTypes.Type(value = Hamster.class, name = "type")
})


@Data
@NoArgsConstructor
public abstract class Animal implements Model {

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
