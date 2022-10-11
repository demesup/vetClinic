package vetclinic.controller;

import org.utils.model.Model;
import vetclinic.model.visit.ProcedureType;
import vetclinic.model.visit.VisitType;
import vetclinic.model.Client;
import vetclinic.model.animal.Animal;
import vetclinic.model.personal.Worker;
import vetclinic.model.visit.Examination;
import vetclinic.model.visit.Injury;
import vetclinic.model.visit.Vaccination;
import vetclinic.model.visit.Visit;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.utils.StringRead.*;
import static org.utils.Utils.findModel;
import static org.utils.Utils.listWithTitle;
import static vetclinic.controller.ClientController.clients;
import static vetclinic.controller.MainController.animalController;
import static vetclinic.controller.MainController.workerController;

public class VisitController implements ModelController {
    public static List<Visit> visits = new ArrayList<>();

    public Model newInstance() throws IOException {
        System.out.println("To create visit you also hae to choose/create animal");

        System.out.println("Enter visit type\n[examination, injury, vaccination]");
        Visit visit = null;

        try {
            switch (VisitType.valueOf(readStringUpperCaseWithoutSpace())) {
                case INJURY -> visit = createInjury();
                case EXAMINATION -> visit = createExamination();
                case VACCINATION -> visit = createVaccination();
            }
            animalController.newOrExisting().getVisits().add(visit);
            return visit;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return newInstance();
        }
    }

    public void delete() {
        try {
            System.out.println(listWithTitle(clients));
            Client client = (Client) findModel(clients, readNumber());

            List<Animal> clientAnimals = client.getAnimals();
            System.out.println(listWithTitle(clientAnimals));
            Animal animal = (Animal) findModel(clientAnimals, readNumber());

            List<Visit> animalVisits = animal.getVisits();
            System.out.println(listWithTitle(animalVisits));
            Visit visit = (Visit) findModel(animalVisits, readNumber());
            animalVisits.remove(visit);
            visits.remove(visit);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<?> getList() {
        return visits;
    }

    public Vaccination createVaccination() throws IOException {
        Worker worker = workerController.getSuitableWorker(VisitType.VACCINATION);

        System.out.println("Enter vaccination name");
        String name = read();

        return new Vaccination(LocalDateTime.now(), worker, name);
    }

    public Examination createExamination() throws IOException {
        Worker worker = workerController.getSuitableWorker(VisitType.EXAMINATION);

        System.out.println("If examination is planned enter yes");
        boolean isPlanned = read().equalsIgnoreCase("yes");
        return new Examination(LocalDateTime.now(), worker, isPlanned);
    }

    private Injury createInjury() throws IOException {
        Worker worker = workerController.getSuitableWorker(VisitType.INJURY);

        String diagnosis = getDiagnosis();
        Set<String> procedures = getProcedures();

        return new Injury(LocalDateTime.now(), worker, diagnosis, procedures);
    }

    private Set<String> getProcedures() {

        Set<ProcedureType> injuryProcedures = new LinkedHashSet<>();

        try {
            while (true) {
                printProcedures(injuryProcedures);

                addProcedure(injuryProcedures);
            }
        } catch (IndexOutOfBoundsException | IOException e) {
            System.out.println(e.getMessage());
        }

        Set<String> procedureNames = new LinkedHashSet<>();
        injuryProcedures.forEach(procedureType -> procedureNames.add(procedureType.name()));

        return procedureNames;
    }

    private void addProcedure(Set<ProcedureType> injuryProcedures) throws IndexOutOfBoundsException, IOException {
        System.out.println("Enter number. To exit enter any number upper than 7 or less than 0");

        injuryProcedures.add(ProcedureType.values()[readNumber()]);
    }

    private void printProcedures(Set<ProcedureType> userSet) {
        Set<ProcedureType> setToPrint = getSetToPrint(userSet);

        setToPrint.forEach(procedureType -> System.out.println(procedureType.ordinal() + " " + procedureType.name() +
                "\n" + procedureType.getDescription()));
    }

    private Set<ProcedureType> getSetToPrint(Set<ProcedureType> userSet) {
        List<ProcedureType> procedureTypeList = List.of(ProcedureType.values());

        Set<ProcedureType> setToPrint = new LinkedHashSet<>(procedureTypeList);
        setToPrint.addAll(userSet);
        Set<ProcedureType> temp = new LinkedHashSet<>(procedureTypeList);
        temp.retainAll(userSet);
        setToPrint.removeAll(temp);
        return setToPrint;
    }

    private String getDiagnosis() throws IOException {
        System.out.println("Enter diagnosis");
        return read();
    }

    @Override
    public Model chooseInstance() throws IOException {
        Client client = (Client) findModel(clients, readNumber());
        Animal animal = (Animal) findModel(client.getAnimals(), readNumber());
        return findModel(animal.getVisits(), readNumber());
    }
}
