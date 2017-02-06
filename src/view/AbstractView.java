package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * Created by Timothy on 6-2-2017.
 */

public abstract class AbstractView extends JPanel{
    public AbstractView() {

    }

    public void updateView() {
        if (!size.equals(getSize())) {
            size = getSize();
            carParkImage = createImage(size.width, size.height);
        }
        Graphics graphics = carParkImage.getGraphics();
        for(int floor = 0; floor < getNumberOfFloors(); floor++) {
            for(int row = 0; row < getNumberOfRows(); row++) {
                for(int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = getCarAt(location);
                    Color color = car == null ? Color.white : car.getColor();
                    drawPlace(graphics, location, color);
                }
            }
        }
        repaint();
    }

}
