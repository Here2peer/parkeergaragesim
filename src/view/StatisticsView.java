package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import model.Car;
import model.Location;
import model.Model;

/**
 * Created by timothy on 13-2-17.
 */
public class StatisticsView extends AbstractView {

    private Dimension size;
    private Image statisticsImage;
    private Model model;

    /**
     * Constructor for objects of class CarPark
     */
    public StatisticsView(Model model) {
        super(model);
        this.model = model;
        size = new Dimension(0, 0);
    }


    /**
     * Overridden. Tell the GUI manager how big we would like to be.
     */
    public Dimension getPreferredSize() {
        return new Dimension(800, 500);
    }

    /**
     * Overriden. The car park view component needs to be redisplayed. Copy the
     * internal image to screen.
     */
    public void paintComponent(Graphics g) {
        if (statisticsImage == null) {
            return;
        }

        Dimension currentSize = getSize();
        if (size.equals(currentSize)) {
            g.drawImage(statisticsImage, 0, 0, null);
        }
        else {
            // Rescale the previous image.
            g.drawImage(statisticsImage, 0, 0, currentSize.width, currentSize.height, null);
        }
    }

    /**
     * Create a new car park image if the size has changed.
     * Goes through all parking spot and draws it according to if it is occupied, and if so, according to the type of the occupying car.
     */
    public void updateView() {
        if (!size.equals(getSize())) {
            size = getSize();
            statisticsImage = createImage(size.width, size.height);
        }
        Graphics graphics = statisticsImage.getGraphics();

        // This is where the magic happens


        repaint();
    }
}
