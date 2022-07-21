package vetClinic.controller;

import vetClinic.controller.animalController.AnimalController;
import vetClinic.controller.animalController.CatController;
import vetClinic.controller.animalController.DogController;
import vetClinic.controller.animalController.HamsterController;
import vetClinic.controller.visitController.ExaminationController;
import vetClinic.controller.visitController.InjuryController;
import vetClinic.controller.visitController.VaccinationController;
import vetClinic.controller.visitController.VisitController;
import vetClinic.controller.workerController.WorkerController;

public class AllControllers {
    public static VisitController visitController = new VisitController();
public static AnimalController animalController = new AnimalController();
public static WorkerController workerController = new WorkerController();
    public static CatController catController = new CatController();
    public static DogController dogController = new DogController();
    public static HamsterController hamsterController = new HamsterController();
    public static ClientController clientController = new ClientController();
    public static InjuryController injuryController = new InjuryController();
    public static ExaminationController examinationController = new ExaminationController();
    public static VaccinationController vaccinationController = new VaccinationController();
}
