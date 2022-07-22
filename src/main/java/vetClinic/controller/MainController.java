package vetClinic.controller;

import lombok.NoArgsConstructor;
import vetClinic.enums.ToJsonType;
import vetClinic.repository.JsonProcessing;

import java.io.IOException;

import static vetClinic.Utils.*;
import static vetClinic.controller.AllControllers.*;
import static vetClinic.controller.ClientController.clients;
import static vetClinic.controller.workerController.WorkerController.workers;

@NoArgsConstructor
public class MainController {

    public void start() throws IOException {
        JsonProcessing jsonSaverReader = new JsonProcessing(ToJsonType.CLIENTS, clients);
        JsonProcessing workerSaverReader = new JsonProcessing(ToJsonType.WORKERS, workers);

        System.out.println("Welcome to clinic");
        jsonSaverReader.getList();
//        workerSaverReader.getList();

        try {
            boolean continueProgram = true;
            while (continueProgram) {
                System.out.println("Enter visit action \n[add, delete]");
                switch (readStringUpperCaseWithoutSpace()) {
                    case "ADD" -> clientController.start();
                    case "DELETE" -> visitController.delete();
                    case "PRINT" -> System.out.println(listWithTitle(clients));
                    case "EXIT" -> {
                        throw new IOException("Exiting..");
                    }
                    default -> System.out.println("wrong input");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        jsonSaverReader.saveList();
//        workerSaverReader.saveList();

        System.out.println("Good bye!");
    }
}
