package vetClinic.model.personal;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.NoArgsConstructor;
import vetClinic.model.personal.interfaces.CanMakeVaccination;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.CLASS;

@JsonTypeInfo(use = CLASS, include = PROPERTY, property = "type", visible = true)


@NoArgsConstructor
public class Nurse extends Worker implements CanMakeVaccination {
    String type = Nurse.class.getName();

    public Nurse(String name, int experience) {
        super(name, experience);
    }

    @Override
    public String toString() {
        return "Nurse{" +
                "name='" + name + '\'' +
                ", experience=" + experience +
                '}';
    }
}
