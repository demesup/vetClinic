package vetClinic.controller;

import vetClinic.enums.ProcedureType;
import vetClinic.enums.VisitType;
import vetClinic.model.Client;
import vetClinic.model.animal.Animal;
import vetClinic.model.personal.Worker;
import vetClinic.model.visit.Examination;
import vetClinic.model.visit.Injury;
import vetClinic.model.visit.Vaccination;
import vetClinic.model.visit.Visit;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static vetClinic.Utils.*;
import static vetClinic.controller.ClientController.clients;
import static vetClinic.controller.MainController.clientController;
import static vetClinic.controller.MainController.workerController;

public class VisitController {

    public Visit newVisit() throws IOException {
        System.out.println("Enter visit type\n[examination, injury, vaccination]");
        Visit visit = null;

        try {
            switch (VisitType.valueOf(readStringUpperCaseWithoutSpace())) {
                case INJURY -> visit = createInjury();
                case EXAMINATION -> visit = createExamination();
                case VACCINATION -> visit = createVaccination();
            }
            return visit;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return newVisit();
        }
    }

    public void delete() {
        try {
            Client client = (Client) findModel(clients, readNumber());
            Animal animal = (Animal) findModel(client.getAnimals(), readNumber());

            List<Visit> visits = animal.getVisits();
            visits.remove((Visit) findModel(visits, readNumber()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Vaccination createVaccination() throws IOException {
        Worker worker = workerController.start(VisitType.VACCINATION);

        System.out.println("Enter vaccination name");
        String name = reader.readLine();

        return new Vaccination(LocalDateTime.now(), worker, name);
    }

    public Examination createExamination() throws IOException {
        Worker worker = workerController.start(VisitType.EXAMINATION);

        System.out.println("If examination is planned enter yes");
        boolean isPlanned = reader.readLine().equalsIgnoreCase("yes");
        return new Examination(LocalDateTime.now(), worker, isPlanned);
    }

    private Injury createInjury() throws IOException {
        Worker worker = workerController.start(VisitType.INJURY);

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
        return reader.readLine();
    }

    public void add() throws IOException {
        clientController.start();
    }
}
