package view;

import javax.swing.*;
import java.awt.*;

import model.*;

public class SimulatorView extends AbstractView {
    private CarParkView carParkView;
    private int numberOfFloors;
    private int numberOfRows;
    private int numberOfPlaces;
    private int numberOfOpenSpots;
    private int numberOfOpenReservedSpots;
    private Car[][][] cars;

    /**
     * Constructor for the SimulatorView class.
     *
     * @param numberOfFloors    Number of floors in the car park
     * @param numberOfRows      Number of rows per floor
     * @param numberOfPlaces    Number of places per row
     */
    public SimulatorView() {
        this.numberOfFloors = 3;
        this.numberOfRows = 6;
        this.numberOfPlaces = 30;
        this.numberOfOpenSpots = (numberOfFloors - 1) * numberOfRows * numberOfPlaces;
        this.numberOfOpenReservedSpots = 1 * numberOfRows * numberOfPlaces;

        cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];

        carParkView = new CarParkView();

        Container contentPane = getContentPane();
        contentPane.add(carParkView, BorderLayout.CENTER);
        pack();
        setVisible(true);

        updateView();
    }

    /**
     * View
     */
    public void setTitle(double turnoverTotal) {
        String text = String.format("%.2f", (double)turnoverTotal);
        this.setTitle(text);
    }

    private class CarParkView extends JPanel {

        private Dimension size;
        private Image carParkImage;

        /**
         * Constructor for objects of class CarPark
         */
        public CarParkView() {
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
         * Controller (??)
         */
        public void updateView() {
            if (!size.equals(getSize())) {
                size = getSize();
                carParkImage = createImage(size.width, size.height);
            }
            Graphics graphics = carParkImage.getGraphics();
            for(int floor = 0; floor < getNumberOfFloors()-1; floor++) {
                for(int row = 0; row < getNumberOfRows(); row++) {
                    for(int place = 0; place < getNumberOfPlaces(); place++) {
                        Location location = new Location(floor, row, place);
                        Car car = getCarAt(location);
                        Color color = car == null ? Color.decode("#E2E2E2") : car.getColor();
                        drawPlace(graphics, location, color);
                    }
                }
            }

            // Hardcoded, could be done differently
            for(int floor = 2; floor < getNumberOfFloors(); floor++) {
                for(int row = 0; row < getNumberOfRows(); row++) {
                    for(int place = 0; place < getNumberOfPlaces(); place++) {
                        Location location = new Location(floor, row, place);
                        Car car = getCarAt(location);
                        Color color = car == null ? Color.decode("#C71585") : car.getColor();
                        drawPlace(graphics, location, color);
                    }
                }
            }
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

}