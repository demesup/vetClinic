package vetclinic.model.visit;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.NoArgsConstructor;
import vetclinic.model.personal.Worker;

import java.time.LocalDateTime;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.CLASS;

@JsonTypeInfo(use = CLASS, include = PROPERTY, property = "type", visible = true)

@Data
@NoArgsConstructor
public class Injury extends Visit {
    String type = Injury.class.getName();
    String visits = type;
    private String diagnosis;
    private Set<String> procedures;


    public Injury(LocalDateTime visitDateTime, Worker worker, String diagnosis, Set<String> procedures) {
        super(visitDateTime, worker);
        this.diagnosis = diagnosis;
        this.procedures = procedures;
    }

    @Override
    public String toString() {
        return "Injury{" +
                "\n\tdiagnosis='" + diagnosis + '\'' +
                ", \n\tprocedures=" + procedures +
                ", \n\tvisitDateTime=" + visitDateTime.toString() +
                ", \n\tworker=" + worker +
                '}';
    }
}
