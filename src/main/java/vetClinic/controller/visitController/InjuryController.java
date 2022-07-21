package vetClinic.controller.visitController;

import vetClinic.enums.ProcedureType;
import vetClinic.enums.VisitType;
import vetClinic.model.personal.Worker;
import vetClinic.model.visit.Injury;
import vetClinic.model.visit.Visit;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static vetClinic.Utils.readNumber;
import static vetClinic.Utils.reader;
import static vetClinic.controller.AllControllers.workerController;

public class InjuryController extends VisitController {

    @Override
    public Visit newVisit() throws IOException {
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
}
