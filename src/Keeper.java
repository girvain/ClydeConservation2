public class Keeper {

    private String keeperId;
    private String name;
    private int cageCount = 0;

    public void idGenerator() {
        double rand = 0;
        while (rand < 1000) {
            rand = Math.random()* 10000;
        }
        int number = (int)rand;

        keeperId = "KP" + number;
    }

    public void displayKeeperDetails() {
        System.out.println("Keeper ID: " + getKeeperId());
        System.out.println("Name: " + getName());
    }

    public String getKeeperId() {
        return keeperId;
    }

    public void setKeeperID(String keeperID) {
        this.keeperId = keeperID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCageCount() {
        return cageCount;
    }

    public void setCageCount(int cageCount) {
        this.cageCount = cageCount;
    }

    public void increaseCAgeCount() {
        cageCount += 1;
    }
}
