package vetClinic.controller;

import vetClinic.model.Client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static vetClinic.Utils.*;
import static vetClinic.controller.AllControllers.animalController;

public class ClientController {
    public static List<Client> clients = new ArrayList<>();

    private void newClient() throws IOException {
        System.out.println("Enter client name");
        Client client = new Client(reader.readLine());
        clients.add(client);

        animalController.getAnimalParams(client);
    }

    private void chooseClient() throws IOException {
        if (clients.isEmpty()) {
            System.out.println("No clients yet. Adding new...");
            newClient();
        }

        System.out.println(listWithTitle(clients));

        System.out.println("Enter number");
        try {
            Client client = clients.get(readNumber());
            animalController.start(client);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            chooseClient();
        }
    }

    public void start() throws IOException {
        System.out.println("New client or one from existing? [new, exist]");

        switch (readStringUpperCaseWithoutSpace()) {
            case "NEW" -> newClient();
            case "EXIST" -> chooseClient();
            default -> System.out.println("Wrong input");
        }
    }
}
