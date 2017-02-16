package view;

import java.awt.Color;
import java.awt.*;
import javax.swing.*;
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

        int hours = model.calcHours();
        String hourz = String.valueOf(hours);
        int minutes = model.calcMinutes();
        String minutez = String.valueOf(minutes);
        int days = model.calcDays();

        if(hours < 10) {
            hourz = "0" + hourz;
        }

        if(minutes < 10) {
            minutez = "0" + minutez;
        }

//        System.out.println("Current date: day " + days + ", " + hourz + ":" + minutez);

        JLabel infoDate = new JLabel("Current date: ");
        add(infoDate);

        JLabel date = new JLabel("day " + days + ", " + hourz + ":" + minutez);
        add(date);

        // Occupation rate
        JLabel infoPlaces = new JLabel("Total number of parking spots: ");
        add(infoPlaces);

        JLabel numberPlaces = new JLabel(String.valueOf(model.getNumberOfTotalPlaces()));
        add(numberPlaces);

        JLabel infoFreePlaces = new JLabel("Total number of free parking spots: ");
        add(infoFreePlaces);

        JLabel numberFreePlaces = new JLabel(String.valueOf(model.getNumberOfFreePlaces()));
        add(numberFreePlaces);

        JLabel infoOccupationRate = new JLabel("Occupation rate: ");
        add(infoOccupationRate);

        JLabel numberOccupationRate = new JLabel(String.valueOf(model.getOccupationRate()) + "%");
        add(numberOccupationRate);

        setVisible(true);

        repaint();
    }
}
