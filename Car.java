//package Parkeersimulator;

import java.awt.*;

public abstract class Car {

    private Location location;
    private int minutesLeft;
    private int minutesTotal;
    private boolean isPaying;
    private boolean hasToPay;
    private boolean hasReserved;
    private boolean hasReducedPrice;

    /**
     * Constructor for objects of class Car
     */
    public Car() {

    }

    /**
     * Returns location of the car.
     *
     * @return Location of the car
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
     * Returns the total time that the car will stay in the garage.
     *
     * @return  Minutes left
     */
    public int getMinutesTotal() {
        return minutesTotal;
    }

    /**
     * Sets the total time that the car will stay in the garage.
     * @param minutesTotal  Total time that car stays in spot
     */
    public void setMinutesTotal(int minutesTotal) {
        this.minutesTotal = minutesTotal;
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
     * @return  Is the car currently paying?
     */
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
     * @return  Did the customer reserve a parking spot?
     */
    public boolean getHasReserved() {
        return hasReserved;
    }

    /**
     * @param hasReserved   Did the customer reserve a parking spot?
     */
    public void setHasReserved(boolean hasReserved) {
        this.hasReserved = hasReserved;
    }

    /**
     * @return  Does the car get a reduced price?
     */
    public boolean getHasReducedPrice() {
        return hasReducedPrice;
    }

    /**
     * @param hasReducedPrice   Does the car get a reduced price?
     */
    public void setHasReducedPrice(boolean hasReducedPrice) {
        this.hasReducedPrice = hasReducedPrice;
    }

    /**
     * Removes 1 minute from the minutes left.
     */
    public void tick() {
        minutesLeft--;
    }
    
    public abstract Color getColor();
}