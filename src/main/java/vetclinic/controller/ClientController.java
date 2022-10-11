package vetclinic.controller;

import org.utils.model.Model;
import vetclinic.model.Client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.utils.StringRead.*;
import static org.utils.Utils.findModel;
import static org.utils.Utils.listWithTitle;

public class ClientController implements ModelController {
    public static List<Client> clients = new ArrayList<>();

    @Override
    public Client newInstance() throws IOException {
        System.out.println("Enter client name");
        Client client = new Client(read());
        clients.add(client);
        return client;
    }

    @Override
    public List<Client> getList() {
        return clients;
    }

    @Override
    public void delete() throws IOException {
        System.out.println(listWithTitle(clients));

        clients.remove(findModel(clients, readNumber()));
    }

    @Override
    public Model chooseInstance() throws IOException {
        if (clients.isEmpty()) {
            System.out.println("No clients yet. Adding new...");
            return newInstance();
        }

        System.out.println(listWithTitle(clients));

        System.out.println("Enter number");
        return findModel(clients, readNumber());
    }

    public Client newOrExisting() throws IOException {
        System.out.println("New client or one from existing? [new, exist]");

        switch (readStringUpperCaseWithoutSpace()) {
            case "NEW" -> {
                return (Client) newInstance();
            }
            case "EXIST" -> {
                return (Client) chooseInstance();
            }
            default -> System.out.println("Wrong input");
        }

        return newOrExisting();
    }
}
