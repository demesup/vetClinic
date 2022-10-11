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
public class Dog extends Animal {
    String type = Dog.class.getName();
    String animals = type;
    private boolean isLivingOutside;
    private boolean isTrained;

    public Dog(String name, int age, Boolean isLivingOutside, Boolean isTrained) {
        super(name, age);
        this.isLivingOutside = isLivingOutside;
        this.isTrained = isTrained;
    }

    @Override
    public String toString() {
        return "Dog{" +
                " \n\tname='" + name + '\'' +
                ", \n\tage=" + age +
                ", \n\tisLivingOutside=" + isLivingOutside +
                ", \n\tisTrained=" + isTrained +
                ", \n\tvisits=" + listInSeparatedLines(visits) +
                ", \n\tvaccinations=" + listInSeparatedLines(vaccinations) +
                '}';
    }
}
