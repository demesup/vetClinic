package vetclinic.controller;


import org.utils.model.Model;

import java.io.IOException;
import java.util.List;

public interface ModelController {
    <T extends Model> T newInstance() throws IOException;
    <T extends Model> T chooseInstance() throws IOException;
    void delete() throws IOException;

    <T extends Model> List<T> getList();

}
