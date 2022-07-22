package vetClinic.model.visit;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.NoArgsConstructor;
import vetClinic.model.personal.Worker;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.CLASS;

@JsonTypeInfo(use = CLASS, include = PROPERTY, property = "type", visible = true)

@Data
@NoArgsConstructor
public class Vaccination extends Visit{
    String type = Vaccination.class.getName();
    String visits = type;
    private String name;

    public Vaccination(LocalDateTime visitDateTime, Worker worker, String name) {
        super(visitDateTime, worker);
        this.name = name;
    }

    @Override
    public String toString() {
        return "Vaccination{" +
                "\n\tname='" + name + '\'' +
                ", \n\tvisitDateTime=" + visitDateTime.toString() +
                ", \n\tworker=" + worker +
                '}';
    }
}
