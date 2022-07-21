package vetClinic.enums;

import lombok.Getter;
import vetClinic.model.Client;
import vetClinic.model.personal.Worker;

import java.io.File;

@Getter
public enum ToJsonType {
    WORKERS(new File("src\\main\\java\\vetClinic\\repository\\workers.json"), Worker.class),

    CLIENTS(new File( "src\\main\\java\\vetClinic\\repository\\clients.json"), Client.class);

    final File file;
    final Class<? extends Model> modelClass;

    ToJsonType(File file, Class<? extends Model> modelClass) {
        this.file = file;
        this.modelClass = modelClass;
    }
}
