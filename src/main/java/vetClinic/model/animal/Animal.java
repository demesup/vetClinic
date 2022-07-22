package vetClinic.model.animal;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import vetClinic.enums.Model;
import vetClinic.model.visit.Visit;

import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.CLASS;
import static vetClinic.Utils.listInSeparatedLines;

@JsonTypeInfo(use = CLASS, include = PROPERTY, property = "animals", visible = true)


@JsonPropertyOrder({
        "name",
        "age",
        "visits",
        "vaccinations"
})
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
