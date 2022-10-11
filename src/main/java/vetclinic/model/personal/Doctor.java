package vetclinic.model.personal;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.NoArgsConstructor;
import vetclinic.model.personal.interfaces.CanHealInjury;
import vetclinic.model.personal.interfaces.CanMakeExamination;
import vetclinic.model.personal.interfaces.CanMakeVaccination;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.CLASS;

@JsonTypeInfo(use = CLASS, include = PROPERTY, property = "type", visible = true)

@NoArgsConstructor
public class Doctor extends Worker implements CanMakeExamination, CanMakeVaccination, CanHealInjury {
    String type = Doctor.class.getName();

    public Doctor(String name, int experience) {
        super(name, experience);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "name='" + name + '\'' +
                ", experience=" + experience +
                '}';
    }
}
