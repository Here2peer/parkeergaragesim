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
public class CarParkView extends AbstractView {

    private Dimension size;
    private Image carParkImage;
    private Model model;

    /**
     * Constructor for objects of class CarPark
     */
    public CarParkView(Model model) {
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
        if (carParkImage == null) {
            return;
        }

        Dimension currentSize = getSize();
        if (size.equals(currentSize)) {
            g.drawImage(carParkImage, 0, 0, null);
        }
        else {
            // Rescale the previous image.
            g.drawImage(carParkImage, 0, 0, currentSize.width, currentSize.height, null);
        }
    }

    /**
     * Create a new car park image if the size has changed.
     * Goes through all parking spot and draws it according to if it is occupied, and if so, according to the type of the occupying car.
     */
    public void updateView() {
        if (!size.equals(getSize())) {
            size = getSize();
            carParkImage = createImage(size.width, size.height);
        }
        Graphics graphics = carParkImage.getGraphics();
        graphics.clearRect(0, 0, 800, 600);
        for(int floor = 0; floor < model.getNumberOfFloors()-1; floor++) {
            for(int row = 0; row < model.getNumberOfRows(); row++) {
                for(int place = 0; place < model.getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = model.getCarAt(location);
                    Color color = car == null ? Color.decode("#E2E2E2") : car.getColor();
                    drawPlace(graphics, location, color);
                }
            }
        }

        // Hardcoded, could be done differently
        for(int floor = 2; floor < model.getNumberOfFloors(); floor++) {
            for(int row = 0; row < model.getNumberOfRows(); row++) {
                for(int place = 0; place < model.getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = model.getCarAt(location);
                    Color color = car == null ? Color.decode("#C71585") : car.getColor();
                    drawPlace(graphics, location, color);
                }
            }
        }

        graphics.setColor(Color.black);
        int hours = model.calcHourOfDay();
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

        int a = 75;
        int b = a + 100;
        int c = 400;

        graphics.drawString("Current date: ", a, c);
        graphics.drawString(hourz + ":" + minutez + ", day " + days, b, c);

        graphics.drawString("Total revenue: ", a, c + 15);
        graphics.drawString("€" + String.valueOf((int)model.getRoundTurnover()), b, c + 15);

        graphics.drawString("Total spots: ", a, c + 45);
        graphics.drawString(String.valueOf(model.getNumberOfTotalPlaces()), b, c + 45);

        graphics.drawString("Free spots: ", a, c  + 60);
        graphics.drawString(String.valueOf(model.getNumberOfFreePlaces()), b, c + 60);

        graphics.drawString("Occupied spots: ", a, c + 75);
        graphics.drawString(String.valueOf(model.getNumberOfOccupiedPlaces()), b, c + 75);

        graphics.drawString("Occupation rate: ", a, c + 90);
        graphics.drawString(String.valueOf(model.getOccupationRate()) + "%", b, c + 90);

        graphics.drawString("Normal cars: ", a, c + 120);
        graphics.drawString(String.valueOf(model.getNumberOfNormalCars()), b, c + 120);

        graphics.drawString("Reserved cars: ", a, c + 135);
        graphics.drawString(String.valueOf(model.getNumberOfReservedCars()), b, c + 135);

        graphics.drawString("Pass holders: ", a, c + 150);
        graphics.drawString(String.valueOf(model.getNumberOfParkingCars()), b, c + 150);

        repaint();
    }

    /**
     * Paint a place on this car park view in a given color.
     */
    private void drawPlace(Graphics graphics, Location location, Color color) {
        graphics.setColor(color);
        graphics.fillRect(
                location.getFloor() * 260 + (1 + (int)Math.floor(location.getRow() * 0.5)) * 75 + (location.getRow() % 2) * 20,
                60 + location.getPlace() * 10,
                20 - 1,
                10 - 1); // TODO use dynamic size or constants
    }
}
