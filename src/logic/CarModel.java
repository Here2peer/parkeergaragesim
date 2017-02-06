package logic;

import java.awt.Color;

/**
 * Created by Timothy on 6-2-2017.
 */

public class CarModel {

    private Location location;
    private int minutesLeft;
    private boolean isPaying;
    private boolean hasToPay;

    /**
     * Constructor for objects of class src.Car
     */
    public Car() {

    }

    /**
     * Returns location of the car.
     *
     * @return src.Location of the car
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets location of the car to the input.
     *
     * @param location  Wanted new location of the car
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Returns the minutes left that the car will stay in the garage.
     *
     * @return  Minutes left
     */
    public int getMinutesLeft() {
        return minutesLeft;
    }

    /**
     * Sets the minutes left that the car will stay in the garage.
     * @param minutesLeft   Minutes left
     */
    public void setMinutesLeft(int minutesLeft) {
        this.minutesLeft = minutesLeft;
    }

    /**
     * @return  Is the car currently paying?     */
    public boolean getIsPaying() {
        return isPaying;
    }

    /**
     * @param isPaying  Is the car currently paying?
     */
    public void setIsPaying(boolean isPaying) {
        this.isPaying = isPaying;
    }

    /**
     * @return  Did the car pay yet?
     */
    public boolean getHasToPay() {
        return hasToPay;
    }

    /**
     * @param hasToPay  Did the car pay yet?
     */
    public void setHasToPay(boolean hasToPay) {
        this.hasToPay = hasToPay;
    }

    /**
     * Removes 1 minute from the minutes left.
     */
    public void tick() {
        minutesLeft--;
    }

    public abstract Color getColor();
}
