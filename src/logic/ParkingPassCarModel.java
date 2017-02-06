package logic;

import java.awt.Color;
import java.util.Random;

/**
 * Created by Timothy on 6-2-2017.
 */

public class ParkingPassCarModel {
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
