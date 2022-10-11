package vetclinic.controller;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import vetclinic.model.Client;
import vetclinic.model.animal.Animal;
import vetclinic.model.personal.Worker;
import vetclinic.repository.JsonConverter;
import vetclinic.repository.ToJsonType;

import java.util.List;
import java.util.Objects;

import static org.utils.StringRead.readStringUpperCaseWithoutSpace;
import static org.utils.Utils.listWithTitle;
import static vetclinic.controller.AnimalController.animals;
import static vetclinic.controller.ClientController.clients;
import static vetclinic.controller.VisitController.visits;
import static vetclinic.controller.WorkerController.workers;

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
        } catch (ClassCastException e) {
            System.out.println(e.getMessage());
        }

        for (Client client: clients) {
            animals.addAll(client.getAnimals());
            for (Animal animal : client.getAnimals()) {
                visits.addAll(animal.getVisits());
            }
        }
    }

    public void start() {
        System.out.println("Welcome to clinic");

        try {
            while (true) {
                chooseAction(Objects.requireNonNull(chooseEntity()));
            }
        } catch (Exception ignored) {
        }
        jsonConverter.saveList(clients, ToJsonType.CLIENTS);
        jsonConverter.saveList(workers, ToJsonType.WORKERS);

        System.out.println("Good bye!");
    }

    private ModelController chooseEntity() throws Exception {
        System.out.println("Enter field to work with or anything to exit:\n[visit, worker, client, animal]");
        switch (readStringUpperCaseWithoutSpace()) {
            case "VISIT" -> {
                return visitController;
            }
            case "WORKER" -> {
                return workerController;
            }
            case "CLIENT" -> {
                return clientController;
            }
            case "ANIMAL" -> {
                return animalController;
            }
            default -> {
                System.out.println("Wrong input");
                return null;
            }
        }
    }

    private void chooseAction(@NonNull ModelController controller) throws Exception {
        System.out.println("Enter visit action ot anything to exit\n[add, delete, print, exit]");
        switch (readStringUpperCaseWithoutSpace()) {
            case "ADD" -> controller.newInstance();
            case "DELETE" -> controller.delete();
            case "PRINT" -> System.out.println(listWithTitle(controller.getList()));
            default -> System.out.println("wrong input");
        }

    }
}
