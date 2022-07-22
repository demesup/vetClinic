package vetClinic.model.visit;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vetClinic.model.personal.Worker;

import java.time.LocalDateTime;

@Data
public class Vaccination extends Visit{
    private String name;

    public Vaccination(LocalDateTime visitDateTime, Worker personal, String name) {
        super(visitDateTime, personal);
        this.name = name;
    }

    @Override
    public String toString() {
        return "Vaccination{" +
                "\n\tname='" + name + '\'' +
                ", \n\tvisitDateTime=" + visitDateTime.toString() +
                ", \n\tpersonal=" + personal +
                '}';
    }
}
