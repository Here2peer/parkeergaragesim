//package Parkeersimulator;

import java.util.Random;
import java.awt.*;

/**
 * Represents an average car, coloured red in the simulation.
 */
public class AdHocCar extends Car {
	private static final Color COLOR=Color.decode("#FF8800");

    /**
     * Constructor for the AdHocCar class. Car stays between 15 and 195 minutes.
     */
    public AdHocCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(true);
        this.setHasReserved(false);
    }

    /**
     * @return Colour of the car
     */
    public Color getColor(){
    	return COLOR;
    }
}
