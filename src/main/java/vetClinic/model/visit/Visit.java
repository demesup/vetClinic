package vetClinic.model.visit;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vetClinic.enums.Model;
import vetClinic.model.personal.Worker;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.CLASS;

@JsonTypeInfo(use = CLASS, include = PROPERTY, property = "visits", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Examination.class, name = "type"),
        @JsonSubTypes.Type(value = Injury.class, name = "type"),
        @JsonSubTypes.Type(value = Vaccination.class, name = "type")
})

@Data
@NoArgsConstructor
public abstract class Visit implements Model {
    protected LocalDateTime visitDateTime;
    protected Worker worker;

    public Visit(LocalDateTime visitDateTime, Worker worker) {
        this.visitDateTime = visitDateTime;
        this.worker = worker;
    }
}
