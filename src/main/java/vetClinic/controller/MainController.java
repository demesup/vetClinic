package vetClinic.controller;

import lombok.NoArgsConstructor;
import vetClinic.enums.ToJsonType;
import vetClinic.repository.JsonSaverReader;

import java.io.IOException;

import static vetClinic.Utils.reader;
import static vetClinic.controller.AllControllers.*;
import static vetClinic.controller.ClientController.clients;
import static vetClinic.controller.workerController.WorkerController.workers;

@NoArgsConstructor
public class MainController {

    public void start() throws IOException {
        JsonSaverReader jsonSaverReader = new JsonSaverReader(ToJsonType.CLIENTS, clients);
        JsonSaverReader workerSaverReader = new JsonSaverReader(ToJsonType.WORKERS, workers);

        System.out.println("Welcome to clinic");
        jsonSaverReader.getList();
        workerSaverReader.getList();

        boolean continueProgram = true;
        while (continueProgram) {
            visitController.start();

            System.out.println("If you want to exit enter exit");
            if (reader.readLine().equalsIgnoreCase("exit")) continueProgram = false;
        }
        jsonSaverReader.saveList();
        workerSaverReader.saveList();

        System.out.println("Good bye!");
    }
}
