package model;

/**
 * Created by Somentus on 08/02/2017.
 */

public class ViewModel {
    public ViewModel() {

    }

    /**
     * Updates the car park view.
     *
     * Model
     */
    public void updateView() {
        carParkView.updateView();
    }

    /**
     * @return  Number of floors in the car park
     */
    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    /**
     * @return  Number of rows per floor
     */
    public int getNumberOfRows() {
        return numberOfRows;
    }

    /**
     * @return  Number of places per row
     */
    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    /**
     * @return  Number of open spots in the car park
     */
    public int getNumberOfOpenSpots(){
        return numberOfOpenSpots;
    }

    /**
     * @return  Number of open reserved spots in the car park
     */
    public int getNumberOfOpenReservedSpots(){
        return numberOfOpenReservedSpots;
    }

    /**
     * Returns a car on the given location. If no car is found, returns null.
     * @param location  Location to be checked.
     * @return          Car in given location. If no car is found, returns null.
     * Model
     */
    public Car getCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        return cars[location.getFloor()][location.getRow()][location.getPlace()];
    }

    /**
     * Tries to park a given car in a given spot. If spot is taken or invalid, returns false.
     *
     * @param location  Parking spot where the car is trying to park.
     * @param car       Car that is trying to park.
     * @return          Returns false if parking spot is invalid or alraedy taken, true if car successfully parked.
     * Model
     */
    public boolean setCarAt(Location location, Car car) {
        if (!locationIsValid(location)) {
            return false;
        }
        Car oldCar = getCarAt(location);
        if (oldCar == null) {
            cars[location.getFloor()][location.getRow()][location.getPlace()] = car;
            car.setLocation(location);
            if(car.getHasReserved()){
                numberOfOpenReservedSpots--;
            } else {
                numberOfOpenSpots--;
            }
            return true;
        }
        return false;
    }

    /**
     * Tries to remove a car from a given spot and returns the car. If spot is empty or invalid, returns null.
     *
     * @param location  Location to remove a car from
     * @return          Returns null if spot is invalid or already empty, returns the car if process was succesful
     * Model
     */
    public Car removeCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        Car car = getCarAt(location);
        if (car == null) {
            return null;
        }
        cars[location.getFloor()][location.getRow()][location.getPlace()] = null;
        car.setLocation(null);
        if(car.getHasReserved()){
            numberOfOpenReservedSpots++;
        } else {
            numberOfOpenSpots++;
        }
        return car;
    }

    /**
     * @return  First free location in the car park.
     * Model
     */
    public Location getFirstFreeLocation() {
        for (int floor = 0; floor < getNumberOfFloors()-1; floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    if (getCarAt(location) == null) {
                        return location;
                    }
                }
            }
        }
        return null;
    }

    /**
     * @return  First free reserved location in the car park.
     * Model
     */
    public Location getFirstFreeReservedLocation() {
        for (int floor = 2; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    if (getCarAt(location) == null) {
                        return location;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Runs through all parking spots and checks if it is occupied. If so, it checks if the car has to leave yet and if the car is not currently not paying.
     *
     * @return  Returns first car leaving. If no cars are found, returns null.
     * Model
     */
    public Car getFirstLeavingCar() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = getCarAt(location);
                    if (car != null && car.getMinutesLeft() <= 0 && !car.getIsPaying()) {
                        return car;
                    }
                }
            }
        }
        return null;
    }


    /**
     * Runs through all parking spots. If occupied, gives the car 1 tick, making the cars minutes left go down by 1.
     * Model
     */
    public void tick() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = getCarAt(location);
                    if (car != null) {
                        car.tick();
                    }
                }
            }
        }

        setTitle(turnoverTotal);
    }

    /**
     * Checks if location is within the given bounds of the car park.
     *
     * @param location  Location to have its validity checked
     * @return          False if location is invalid, true if location is valid.
     * Model
     */
    private boolean locationIsValid(Location location) {
        int floor = location.getFloor();
        int row = location.getRow();
        int place = location.getPlace();
        if (floor < 0 || floor >= numberOfFloors || row < 0 || row > numberOfRows || place < 0 || place > numberOfPlaces) {
            return false;
        }
        return true;
    }
}
