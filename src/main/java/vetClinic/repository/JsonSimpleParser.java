package vetClinic.repository;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import vetClinic.enums.ToJsonType;

import java.io.FileReader;

public class JsonSimpleParser {
    public void parse() {

        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(ToJsonType.WORKERS.getFile())) {

         JSONObject rootJsonObject = (JSONObject) parser.parse(reader);



        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
