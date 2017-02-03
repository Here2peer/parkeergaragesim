//package Parkeersimulator;

import java.util.Random;
import java.awt.*;

public class ParkingPassCar extends Car {
	private static final Color COLOR=Color.blue;

    /**
     * Constructor for the ParkingPassCar class. Car stays between 15 and 195 minutes.
     */
    public ParkingPassCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
        this.setHasReserved(false);
    }

    /**
     * @return  Colour of the car
     */
    public Color getColor(){
    	return COLOR;
    }
}
