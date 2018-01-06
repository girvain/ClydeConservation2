public class Animal {
    private String animalId;
    private String name;
    private String sex;
    private String species;

    public void displayAnimalDetails() {
        System.out.println("Animal ID: " + getAnimalId());
        System.out.println("Animal name: " + getName());
        System.out.println("Animal species: " + getSpecies());
        System.out.println("Animal sex: " + getSex());
    }

    public void idGenerator() {
        double rand = 0;
        while (rand < 1000) {
            rand = Math.random()* 10000;
        }
        int number = (int)rand;
        String id = "";
        if (species != null) {
            id = species.substring(0, 2).toUpperCase();
        }
        animalId = id + number;
    }

    public String getAnimalId() {
        return animalId;
    }

    public void setAnimalId(String animalId) {
        this.animalId = animalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }
}
