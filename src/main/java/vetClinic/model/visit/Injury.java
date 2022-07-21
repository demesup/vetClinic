package vetClinic.model.visit;

import lombok.Data;
import vetClinic.enums.ProcedureType;
import vetClinic.model.personal.Worker;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static vetClinic.Utils.listInSeparatedLines;


@Data
public class Injury extends Visit {
    private String diagnosis;
    private Set<String> procedures;


    public Injury(LocalDateTime visitDateTime, Worker personal, String diagnosis, Set<String> procedures) {
        super(visitDateTime, personal);
        this.diagnosis = diagnosis;
        this.procedures = procedures;
    }

    @Override
    public String toString() {
        return "Injury{" +
                "\n\tdiagnosis='" + diagnosis + '\'' +
                ", \n\tprocedures=" + listInSeparatedLines(List.of(procedures)) +
                ", \n\tvisitDateTime=" + visitDateTime.toString() +
                ", \n\tpersonal=" + personal +
                '}';
    }
}
