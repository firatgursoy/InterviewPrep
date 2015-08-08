package parkingLot;

import java.util.ArrayList;
import java.util.List;

/**
 * Size: Compact, Large, MotorCycle
 * Vehicle
 *      + getSize
 *      + getNumberOfSpotsNeeded
 *      + clearSpots
 *      + 
 * Car is a Vehicle
 * Motorcycle is a Vehicle
 * Bus is a Vehicle
 * 
 * ParkingSpot
 *      + isAvailable
 *      + canFitVehicle
 *      + parkVehicle
 *      + removeVehicle
 *      
 * Level
 *      + getAvailableSpots
 *      + parkVehicle
 *      - findAvailableSpotNumber
 *      - 
 *      
 * ParkingLot
 */

enum Size {
    Compact,
    Large, 
    Motorcycle
};

abstract class Vehicle {
    protected Size size;
    protected int numberOfSpotsNeeded;
    protected String licensePlate;
    protected List<ParkingSpot> spots = new ArrayList<>();
    
    public Vehicle(String license) {
        this.licensePlate = license;
    }
    
    public abstract boolean canFitIntoSpot(ParkingSpot spot);
    
    public void parkIntoSpot(ParkingSpot spot) {
        this.spots.add(spot);
    }
    
    public void clearSpots() {
        for (ParkingSpot parkingSpot : spots) {
            parkingSpot.removeVehicle();
        }
        
        this.spots.clear();
    }
    
    public int getNumberOfSpotsNeeded() {
        return numberOfSpotsNeeded;
    }

    @Override
    public String toString() {
        return "Vehicle [size=" + size + ", numberOfSpotsNeeded=" + numberOfSpotsNeeded + ", licensePlate="
                        + licensePlate + "]";
    }
}

class Motorcycle extends Vehicle {
    public Motorcycle(String license) {
        super(license);
        this.size = Size.Motorcycle;
        this.numberOfSpotsNeeded = 1;
    }
    
    @Override
    public boolean canFitIntoSpot(ParkingSpot spot) {
        return true;
    }
}

class Car extends Vehicle {
    public Car(String license) {
        super(license);
        this.size = Size.Compact;
        this.numberOfSpotsNeeded = 1;
    }

    @Override
    public boolean canFitIntoSpot(ParkingSpot spot) {
        return spot.getSize() == Size.Compact || spot.getSize() == Size.Large;
    }
}

class Bus extends Vehicle {
    public Bus(String license) {
        super(license);
        this.numberOfSpotsNeeded = 5;
        this.size = Size.Large;
    }

    @Override
    public boolean canFitIntoSpot(ParkingSpot spot) {
        return spot.getSize() == Size.Large;
    }
}

class ParkingSpot {
    private Level level;
    private int rowNumber;
    private int spotNumber;
    private Vehicle vehicle;
    private Size size;
    
    public ParkingSpot(Level level, int row, int spotNumber, Size size) {
        this.level = level;
        this.rowNumber = row;
        this.spotNumber = spotNumber;
        this.size = size;
    }
    
    public boolean isAvailable() {
        return vehicle == null;
    }
    
    public boolean canFitIntoSpot(Vehicle veh) {
        return isAvailable() && veh.canFitIntoSpot(this);
    }
    
    public boolean parkVehicle(Vehicle veh) {
        if (!canFitIntoSpot(veh)) {
            return false;
        }
        
        this.vehicle = veh;
        this.vehicle.parkIntoSpot(this);
        return true;
    }
    
    public void removeVehicle() {
        this.vehicle = null;
        //notify vehicle is removed;
    }
    
    public Size getSize() {
        return size;
    }
    
    public int getRowNumber() {
        return rowNumber;
    }
    
    public int getSpotNumber() {
        return spotNumber;
    }
    
    public Level getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return "ParkingSpot [level=" + level + ", rowNumber=" + rowNumber + ", spotNumber=" + spotNumber + ", vehicle="
                        + vehicle + ", size=" + size + "]";
    }
}

class Level {
    private final static int SPOTS_PER_ROW = 10;
    
    private int levelNumber;
    private List<ParkingSpot[]> spotsByRow = new ArrayList<>();
    private int availableSpots = 0;
    
    public Level(int levelNumber, int motorCycleRows, int largeSpotsRows, int compactSpotRows) {
        this.levelNumber = levelNumber;
        
        int row = 0;
        for(int i = 0; i < largeSpotsRows; i++) {
            spotsByRow.add(createParkingSpots(SPOTS_PER_ROW, Size.Large, row++));
        }
        
        for(int i = 0; i < compactSpotRows; i++) {
            spotsByRow.add(createParkingSpots(SPOTS_PER_ROW, Size.Compact, row++));
        }
        
        for(int i = 0; i < motorCycleRows; i++) {
            spotsByRow.add(createParkingSpots(SPOTS_PER_ROW, Size.Motorcycle, row++));
        }
        
        this.availableSpots = (motorCycleRows * SPOTS_PER_ROW) +
                              (largeSpotsRows * SPOTS_PER_ROW) +
                              (compactSpotRows * SPOTS_PER_ROW);
    }
    
    public boolean parkVehicle(Vehicle veh) {
        int spotsNeeded = veh.getNumberOfSpotsNeeded();
        if (getAvailableSpots() < spotsNeeded) {
            return false;
        }
        
        int[] location = findSpotFor(veh);
        boolean result = true;
        
        if (location != null && location.length == 2) {
            int row = location[0];
            int spotNumber = location[1];
            
            ParkingSpot[] parkingSpots = spotsByRow.get(row);
            for (int i = spotNumber; i < parkingSpots.length && i < spotsNeeded + spotNumber; i++) {
                ParkingSpot spot = parkingSpots[i];
                result &= spot.parkVehicle(veh);
            }
            
            return result;
        }
        
        return false;
    }
    
    public int[] findSpotFor(Vehicle veh) {
        int[] spotFound = null;
        int spotsNeeded = veh.getNumberOfSpotsNeeded();
        
        for (ParkingSpot[] spot : spotsByRow) {
            int spotsFound = 0;
            for (ParkingSpot item : spot) {
                if (item.canFitIntoSpot(veh)) {
                    spotsFound++;
                } else {
                    spotsFound = 0;
                }
                
                if (spotsFound == spotsNeeded) {
                    availableSpots -= spotsNeeded;
                    if (spotsNeeded > 1) {
                        return new int[]{item.getRowNumber(), item.getSpotNumber() - (spotsNeeded - 1)};
                    }
                    return new int[]{item.getRowNumber(), item.getSpotNumber()};
                }
            }
        }
        
        return spotFound;
    }
    
    private ParkingSpot[] createParkingSpots(int spots, Size size, int row) {
        ParkingSpot[] parking = new ParkingSpot[spots];
        
        for(int i = 0; i < parking.length; i++) {
            parking[i] = new ParkingSpot(this, row, i, size);
        }
        
        return parking;
    }
    
    public int getAvailableSpots() {
        return this.availableSpots;
    }
    
    public int getLevelNumber() {
        return levelNumber;
    }
}


public class ParkingLot {

    public static void main(String args[]) {
        Vehicle car = new Car("1");
        Vehicle bus = new Bus("2");
        Level level = new Level(1, 1, 3, 5);
        
        boolean carResult = level.parkVehicle(car);
        boolean busResult = level.parkVehicle(bus);
        
        System.out.println(busResult);
    }
}
