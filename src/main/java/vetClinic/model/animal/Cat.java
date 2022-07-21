package vetClinic.model.animal;

import lombok.Data;

import static vetClinic.Utils.listInSeparatedLines;


@Data
public class Cat extends Animal {
    private boolean isAfraidOfWater;

    public Cat(String name, int age ,Boolean isAfraidOfWater) {
        super(name, age);
        this.isAfraidOfWater = isAfraidOfWater;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "\n\tname='" + name + '\'' +
                ", \n\tage=" + age +
                ", \n\tisAfraidOfWater=" + isAfraidOfWater +
                ", \n\tvisits=" + listInSeparatedLines(visits) +
                ", \n\tvaccinations=" + listInSeparatedLines(vaccinations) +
                '}';
    }
}
