package vetClinic.model.visit;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vetClinic.enums.Model;
import vetClinic.model.personal.Worker;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Visit implements Model {
    protected LocalDateTime visitDateTime;
    protected Worker personal;
}
