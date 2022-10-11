package vetclinic.model.animal;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.CLASS;
import static org.utils.Utils.listInSeparatedLines;

@JsonTypeInfo(use = CLASS, include = PROPERTY, property = "type", visible = true)

@NoArgsConstructor
@Data
public class Cat extends Animal {
    String type = Cat.class.getName();
    String animals = type;

    private boolean isAfraidOfWater;

    public Cat(String name, int age ,Boolean isAfraidOfWater) {
        super(name, age);
        this.isAfraidOfWater = isAfraidOfWater;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "\n\tname='" + name + '\'' +
                ", \n\tage=" + age +
                ", \n\tisAfraidOfWater=" + isAfraidOfWater +
                ", \n\tvisits=" + listInSeparatedLines(visits) +
                ", \n\tvaccinations=" + listInSeparatedLines(vaccinations) +
                '}';
    }
}
