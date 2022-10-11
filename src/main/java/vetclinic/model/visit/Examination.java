package vetclinic.model.visit;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.NoArgsConstructor;
import vetclinic.model.personal.Worker;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.CLASS;

@JsonTypeInfo(use = CLASS, include = PROPERTY, property = "type", visible = true)

@Data
@NoArgsConstructor
public class Examination extends Visit {

    String type = Examination.class.getName();
    String visits = type;
    private boolean isPlanned;

    public Examination(LocalDateTime visitDateTime, Worker worker, boolean isPlanned) {
        super(visitDateTime, worker);
        this.isPlanned = isPlanned;
    }

    @Override
    public String toString() {
        return "Examination{" +
                "\n\tisPlanned=" + isPlanned +
                ", \n\tvisitDateTime=" + visitDateTime.toString() +
                ", \n\tworker=" + worker +
                '}';
    }
}
