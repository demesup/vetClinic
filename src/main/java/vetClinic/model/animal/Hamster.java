package vetClinic.model.animal;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.CLASS;
import static vetClinic.Utils.listInSeparatedLines;

@JsonTypeInfo(use = CLASS, include = PROPERTY, property = "type", visible = true)


@NoArgsConstructor
@Data
public class Hamster extends Animal {
    String type = Hamster.class.getName();
    String animals = type;
    private boolean isActive;

    public Hamster(String name, int age, boolean isActive) {
        super(name, age);
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "Hamster{" +
                ", \n\tname='" + name + '\'' +
                ", \n\tage=" + age +
                ", \n\tisActive=" + isActive +
                ", \n\tvisits=" + listInSeparatedLines(visits) +
                ", \n\tvaccinations=" + listInSeparatedLines(vaccinations) +
                '}';
    }
}
