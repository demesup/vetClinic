package vetclinic.repository;

import lombok.Getter;
import org.utils.model.Model;
import vetclinic.model.Client;
import vetclinic.model.personal.Worker;

import java.io.File;

@Getter
public enum ToJsonType {
    WORKERS(new File("src.main/java/vetClinic/repository/workers.json"), Worker.class),

    CLIENTS(new File( "src.main/java/vetClinic/repository/clients.json"), Client.class);

    final File file;
    final Class<? extends Model> modelClass;

    ToJsonType(File file, Class<? extends Model> modelClass) {
        this.file = file;
        this.modelClass = modelClass;
    }
}
