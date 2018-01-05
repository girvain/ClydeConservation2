
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class FileIO {

    private Scanner scanner;
    private File file; // this is the file that will be used by the program

    FileIO() {
        this.file = new File("ClydeConservatoryData.txt");
    }


    public void writeCages(ArrayList<Cage> cageArrayList) {
        try {
            PrintWriter out = new PrintWriter(new FileWriter(file));
            for (Cage item : cageArrayList) {
                if (cageArrayList != null) {
                    out.write("cage-name: ");
                    out.write(item.getCageNumber() + "\n");
                    out.write("\tcage-limit: ");
                    out.write(item.getCageLimit() + "\n");
                    out.write("\tcurrent-species: ");
                    out.write(item.getCurrentSpecies() + "\n");
                    writeAnimals(item.getAnimalTreeMap(), out);
                    writeKeepers(item.getKeeperTreeMap(), out);
                }
            }
            out.flush(); // Flush the buffer!!!
        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error saving course to file");
        }
    }
    public void writeAnimals(TreeMap<String, Animal> array, PrintWriter out) {
        try {
            for (String animal : array.keySet()
                    ){
                if (animal != null) { // the null check is necessary because it is a regular array, not arrayList
                    out.write("\tAnimal \n");
                    out.write("\t\tanimal-ID: ");
                    out.write(array.get(animal).getAnimalId()+ "\n");
                    out.write("\t\tname: ");
                    out.write(array.get(animal).getName()+ "\n");
                    out.write("\t\tsex: ");
                    out.write(array.get(animal).getSex()+ "\n");
                    out.write("\t\tspecies: ");
                    out.write(array.get(animal).getSpecies()+ "\n");
                }
            }
            out.flush();
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error saving to file");
        }

    }

    public void writeKeepers(TreeMap<String, Keeper> array, PrintWriter out) {
        try {
            for (String keeper : array.keySet()){
                if (keeper != null) { // the null check is necessary because it is a regular array, not arrayList
                    out.write("\tKeeper \n");
                    out.write("\t\tkeeper-ID: ");
                    out.write(array.get(keeper).getKeeperId()+ "\n");
                    out.write("\t\tname: ");
                    out.write(array.get(keeper).getName()+ "\n");
                }
            }
            out.flush();
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error saving to file");
        }

    }


    /**
     * takes the CourseList class as input. Uses a Scanner
     * with the default space as the delimiter to iterate through the text file,
     * then initializes the objects accordingly to the CourseList class.
     * @param cageList
     */
    public void readData(CageList cageList) {
        try {
            Scanner scanner = new Scanner(new FileReader(file));
            Cage cage = null; // global course object for the other if else statements to access to add objects to
            while(scanner.hasNext()) {
                // This is the course that the scanner is currently on. It's global for else if statements to access
                String currentWord = scanner.next();
                if (currentWord.equals("cage-name:")) {
                    // added the substring to get rid of the space at the start of the course-name
                    String cageName = scanner.nextLine().substring(1);
                    cage = new Cage();
                    cage.setCageNumber(cageName);

                    if (scanner.next().equals("cage-limit:")) {
                        int cageLimit = scanner.nextInt();
                        cage.setCageLimit(cageLimit);
                    }
                    // this might not work!!!
                    if (scanner.next().equals("current-species")) {
                        String currentSpecies = scanner.nextLine();
                        cage.setCurrentSpecies(currentSpecies);
                    }

                    cageList.addCage(cage);// adds the course to courses, but still have reference to it
                    // until a new cage object is created.
                }
                else if (currentWord.equals("Animal")) {
                    Animal animal = new Animal();
                    if (scanner.next().equals("animal-ID:")) {
                        String animalID = scanner.nextLine().substring(1);
                        animal.setAnimalId(animalID);// change the String to int
                    }
                    if (scanner.next().equals("name:")) {
                        String name = scanner.nextLine().substring(1);
                        animal.setName(name);
                    }
                    if (scanner.next().equals("sex:")) {
                        String sex = scanner.nextLine().substring(1);
                        animal.setSex(sex);
                    }
                    if (scanner.next().equals("species:")) {
                        String sex = scanner.nextLine().substring(1);
                        animal.setSpecies(sex);
                    }
                    cage.addAnimal(animal); // add the animal to the cages
                }
                else if (currentWord.equals("Keeper")) {
                    Keeper keeper = new Keeper();
                    if (scanner.next().equals("keeper-ID:")) {
                        String keeperId = scanner.nextLine().substring(1);
                        keeper.setKeeperID(keeperId);
                    }
                    if (scanner.next().equals("name:")) {
                        String keeperName = scanner.nextLine().substring(1);
                        keeper.setName(keeperName);
                    }
                    cage.addKeeper(keeper);
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}

