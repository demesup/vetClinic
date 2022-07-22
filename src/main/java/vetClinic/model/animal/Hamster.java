package vetClinic.model.animal;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

import static vetClinic.Utils.listInSeparatedLines;

@Data
public class Hamster extends Animal {

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
