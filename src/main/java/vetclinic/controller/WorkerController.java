package vetclinic.controller;

import org.utils.model.Model;
import vetclinic.model.visit.VisitType;
import vetclinic.model.personal.WorkerType;
import vetclinic.model.personal.Doctor;
import vetclinic.model.personal.Nurse;
import vetclinic.model.personal.Therapist;
import vetclinic.model.personal.Worker;
import vetclinic.model.personal.interfaces.CanHealInjury;
import vetclinic.model.personal.interfaces.CanMakeExamination;
import vetclinic.model.personal.interfaces.CanMakeVaccination;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.utils.StringRead.*;
import static org.utils.Utils.*;

public class WorkerController implements ModelController {
    public static List<Worker> workers = new ArrayList<>();
    VisitType visitType;

    public Worker getSuitableWorker(VisitType visitType) throws IOException {
        this.visitType = visitType;
        System.out.println("New worker or one from existing? [new, exist]");

        switch (readStringUpperCaseWithoutSpace()) {
            case "NEW" -> {
                return (Worker) newInstance();
            }
            case "EXIST" -> {
                return chooseInstance();
            }
            default -> {
                System.out.println("Wrong input");
                return getSuitableWorker(visitType);
            }
        }
    }

    @Override
    public Worker chooseInstance() throws IOException {

        List<Worker> workerList = getSuitableWorkers(visitType);
        if (workerList.isEmpty()) {
            System.out.println("Suitable worker list is empty. Adding new");
            newInstance();
            return chooseInstance();
        }

        System.out.println(listInSeparatedLines(workerList));

        System.out.println("Enter number");
        try {
            return (Worker) findModel(workerList, readNumber());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            return chooseInstance();
        }
    }

    @Override
    public void delete() throws IOException {
        System.out.println(listWithTitle(workers));
        workers.remove(findModel(workers, readNumber()));
    }

    @Override
    public List<?> getList() {
        return workers;
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

    @Override
    public Model newInstance() throws IOException {
        System.out.println("Enter worker name");
        String name = read();
        System.out.println("Enter worker experience");
        int experience = readNumber();

        System.out.println("Enter type[doctor, nurse, therapist]");

        try {
            Worker worker = null;
            switch (WorkerType.valueOf(readStringUpperCaseWithoutSpace())) {
                case NURSE -> worker = new Nurse(name, experience);
                case DOCTOR -> worker = new Doctor(name, experience);
                case THERAPIST -> worker = new Therapist(name, experience);
            }
            workers.add(worker);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return getSuitableWorker(visitType);
    }
}
