package vetClinic.model.personal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;
import vetClinic.enums.Model;
import vetClinic.model.personal.interfaces.WorkerInterface;

@NoArgsConstructor
@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.WRAPPER_OBJECT)

public abstract class Worker implements Model, WorkerInterface {

    protected String name;
    protected int experience;

    public Worker(String name, int experience) {
        this.name = name;
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "\n\ttype= " + this.getClass().getSimpleName() +
                ", \n\tname='" + name + '\'' +
                ", \n\texperience=" + experience +
                '}';
    }
}
