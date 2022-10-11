package vetclinic.model.personal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.utils.model.Model;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.CLASS;

@JsonTypeInfo(use = CLASS, include = PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Nurse.class, name = "type"),
        @JsonSubTypes.Type(value = Doctor.class, name = "type"),
        @JsonSubTypes.Type(value = Therapist.class, name = "type")
})

@Data
@NoArgsConstructor
public abstract class Worker implements Model {
    @JsonIgnore
    String worker = Worker.class.getName();

    protected String name;
    protected int experience;

    public Worker(String name, int experience) {
        this.name = name;
        this.experience = experience;
    }
}
