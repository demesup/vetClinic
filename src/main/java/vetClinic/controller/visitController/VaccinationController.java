package vetClinic.controller.visitController;


import vetClinic.enums.VisitType;
import vetClinic.model.personal.Worker;
import vetClinic.model.visit.Vaccination;

import java.io.IOException;
import java.time.LocalDateTime;

import static vetClinic.Utils.reader;
import static vetClinic.controller.AllControllers.workerController;

public class VaccinationController extends VisitController {

    @Override
    public Vaccination newVisit() throws IOException {
        Worker worker = workerController.start(VisitType.VACCINATION);

        System.out.println("Enter vaccination name");
        String name = reader.readLine();

        return new Vaccination(LocalDateTime.now(), worker, name);
    }
}
