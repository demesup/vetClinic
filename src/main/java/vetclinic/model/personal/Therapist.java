package vetclinic.model.personal;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.NoArgsConstructor;
import vetclinic.model.personal.interfaces.CanHealInjury;
import vetclinic.model.personal.interfaces.CanMakeExamination;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.CLASS;

@JsonTypeInfo(use = CLASS, include = PROPERTY, property = "type", visible = true)

@NoArgsConstructor
public class Therapist extends Worker implements CanMakeExamination, CanHealInjury {
    String type = Therapist.class.getName();

    public Therapist(String name, int experience) {
        super(name, experience);
    }

    @Override
    public String toString() {
        return "Therapist{" +
                "name='" + name + '\'' +
                ", experience=" + experience +
                '}';
    }
}
