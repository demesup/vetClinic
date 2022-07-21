package vetClinic.model.animal;

import lombok.Data;

import static vetClinic.Utils.listInSeparatedLines;

@Data
public class Dog extends Animal{
    private boolean isLivingOutside;
    private boolean isTrained;

    public Dog(String name, int age, Boolean isLivingOutside, Boolean isTrained) {
        super(name, age);
        this.isLivingOutside = isLivingOutside;
        this.isTrained = isTrained;
    }

    @Override
    public String toString() {
        return "Dog{" +
                " \n\tname='" + name + '\'' +
                ", \n\tage=" + age +
                ", \n\tisLivingOutside=" + isLivingOutside +
                ", \n\tisTrained=" + isTrained +
                ", \n\tvisits=" + listInSeparatedLines(visits) +
                ", \n\tvaccinations=" + listInSeparatedLines(vaccinations) +
                '}';
    }
}
