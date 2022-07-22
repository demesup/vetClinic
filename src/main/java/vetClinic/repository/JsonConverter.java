package vetClinic.repository;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import vetClinic.enums.ToJsonType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonConverter {

    private static final ObjectMapper om = new ObjectMapper();

    static {
        om.registerModule(new JavaTimeModule());
    }

    private final JavaType type;
    private final File file;
    private final String listName;

    public JsonConverter
            (ToJsonType model) {
        type = om.getTypeFactory().constructCollectionLikeType(List.class, model.getModelClass());
        file = model.getFile();
        listName = model.name();
    }

    public void saveList(List<?> list) {
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
            return om.readValue(file,type);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }
}