package vetClinic.enums;

import vetClinic.model.personal.interfaces.CanHealInjury;
import vetClinic.model.personal.interfaces.CanMakeExamination;
import vetClinic.model.personal.interfaces.CanMakeVaccination;

public enum VisitType {
    EXAMINATION(CanMakeExamination.class),
    VACCINATION(CanMakeVaccination.class),
    INJURY(CanHealInjury.class);
    final Class<?> className;

    VisitType(Class<?> className) {
        this.className = className;
    }

    public String getClassName() {
        return className.getName();
    }
}
