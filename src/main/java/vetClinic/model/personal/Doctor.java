package vetClinic.model.personal;

import lombok.NoArgsConstructor;
import vetClinic.model.personal.interfaces.CanHealInjury;
import vetClinic.model.personal.interfaces.CanMakeExamination;
import vetClinic.model.personal.interfaces.CanMakeVaccination;
import vetClinic.model.personal.interfaces.WorkerInterface;

@NoArgsConstructor

public class Doctor extends Worker implements CanMakeExamination, CanMakeVaccination, CanHealInjury {
    public Doctor(String name, int experience) {
        super(name, experience);
    }


}
