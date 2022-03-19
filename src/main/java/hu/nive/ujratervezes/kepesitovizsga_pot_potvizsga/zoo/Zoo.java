package hu.nive.ujratervezes.kepesitovizsga_pot_potvizsga.zoo;

import org.mariadb.jdbc.MariaDbDataSource;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class Zoo {

    private MariaDbDataSource dataSource;

    public Zoo() {
    }

    public Zoo(MariaDbDataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Set<ZooAnimal> animals = new HashSet<>();

    public List<ZooAnimal> getAnimals() {
        return new ArrayList<>(animals);
    }

    public void addAnimal(ZooAnimal animal) {
        animals.add(animal);
    }

    public ZooAnimal getHeaviestAnimalInTheZoo() {
        return animals.stream().max(Comparator.comparing(ZooAnimal::getWeight)).get();
    }

    public long countWeights() {
        return animals.stream().mapToLong(ZooAnimal::getWeight).sum();
    }

    public ZooAnimal findExactAnimal(ZooAnimal animal) {
        List<ZooAnimal> animalsFound = animals.stream().filter(animal::equals).collect(Collectors.toList());
        if (animalsFound.isEmpty()) {
            throw new IllegalArgumentException("There is no such animal in the zoo!");
        }
        return animalsFound.get(0);
    }

    public ZooAnimal findExactAnimalByName(String name) {
        List<ZooAnimal> animalsFound = animals.stream().filter(animal -> name.equals(animal.getName())).collect(Collectors.toList());
        if (animalsFound.isEmpty()) {
            throw new IllegalArgumentException("There is no such animal in the zoo!");
        }
        return animalsFound.get(0);
    }

    public int getNumberOfAnimals() {
        return animals.size();
    }

    public List<ZooAnimal> getAnimalsOrderedByName() {
        return animals.stream().sorted(Comparator.comparing(ZooAnimal::getName)).collect(Collectors.toList());
    }

    public Map<AnimalType, Integer> getAnimalStatistics() {
        Map<AnimalType, Integer> animalsByType = new HashMap<>();
        for (AnimalType type: AnimalType.values()) {
            animalsByType.put(type, getNumberOfAnimalsPerType(type));
        }
        return animalsByType;
    }

    private int getNumberOfAnimalsPerType(AnimalType type) {
        return (int)animals.stream().filter(animal -> type == animal.getType()).count();
    }

    public void loadAnimals() throws SQLException {
        try(Connection con = dataSource.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from animals")) {
                while (rs.next()) {
                    String name = rs.getString("animal_name");
                    int length = rs.getInt("length_of_member");
                    long weight = rs.getLong("weight");
                    AnimalType type = AnimalType.valueOf(rs.getString("animal_type"));
                    switch (type) {
                        case LION: animals.add(new Lion(name));
                        break;
                        case GIRAFFE: animals.add(new Giraffe(name, length));
                        break;
                        case ELEPHANT: animals.add(new Elephant(name, length, weight));
                    }
                }
            } catch (SQLException sqle) {
            throw new IllegalStateException("Cannot reach database!");
        }
    }

    public void addAnimalToDatabase(ZooAnimal animal) {
        try(Connection con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement("insert into aniamls (animal_name, length_of_member, weight, animal_type) values (?, ?, ?, ?)")) {
            ps.setString(1, animal.getName());
            ps.setInt(2, animal.getLength());
            ps.setLong(3, animal.getWeight());
            ps.setString(4, (String.valueOf(animal.getType())));
            ps.executeUpdate();
        } catch (SQLException sqle) {
            throw new IllegalStateException("Cannot reach database!");
        }
    }
}

