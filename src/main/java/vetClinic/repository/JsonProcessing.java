package vetClinic.repository;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import vetClinic.enums.Model;
import vetClinic.enums.ToJsonType;
import vetClinic.model.Client;
import vetClinic.model.animal.Animal;
import vetClinic.model.personal.Worker;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonProcessing {

    private static final ObjectMapper om = new ObjectMapper();

    static {
        om.registerModule(new JavaTimeModule());
        om.registerSubtypes(Animal.class);
        om.registerSubtypes(Client.class);
    }

    private final JavaType type;
    private final List<? extends Model> list;
    private final File file;
    private final Class<? extends Model> listGeneric;
    private final String listName;

    public JsonProcessing(ToJsonType model, List<? extends Model> list) {
        type = om.getTypeFactory().constructCollectionLikeType(List.class, Client.class);
        this.list = list;
        file = model.getFile();
        listGeneric = model.getModelClass();
        listName = model.name();
    }

    public void saveList() {
        if (list.isEmpty()) {
            System.out.println("List is of " + listName + " is empty");
            return;
        }

        try {
            om.writerFor(type).writeValue(file, list);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<?> getList() {
        try {
            return om.readValue(file, om.getTypeFactory().constructCollectionLikeType(List.class, listGeneric));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
