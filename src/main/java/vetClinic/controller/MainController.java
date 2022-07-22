package vetClinic.controller;

import lombok.NoArgsConstructor;
import vetClinic.enums.ToJsonType;
import vetClinic.model.Client;
import vetClinic.model.personal.Worker;
import vetClinic.repository.JsonConverter;

import java.util.List;

import static vetClinic.Utils.listWithTitle;
import static vetClinic.Utils.readStringUpperCaseWithoutSpace;
import static vetClinic.controller.ClientController.clients;
import static vetClinic.controller.WorkerController.workers;

@NoArgsConstructor
public class MainController {
    public static VisitController visitController = new VisitController();
    public static AnimalController animalController = new AnimalController();
    public static WorkerController workerController = new WorkerController();
    public static ClientController clientController = new ClientController();
    static JsonConverter jsonConverter = new JsonConverter();

    static {
        try {
            clients = (List<Client>) jsonConverter.getList(ToJsonType.CLIENTS);
            workers = (List<Worker>) jsonConverter.getList(ToJsonType.WORKERS);

            System.out.println(listWithTitle(clients));
            System.out.println(listWithTitle(workers));
        } catch (ClassCastException e) {
            System.out.println(e.getMessage());
        }
    }

    public void start() {
        System.out.println("Welcome to clinic");

        try {

            while (true) {
                System.out.println("Enter visit action \n[add, delete, print, exit]");
                switch (readStringUpperCaseWithoutSpace()) {
                    case "ADD" -> visitController.add();
                    case "DELETE" -> visitController.delete();
                    case "PRINT" -> System.out.println(listWithTitle(clients));
                    case "EXIT" -> throw new Exception("Exiting..");
                    default -> System.out.println("wrong input");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        jsonConverter.saveList(clients, ToJsonType.CLIENTS);
        jsonConverter.saveList(workers, ToJsonType.WORKERS);

        System.out.println("Good bye!");
    }
}
