package model;

import java.awt.Color;
import java.util.Random;


/**
 * Created by Timothy on 6-2-2017.
 */

public class AdHocCar extends Car {
    private static final Color COLOR = Color.decode("#FF8800");

    /**
     * Constructor for the AdHocCar class. Car stays between 15 and 195 minutes.
     */
    public AdHocCar() {
        Random random = new Random();
        int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setMinutesTotal(stayMinutes);
        this.setHasToPay(true);
        this.setHasReserved(false);
        this.setHasReducedPrice(false);
        ;
    }

    /**
     * @return Colour of the car
     */
    public Color getColor() {
        return COLOR;
    }
}
