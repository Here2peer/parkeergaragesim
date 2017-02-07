//package Parkeersimulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class SimulatorView extends JFrame {
    private CarParkView carParkView;
    private JButton buttonz;
    private int numberOfFloors;
    private int numberOfRows;
    private int numberOfPlaces;
    private int numberOfOpenSpots;
    private int numberOfOpenReservedSpots;
    private Car[][][] cars;

    /**
     * Constructor for the SimulatorView class.
     *
     * @param numberOfFloors Number of floors in the car park
     * @param numberOfRows   Number of rows per floor
     * @param numberOfPlaces Number of places per row
     */
    public SimulatorView(int numberOfFloors, int numberOfRows, int numberOfPlaces) {
        this.numberOfFloors = numberOfFloors;
        this.numberOfRows = numberOfRows;
        this.numberOfPlaces = numberOfPlaces;
        this.numberOfOpenSpots = (numberOfFloors - 1) * numberOfRows * numberOfPlaces;
        this.numberOfOpenReservedSpots = 1 * numberOfRows * numberOfPlaces;

        cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];

        carParkView = new CarParkView();
        buttonz = new JButton("Click Me! :D");

        Container contentPane = getContentPane();
        contentPane.add(carParkView, BorderLayout.NORTH);
        contentPane.add(buttonz, BorderLayout.SOUTH);


        pack();
        setVisible(true);

        updateView();

    }

    /**
     * Updates the car park view.
     */
    public void updateView() {
        carParkView.updateView();
    }

    /**
     * @return Number of floors in the car park
     */
    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    /**
     * @return Number of rows per floor
     */
    public int getNumberOfRows() {
        return numberOfRows;
    }

    /**
     * @return Number of places per row
     */
    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    /**
     * @return Number of open spots in the car park
     */
    public int getNumberOfOpenSpots() {
        return numberOfOpenSpots;
    }

    /**
     * @return Number of open reserved spots in the car park
     */
    public int getNumberOfOpenReservedSpots() {
        return numberOfOpenReservedSpots;
    }

    /**
     * Returns a car on the given location. If no car is found, returns null.
     *
     * @param location Location to be checked.
     * @return Car in given location. If no car is found, returns null.
     */
    public Car getCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        return cars[location.getFloor()][location.getRow()][location.getPlace()];
    }

    /**
     * Tries to park a given car in a given spot. If spot is taken or invalid, returns false.
     *
     * @param location Parking spot where the car is trying to park.
     * @param car      Car that is trying to park.
     * @return Returns false if parking spot is invalid or alraedy taken, true if car successfully parked.
     */
    public boolean setCarAt(Location location, Car car) {
        if (!locationIsValid(location)) {
            return false;
        }
        Car oldCar = getCarAt(location);
        if (oldCar == null) {
            cars[location.getFloor()][location.getRow()][location.getPlace()] = car;
            car.setLocation(location);
            if (car.getHasReserved()) {
                numberOfOpenReservedSpots--;
            } else {
                numberOfOpenSpots--;
            }
            return true;
        }
        return false;
    }

    /**
     * Tries to remove a car from a given spot and returns the car. If spot is empty or invalid, returns null.
     *
     * @param location Location to remove a car from
     * @return Returns null if spot is invalid or already empty, returns the car if process was succesful
     */
    public Car removeCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        Car car = getCarAt(location);
        if (car == null) {
            return null;
        }
        cars[location.getFloor()][location.getRow()][location.getPlace()] = null;
        car.setLocation(null);
        if (car.getHasReserved()) {
            numberOfOpenReservedSpots++;
        } else {
            numberOfOpenSpots++;
        }
        return car;
    }

    /**
     * @return First free location in the car park.
     */
    public Location getFirstFreeLocation() {
        for (int floor = 0; floor < getNumberOfFloors() - 1; floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    if (getCarAt(location) == null) {
                        return location;
                    }
                }
            }
        }
        return null;
    }

    /**
     * @return First free reserved location in the car park.
     */
    public Location getFirstFreeReservedLocation() {
        for (int floor = 2; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    if (getCarAt(location) == null) {
                        return location;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Runs through all parking spots and checks if it is occupied. If so, it checks if the car has to leave yet and if the car is not currently not paying.
     *
     * @return Returns first car leaving. If no cars are found, returns null.
     */
    public Car getFirstLeavingCar() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = getCarAt(location);
                    if (car != null && car.getMinutesLeft() <= 0 && !car.getIsPaying()) {
                        return car;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Runs through all parking spots. If occupied, gives the car 1 tick, making the cars minutes left go down by 1.
     */
    public void tick(double turnoverTotal) {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = getCarAt(location);
                    if (car != null) {
                        car.tick();
                    }
                }
            }
        }

        this.setTitle(String.valueOf(turnoverTotal));
    }

    /**
     * Checks if location is within the given bounds of the car park.
     *
     * @param location Location to have its validity checked
     * @return False if location is invalid, true if location is valid.
     */
    private boolean locationIsValid(Location location) {
        int floor = location.getFloor();
        int row = location.getRow();
        int place = location.getPlace();
        if (floor < 0 || floor >= numberOfFloors || row < 0 || row > numberOfRows || place < 0 || place > numberOfPlaces) {
            return false;
        }
        return true;
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
            } else {
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
            for (int floor = 0; floor < getNumberOfFloors() - 1; floor++) {
                for (int row = 0; row < getNumberOfRows(); row++) {
                    for (int place = 0; place < getNumberOfPlaces(); place++) {
                        Location location = new Location(floor, row, place);
                        Car car = getCarAt(location);
                        Color color = car == null ? Color.decode("#E2E2E2") : car.getColor();
                        drawPlace(graphics, location, color);
                    }
                }
            }

            // Hardcoded, could be done differently
            for (int floor = 2; floor < getNumberOfFloors(); floor++) {
                for (int row = 0; row < getNumberOfRows(); row++) {
                    for (int place = 0; place < getNumberOfPlaces(); place++) {
                        Location location = new Location(floor, row, place);
                        Car car = getCarAt(location);
                        Color color = car == null ? Color.decode("#C71585") : car.getColor();
                        drawPlace(graphics, location, color);
                    }
                }
            }

            {

            }
            repaint();
        }

        /**
         * Paint a place on this car park view in a given color.
         */
        private void drawPlace(Graphics graphics, Location location, Color color) {
            graphics.setColor(color);
            graphics.fillRect(
                    location.getFloor() * 260 + (1 + (int) Math.floor(location.getRow() * 0.5)) * 75 + (location.getRow() % 2) * 20,
                    60 + location.getPlace() * 10,
                    20 - 1,
                    10 - 1); // TODO use dynamic size or constants
        }
    }

    /**
     * Creating a class where the buttons are made
     */


    // public class ViewButton extends JPanel
    //   implements ActionListener {
    //protected JButton button1, button2;
//
    // public ViewButton() {

    /**
     * below the buttons are named, and the action comands are set
     */

    //  button1 = new JButton("Add 1 minute");
    //  button1.setMnemonic(KeyEvent.VK_D);
    //   button1.setActionCommand("Add 1 minute");

    //   button2 = new JButton("Add 100 minutes");
    //  button2.setMnemonic(KeyEvent.VK_D);
    // button2.setActionCommand("Add 100 minutes");

    //  button1.addActionListener(this);
    //  button2.addActionListener(this);
/*
* Explanation of the tools
 */
    //  button1.setToolTipText("Click this button to add 1 minute.");
    //  button2.setToolTipText("Click this button to add 100 minutes.");
    //  }
/*
* What every single button does
 */
    //     public void actionPerformed(ActionEvent e) {
    //       if ("Add 1 minute".equals(e.getActionCommand())) {
    //       tick(1);
    // }
    //   if ("Add 100 minutes".equals(e.getActionCommand())) {
    //   tick(100);
    // }

    //}
    //  }


    public class ButtonView extends JPanel
            implements ActionListener {
        private JButton oneminute;
        private JButton hundredminutes;


        public ButtonView() {
            oneminute = new JButton("One Minute");
            hundredminutes = new JButton("Hundred Minutes");

            setLayout(null);

            oneminute.addActionListener(this);
            add(oneminute);

            hundredminutes.addActionListener(this);
            add(hundredminutes);

        }

        public void actionPerformed(ActionEvent e) {
            if ("onenimute".equals(e.getActionCommand())) {
                tick(1);
            }
            if ("Hundredminutes".equals(e.getActionCommand())) {
                tick(100);

            }
        }
    }
}
