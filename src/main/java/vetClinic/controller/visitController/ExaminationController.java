package vetClinic.controller.visitController;

import vetClinic.enums.VisitType;
import vetClinic.model.personal.Worker;
import vetClinic.model.visit.Examination;

import java.io.IOException;
import java.time.LocalDateTime;

import static vetClinic.Utils.reader;
import static vetClinic.controller.AllControllers.workerController;

public class ExaminationController extends VisitController {

    @Override
    public Examination newVisit() throws IOException {
        Worker worker = workerController.start(VisitType.EXAMINATION);

        System.out.println("If examination is planned enter yes");
        boolean isPlanned = reader.readLine().equalsIgnoreCase("yes");
        return new Examination(LocalDateTime.now(), worker, isPlanned);
    }
}
