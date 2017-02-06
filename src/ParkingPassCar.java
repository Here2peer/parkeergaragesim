package src;//package Parkeersimulator;

import java.util.Random;
import java.awt.*;

public class ParkingPassCar extends Car {
	private static final Color COLOR=Color.blue;

    /**
     * Constructor for the src.ParkingPassCar class. src.Car stays between 15 and 195 minutes.
     */
    public ParkingPassCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
    }

    /**
     * @return  Colour of the car
     */
    public Color getColor(){
    	return COLOR;
    }
}
