package vetClinic.model.personal;

import lombok.NoArgsConstructor;
import vetClinic.model.personal.interfaces.CanMakeVaccination;
import vetClinic.model.personal.interfaces.WorkerInterface;

@NoArgsConstructor

public class Nurse extends Worker implements CanMakeVaccination {
    public Nurse(String name, int experience) {
        super(name, experience);
    }
}
