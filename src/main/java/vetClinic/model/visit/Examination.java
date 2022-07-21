package vetClinic.model.visit;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vetClinic.model.personal.Worker;

import java.time.LocalDateTime;


@Data
public class Examination extends Visit {

    private boolean isPlanned;

    public Examination(LocalDateTime visitDateTime, Worker personal, boolean isPlanned) {
        super(visitDateTime, personal);
        this.isPlanned = isPlanned;
    }

    @Override
    public String toString() {
        return "Examination{" +
                "\n\tisPlanned=" + isPlanned +
                ", \n\tvisitDateTime=" + visitDateTime.toString() +
                ", \n\tpersonal=" + personal +
                '}';
    }
}
