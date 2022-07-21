package vetClinic.model.personal;

import lombok.NoArgsConstructor;
import vetClinic.model.personal.interfaces.CanHealInjury;
import vetClinic.model.personal.interfaces.CanMakeExamination;

@NoArgsConstructor
public class Therapist extends Worker implements CanMakeExamination, CanHealInjury {
    public Therapist(String name, int experience) {
        super(name, experience);
    }
}
