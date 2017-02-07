package logic;

import java.awt.Color;
import java.util.Random;

/**
 * Created by Timothy on 6-2-2017.
 */

public class AdHocCar {
public AdHocCar() {
    Random random = new Random();
    int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
    this.setMinutesLeft(stayMinutes);
    this.setHasToPay(true);
}

    /**
     * @return Colour of the car
     */
    public Color getColor(){
        return COLOR;
    }
}
