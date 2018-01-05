import java.util.TreeMap;

public class Cage {

    private String cageNumber;
    private int cageLimit; // (must be set when created)
    private boolean isFull;
    private String currentSpecies;
    private TreeMap<String, Animal> animalTreeMap;
    private TreeMap<String, Keeper> keeperTreeMap;

    public Cage() {
        animalTreeMap = new TreeMap<>();
        keeperTreeMap = new TreeMap<>();
    }

    public void printAnimals() {
        for (String item : animalTreeMap.keySet()) {
            animalTreeMap.get(item).displayAnimalDetails();
        }
    }

    public void printKeepers() {
        for (String item : keeperTreeMap.keySet()) {
            keeperTreeMap.get(item).displayKeeperDetails();
        }
    }

    public void removeAnimal(String animalId) {
        animalTreeMap.remove(animalId);
        // after the removal, if the cage is empty reset the currentSpecies to null.
        if (animalTreeMap.isEmpty()) {
            currentSpecies = null;
        }
        isFull = false;
    }

    public void removeKeeper(String keeperId) {
        keeperTreeMap.remove(keeperId);
    }

    /**
     * method to add an animal to the treemap. It checks to see if the animal treemap
     * is empty. If it is then there is no species set yet and it will set according to
     * the animal being put in. Checks if the cage is full and returns false if it is.
     * returns true if animal is added successfully.
     * @param animal
     */
    public boolean addAnimal(Animal animal) {
        if (animalTreeMap.isEmpty()) {
            currentSpecies = animal.getSpecies();
        }
        animalTreeMap.put(animal.getAnimalId(), animal);
        // check to see if the cage is full
        if (cageLimit == animalTreeMap.size()) {
            isFull = true;
        }
        return true;
    }

    public boolean speciesCheck(Animal animal) {
        // if currentSpecies is null then there is no preferred species so is the
        // same as matching species for compatability.
        if (currentSpecies == null) {
            return true;
        }
        else if (currentSpecies.equals(animal.getSpecies())) {
            return true;
        }
        else
            return false;
    }

    public void addKeeper(Keeper keeper) {
        keeperTreeMap.put(keeper.getKeeperId(), keeper);
    }

    /**
     * Method to search through the cage for an animal by using the animal ID. Returns
     * the animal ID as a string if it is in the map, returns null if it's not there.
     * @param animalId
     * @return
     */
    public String searchAnimal(String animalId) {
        for (String item : animalTreeMap.keySet()) {
            if (item.equals(animalId)) {
                return item;
            }
        }
        return null;
    }

    public String searchKeeper(String keeperId) {
        for (String item : keeperTreeMap.keySet()) {
            if (item.equals(keeperId)) {
                return item;
            }
        }
        return null;
    }


    /* ================= SETTERS AND GETTERS =================== */

    public TreeMap<String, Animal> getAnimalTreeMap() {
        return animalTreeMap;
    }

    public TreeMap<String, Keeper> getKeeperTreeMap() {
        return keeperTreeMap;
    }

    public String getCageNumber() {
        return cageNumber;
    }

    public void setCageNumber(String cageNumber) {
        this.cageNumber = cageNumber;
    }

    public int getCageLimit() {
        return cageLimit;
    }

    public void setCageLimit(int cageLimit) {
        this.cageLimit = cageLimit;
    }

    public boolean isFull() {
        return isFull;
    }

    public void setFull(boolean full) {
        isFull = full;
    }

    public String getCurrentSpecies() {
        return currentSpecies;
    }

    public void setCurrentSpecies(String currentSpecies) {
        this.currentSpecies = currentSpecies;
    }
}
