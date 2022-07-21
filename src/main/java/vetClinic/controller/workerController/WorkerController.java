package vetClinic.controller.workerController;

import vetClinic.enums.VisitType;
import vetClinic.enums.WorkerType;
import vetClinic.model.personal.Doctor;
import vetClinic.model.personal.Nurse;
import vetClinic.model.personal.Therapist;
import vetClinic.model.personal.Worker;
import vetClinic.model.personal.interfaces.CanHealInjury;
import vetClinic.model.personal.interfaces.CanMakeExamination;
import vetClinic.model.personal.interfaces.CanMakeVaccination;
import vetClinic.model.personal.interfaces.WorkerInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static vetClinic.Utils.*;

public class WorkerController {
    public static List<Worker> workers = new ArrayList<>();
    VisitType visitType;

    public Worker start(VisitType visitType) throws IOException {
        this.visitType = visitType;
        System.out.println("New worker or one from existing? [new, exist]");

        switch (readStringUpperCaseWithoutSpace()) {
            case "NEW" -> newWorker();
            case "EXIST" -> {
                return (Worker) chooseWorker();
            }
            default -> {
                System.out.println("Wrong input");
                return start(visitType);
            }
        }
        return start(visitType);
    }

    private WorkerInterface chooseWorker() throws IOException {

        List<Worker> workerList = getSuitableWorkers(visitType);
        if (workerList.isEmpty()) {
            System.out.println("Suitable worker list is empty. Adding new");
            newWorker();
            chooseWorker();
        }

        System.out.println(listInSeparatedLines(workerList));

        System.out.println("Enter number");
        try {
            return workerList.get(readNumber());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            return chooseWorker();
        }
    }

    private List<Worker> getSuitableWorkers(VisitType visitType) {
        List<Worker> suitableWorkers = new ArrayList<>();
        switch (visitType) {
            case INJURY ->
                    workers.stream().filter(worker -> worker instanceof CanHealInjury).forEach(suitableWorkers::add);
            case VACCINATION ->
                    workers.stream().filter(worker -> worker instanceof CanMakeVaccination).forEach(suitableWorkers::add);
            case EXAMINATION ->
                    workers.stream().filter(worker -> worker instanceof CanMakeExamination).forEach(suitableWorkers::add);
        }
        return suitableWorkers;
    }

    private void newWorker() throws IOException {
        System.out.println("Enter worker name");
        String name = reader.readLine();
        System.out.println("Enter worker experience");
        int experience = readNumber();

        System.out.println("Enter type[doctor, nurse, therapist]");

        try {
            switch (WorkerType.valueOf(readStringUpperCaseWithoutSpace())) {
                case NURSE -> workers.add(new Nurse(name, experience));
                case DOCTOR -> workers.add(new Doctor(name, experience));
                case THERAPIST -> workers.add(new Therapist(name, experience));
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
