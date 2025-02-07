/*
 * Course: CSC1110 131
 * Fall 2023
 * Lab 8 - ParkingLots
 * Name: Andrew Keenan
 * Created: 10-17-23
 */
package keenana;

/**
 * describes the literal parking lot object
 */
public class ParkingLot {
    /**
     * describes that the closed threshold is 80% full
     */
    public static final double CLOSED_THRESHOLD = 80;
    private final String name;
    private int numCars;
    private final int capacity;
    private int timestamp1;
    private int totalMinsClosed;
    private int currentTime;

    /**
     * constructor for the parking lot
     * @param capacity total number of cars the parking lot can fit
     */
    public ParkingLot(int capacity){
        name = "test";
        this.capacity = capacity;
    }

    /**
     * constructor for the parking lot
     * @param name name of the parking lot
     * @param capacity total number of cars the parking lot can fit
     */
    public ParkingLot(String name, int capacity){
        this.name = name;
        this.capacity = capacity;
    }

    /**
     * displays the capacity of the parking lot and the number of
     * cars inside of the parking lot
     */
    public void displayStatus(){
        System.out.println("The capacity is "+capacity);
        System.out.println("There are "+numCars+" cars in the lot");
    }
    public int getMinutesClosed(){
        return totalMinsClosed;
    }

    public String getName() {
        return name;
    }
    public int getNumberOfSpotsRemaining(){
        return capacity - numCars;
    }

    /**
     * returns the percent full of the parking lot
     * @return the percent as a double
     */
    public double getPercentFull(){
        final int converter = 100;
        return ((double)numCars / (double)capacity) * converter;
    }

    /**
     * determines whether the parking lot is closed based off of the percent
     * full and the closed threshold of 80%
     * @return boolean value if the parking lot is closed
     */
    public boolean isClosed(){
        return getPercentFull() >= CLOSED_THRESHOLD;
    }

    /**
     * marking when a vehicle enters the lot
     * @param timestamp the time at which the vehicle enters
     */
    public void markVehicleEntry(int timestamp){
        boolean wasClosed = isClosed();
        if (currentTime == 0){
            currentTime = timestamp;
        }
        if (timestamp - currentTime >= 0){
            numCars++;
            currentTime = timestamp;
        }
        if (isClosed() && !wasClosed){
            timestamp1 = timestamp;
        }
    }

    /**
     * marks when a vehicle leaves the parking lot
     * @param timestamp time at which the vehicle leaves
     */
    public void markVehicleExit(int timestamp){
        boolean wasClosed = isClosed();
        if (currentTime == 0){
            currentTime = timestamp;
        }
        if (timestamp - currentTime >= 0){
            numCars--;
            currentTime = timestamp;
        }
        int timestamp2;
        if (wasClosed && !isClosed()){
            timestamp2 = timestamp;
            totalMinsClosed += timestamp2 - timestamp1;
        }
    }
}
