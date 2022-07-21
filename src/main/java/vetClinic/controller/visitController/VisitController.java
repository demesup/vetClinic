package vetClinic.controller.visitController;

import vetClinic.enums.Model;
import vetClinic.exception.EmptyModelListException;
import vetClinic.enums.VisitType;
import vetClinic.model.Client;
import vetClinic.model.animal.Animal;
import vetClinic.model.visit.Vaccination;
import vetClinic.model.visit.Visit;

import java.io.IOException;
import java.util.List;

import static vetClinic.Utils.*;
import static vetClinic.controller.AllControllers.*;
import static vetClinic.controller.ClientController.clients;

public class VisitController {

    public void start() throws IOException {
        System.out.println("Enter visit action \n[add, delete]");
        switch (readStringUpperCaseWithoutSpace()) {
            case "ADD" -> newVisit();
            case "DELETE" -> delete();
            default -> System.out.println("wrong input");
        }
    }

    public Visit newVisit() throws IOException {
        clientController.start();
        return null;
    }

    public void addVisitToAnimal(Animal animal) throws IOException {
        System.out.println("Enter visit type\n[examination, injury, vaccination]");
        VisitController visitController = null;

        try {
            switch (VisitType.valueOf(readStringUpperCaseWithoutSpace())) {
                case INJURY -> visitController = injuryController;
                case EXAMINATION -> visitController = examinationController;
                case VACCINATION -> visitController = vaccinationController;
            }
            Visit visit = visitController.newVisit();
            if (visit instanceof Vaccination) animal.getVaccinations().add(((Vaccination) visit).getName());
            animal.getVisits().add(visit);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            addVisitToAnimal(animal);
        }
    }

    public void delete() {
        try {
            Client client = (Client) findModel(clients);
            Animal animal = (Animal) findModel(client.getAnimals());
            animal.getVisits().remove((Visit) findModel(animal.getVisits()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private Model findModel(List<? extends Model> list) throws EmptyModelListException, IOException {
        if (list.isEmpty()) throw new EmptyModelListException("Empty list");

        System.out.println(listWithTitle(list));
        System.out.println("Enter number");

        try {
            return list.get(readNumber());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage() + ". Try again");
            return findModel(list);
        }
    }
}
