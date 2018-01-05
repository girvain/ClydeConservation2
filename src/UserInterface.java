import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface{

    private CageList cageList;
    private Scanner scanner;
    private Validate validate;
    private FileIO fileIO;

    UserInterface() {
        cageList = new CageList();
        scanner = new Scanner(System.in);
        validate = new Validate();
        fileIO = new FileIO();
        fileIO.readData(cageList); // Call the readData method to initialize the objects of stored data
    }

    /**
     * This method is to be used between the user and the system. It prints a list of
     * options and takes input which determines what methods will be called.
     * @param
     */
    public void interacter(String displayMessage) {
        System.out.println(displayMessage + "\n");

        printMenuList();
        int input = validate.validateInteger();
        //scanner.nextLine(); // clear the rest of the line for next scanner input.

        switch(input) {
            case 1 :
                cageList.printCages();
                break;
            case 2 :
                cageList.addCage(addCageInputLoop());
                break;
            case 3 :
                addAnimalWithChecks();
                break;
            case 4 :
                cageSelector().printAnimals();
                break;
            case 5 :
                cageSelector().addKeeper(addKeeperInputLoop());
                break;
            case 6 :
                cageSelector().printKeepers();
                break;
            case 7 : // The if statements are to get the output from the remove methods
                removeAnimalLoop();
                break;
            case 8 :
                removeKeeperLoop();
                break;
            case 9 :
                Animal animal = animalSearchInterface();
                if (animal != null) {
                    editAnimalDetails(animal);
                }
                break;
        }

        //fake recursion
        if (input > 10 || input < 1) {
            interacter("Invalid option, enter another choice");
        }
        else if (input == 10) {
            saveData();
            System.out.println("Goodbye");
            scanner.close();
        }
        else
            interacter("\n" + "Process complete, enter another option or press 10 to exit");
    }

    public void printMenuList() {
        System.out.println("1- List available cages");
        System.out.println("2- Add cage to Zoo");
        System.out.println(("3- Add Animal to cage"));
        System.out.println("4- List the animals in a cage");
        System.out.println("5- Add keepers to a cage");
        System.out.println("6- List keepers of a cage");
        System.out.println("7- Remove an animal from a cage");
        System.out.println("8- Remove a keeper of a cage");
        System.out.println("9- Edit Animal details");
        System.out.println("10- Exit");
    }

    /**
     * This method takes user input and seraches for a match in the course array.
     * It then returns the course object if it's a match, otherwise it keeps looping.
     * MAYBE i SHOULD FIX THE LOOP!
     * @return
     */
    public Cage cageSelector() {
        //Scanner scanner = new Scanner(System.in);
        Cage matchingCage;
        while (true) {
            System.out.println("Please enter a cage?");
            // search the courses array for a match
            matchingCage = cageList.cageSearch(scanner.nextLine());
            // if the user input is a match from the cageList then
            // matching course will contain a cage object.
            if (matchingCage != null) {
                break;
            }else
                System.out.println("That's not a registered cage. Try again.");
        }
        return matchingCage;
    }

    /**
     * A method to create a loop and pass input to the makeStudent() method in the
     * Course class. Returns a student object with the data input by the user on the
     * CMD-line.
     * SHOULD THIS BE IT'S OWN CLASS?
     */
    public Animal createAnimalInputLoop() {

        String name = "";
        String sex = ""; // variables to pass input
        String species = "";
        String animalId = "";
        // / the loop will run and collect all the data in sequential order
        while (true) {
            boolean noErrors = true; // boolean to identify if there has been an input error

            try {
                System.out.println("Enter name:");
                name = scanner.nextLine();
                System.out.println("Enter sex:");
                sex = scanner.nextLine();
                //scanner.nextLine(); // to avoid the skipping of the next scanner input
                System.out.println("Enter species:");
                species = scanner.nextLine();
                //scanner.nextLine();
            }catch (InputMismatchException e) {
                System.out.println("Please enter the the right format");
                noErrors = false; // change noError state to false coz error occurred
            }

            if (noErrors) {
                break; // loop will only break if error is true
            }
        }
        // finally, make the student and then return it.
        Animal animal = makeAnimal(name, sex, species);
        return animal;
    }

    public void addAnimalWithChecks() {
        Cage cage = cageSelector(); // get a cage object
        Animal animal = createAnimalInputLoop(); // create an animal
        if (!cage.speciesCheck(animal)) {
            System.out.println("Animal is not the same species as the cage");
        }
        else if (cage.isFull()) {
            System.out.println("Animal cage is currently full");
        }
        else {
            cage.addAnimal(animal);
            System.out.println("Animal added successfully");
        }
    }

    /**
     * This is the same as the above method but it is specific to the subject object.
     * @return Subject
     */

    public Keeper addKeeperInputLoop() {
        String nameOfKeeper = "";
        // / the loop will run and collect all the data in sequential order
        while (true) {
            boolean noErrors = true; // boolean to identify if there has been an input error

            try {
                System.out.println("Enter name of the Keeper:");
                nameOfKeeper = scanner.nextLine();
            }catch (InputMismatchException e) {
                System.out.println("Please enter the the right format");
            }

            if (noErrors) {
                break; // loop will only break if error is true
            }
        }
        Keeper keeper = new Keeper();
        keeper.idGenerator();
        keeper.setName(nameOfKeeper);
        return keeper;
    }

    /**
     * Method to make creating a student quicker and more readable.
     * returns student object.
     */
    public Animal makeAnimal(String name, String sex, String species) {
        Animal animal = new Animal();
        animal.setName(name);
        animal.setSex(sex);
        animal.setSpecies(species);
        animal.idGenerator(); //IMPORTANT: generate this after object is made so species is not null
        return animal;
    }

    public void editAnimalDetailsMenuDisplay() {
        System.out.println("1- Edit animal ID");
        System.out.println("2- Edit animal name");
        System.out.println("3- Edit animal sex");
        System.out.println("4- Exit edit animal details");
    }

    /**
     * Method to take an animal as input and edit one or all of its attributes. Will loop
     * until exited.
     * @param animal
     */
    public void editAnimalDetails(Animal animal) {
        editAnimalDetailsMenuDisplay();
        int input = validate.validateInteger();

        switch (input) {
            case 1 :
                System.out.println("Enter new animal ID");
                animal.setAnimalId(scanner.nextLine());
                break;
            case 2 :
                System.out.println("Enter new animal name");
                animal.setName(scanner.nextLine());
                break;
            case 3 :
                System.out.println("Enter new Sex");
                animal.setSex(scanner.nextLine());
                break;
//            case 4 :
//                animal.setSpecies(scanner.nextLine());
//                break;
        }
        //fake recursion
        if (input > 4 || input < 1) {
            System.out.println("invalid choice, try again");
            editAnimalDetails(animal);
        }
        else if (input == 4) {
            System.out.println("Exiting animal editing");
            //scanner.close();
        }
        else
            editAnimalDetails(animal);
    }

    /**
     * Method for creating a loop interface to take user input to search for an animal. IT
     * starts by selecting the right cage, then uses the search method from the cage class
     * that returns either the string ref to the animal in treemap, or null. If it matches
     * it will get the animal from the map then return it, otherwise is will loop until it
     * gets a match or the user types exit.
     */
    public Animal animalSearchInterface() {
        Cage cage = cageSelector(); // get the cage
        while (true) {
            System.out.println("Enter animal ID to search for animal");
            String input = scanner.nextLine();
            String animalRef = cage.searchAnimal(input);
            if (animalRef != null) {
                Animal animal = cage.getAnimalTreeMap().get(animalRef); // get the animal if its there
                return animal;
            }
            else if (input.toLowerCase().equals("exit")) {
                break; // end the loop coz the user typed exit
            }
            else
                System.out.println("Animal not in cage, try again or type exit");
        }
        return null;
    }



    /**
     * Method for creating a cage
     */
    public Cage addCageInputLoop() {
        String nameOfCage = "";
        int cageLimit = 0;
        // / the loop will run and collect all the data in sequential order
        while (true) {
            boolean noErrors = true; // boolean to identify if there has been an input error

            try {
                System.out.println("Enter name or number of the cage:");
                nameOfCage = scanner.nextLine();
                System.out.println("Enter the limit of animals for the cage:");
                cageLimit = validate.validateInteger();
            }catch (InputMismatchException e) {
                System.out.println("Please enter the the right format");
            }

            if (noErrors) {
                break; // loop will only break if error is true
            }
        }
        Cage cage = new Cage();
        cage.setCageNumber(nameOfCage);
        cage.setCageLimit(cageLimit);
        return cage;
    }

    public void removeAnimalLoop() {
        Cage cage = cageSelector();
        String animalId = "";
        if (cage != null) {
            System.out.println("Enter animal ID");
            animalId = scanner.nextLine();
            if (cage.searchAnimal(animalId) != null) {
                cage.removeAnimal(animalId);
                System.out.println("Animal removed successfully");
            }
            else
                System.out.println("Animal not in the cage");
        }

    }



    public void removeKeeperLoop() {
        Cage cage = cageSelector();
        String keeperId = "";
        if (cage != null) {
            System.out.println("Enter keeper ID");
            keeperId = scanner.nextLine();
            if (cage.searchKeeper(keeperId) != null) {
                cage.removeKeeper(keeperId);
                System.out.println("Keeper removed successfully");
            }
            else
                System.out.println("Keeper not allocated to the cage");
        }

    }

    /**
     * Method to save the current state of the program to a text file. Uses the FileIO
     * class methods and a state variable to know if the state of the program has been
     * changes (edited).
     */
    public void saveData() {
        fileIO.writeCages(cageList.getCagesArray()); // uses the getter() to get a ref to courses array
    }


}
