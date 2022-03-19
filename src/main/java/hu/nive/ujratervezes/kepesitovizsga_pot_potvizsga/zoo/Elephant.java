package hu.nive.ujratervezes.kepesitovizsga_pot_potvizsga.zoo;

public class Elephant extends ZooAnimal{

    public Elephant(String name, int length, long weight) {
        super(name);
        this.length = length;
        this.weight = weight;
        type = AnimalType.ELEPHANT;
    }
}
