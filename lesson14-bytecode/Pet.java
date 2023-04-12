import java.util.HashMap;
import java.util.Map;

class App {
    public static void main(String[] args) {
        new Pet("Ben");
        new Pet("Eva");
        new Pet("Ben");
        var numPets = 3 + Pet.petRecords.getNumberOfPets("Ben");
        System.out.println(numPets);
    }
}

public class Pet {
    static final PetRecords petRecords = new PetRecords();

    final String name;

    public Pet(String name) {
        petRecords.incNumberOfPets(name);
        this.name = name;
    }
}

class PetRecords {
    private Map<String, Integer> pets = new HashMap<>();

    public int getNumberOfPets(String name) {
        return pets.get(name);
    }

    public int incNumberOfPets(String name) {
        int nr = pets.computeIfAbsent(name, n -> 0);
        nr++;
        pets.put(name, nr);
        return nr;
    }
}
