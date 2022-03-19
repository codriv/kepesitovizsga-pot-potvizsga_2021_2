package hu.nive.ujratervezes.kepesitovizsga_pot_potvizsga.zoo;

public class Giraffe extends ZooAnimal{

    public Giraffe(String name, int length) {
        super(name);
        this.length = length;
        type = AnimalType.GIRAFFE;
    }
}
