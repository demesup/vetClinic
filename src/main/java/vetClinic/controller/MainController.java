package vetClinic.controller;

import lombok.NoArgsConstructor;
import vetClinic.enums.ToJsonType;
import vetClinic.model.Client;
import vetClinic.model.personal.Worker;
import vetClinic.repository.JsonConverter;

import java.io.IOException;
import java.util.List;

import static vetClinic.Utils.*;
import static vetClinic.controller.AllControllers.*;
import static vetClinic.controller.ClientController.clients;
import static vetClinic.controller.WorkerController.workers;

@NoArgsConstructor
public class MainController {


    static JsonConverter clientsJson = new JsonConverter(ToJsonType.CLIENTS);
    static JsonConverter workerJson = new JsonConverter(ToJsonType.WORKERS);

    static {
        try {
            clients = (List<Client>) clientsJson.getList();
            workers = (List<Worker>) workerJson.getList();


            System.out.println(listWithTitle(clients));
            System.out.println(listWithTitle(workers));

        } catch (ClassCastException e) {
            System.out.println(e.getMessage());
        }
    }

    public void start() {
        System.out.println("Welcome to clinic");

        try {
            boolean continueProgram = true;
            while (continueProgram) {
                System.out.println("Enter visit action \n[add, delete, print, exit]");
                switch (readStringUpperCaseWithoutSpace()) {
                    case "ADD" -> clientController.start();
                    case "DELETE" -> visitController.delete();
                    case "PRINT" -> System.out.println(listWithTitle(clients));
                    case "EXIT" -> throw new IOException("Exiting..");
                    default -> System.out.println("wrong input");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        clientsJson.saveList(clients);
        workerJson.saveList(workers);

        System.out.println("Good bye!");
    }

}
