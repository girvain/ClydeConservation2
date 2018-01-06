import java.util.ArrayList;

public class CageList {

    private ArrayList<Cage> cagesArray;
    private Cage lastCageAccessed;

    //Constructor
    CageList() {
        cagesArray = new ArrayList<>();
    }

    public void addCage(Cage cage) {
        cagesArray.add(cage);
    }

    /**
     * This method creates a string array, then iterates through the
     * courses and appends the course title to the array using the
     * array tool method printArray.
     */
    public void printCages() {
        for(Cage i : cagesArray) {
            System.out.println("cage: " + i.getCageNumber());
            System.out.println("species: " + i.getCurrentSpecies());

            System.out.print("current animal count: ");
            if (! i.isFull()) {
                System.out.println(i.getAnimalTreeMap().size());
            }
            else
                System.out.println("Cage Full");

            System.out.println("maximum amount: " + i.getCageLimit() + "\n");
        }

    }



    /**
     * Method to take a string input and return a course obejct if it is the same
     * as the input. returns null if there is no match.
     * @param input
     * @return
     */
    public Cage cageSearch(String input) {
        for(Cage i: cagesArray) {
            if (i.getCageNumber().toLowerCase().equals(input.toLowerCase())) {
                return i;
            }
        }
        return null;
    }

    /**
     * Method to search through all the cages (treeMaps) to find a matching animal ID.
     * It sets the last cage accessed to be the current cage it is in when it finds an animal.
     * Lastly it will return the animal if it's a match.
     * @param animalId
     * @return
     */
    public Animal bigSearch(String animalId) {
        for (Cage cage : cagesArray) {
            for (String animal : cage.getAnimalTreeMap().keySet()) {
                if (animal.equals(animalId)) {
                    lastCageAccessed = cage;
                    return cage.getAnimalTreeMap().get(animal);
                }
            }
        }
        return null;
    }

    public ArrayList<Cage> getCagesArray() {
        return cagesArray;
    }

    public Cage getLastCageAccessed() {
        return lastCageAccessed;
    }
}
