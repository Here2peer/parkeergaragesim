package model;

import java.util.Random;

import view.SimulatorView;

public class Simulator {

    private static final String AD_HOC = "1";
    private static final String PASS = "2";
    private static final String RESERVED = "3";

    private CarQueue entranceCarQueue;
    private CarQueue entrancePassQueue;
    private CarQueue paymentCarQueue;
    private CarQueue exitCarQueue;
    private SimulatorView simulatorView;

    private int day = 0;
    private int hour = 0;
    private int minute = 0;

    private int tickPause = 100;

    int weekDayArrivals=100; // average number of arriving cars per hour
    int weekendArrivals = 200; // average number of arriving cars per hour
    int weekDayPassArrivals= 50; // average number of arriving cars per hour
    int weekendPassArrivals = 5; // average number of arriving cars per hour
    int weekDayReservedArrivals= 50; // average number of arriving cars per hour
    int weekendReservedArrivals = 5; // average number of arriving cars per hour

    int enterSpeed = 3; // number of cars that can enter per minute
    int paymentSpeed = 7; // number of cars that can pay per minute
    int exitSpeed = 5; // number of cars that can leave per minute

    double turnoverTotal;

    double price;
    double priceReduced;

    public Simulator() {
        entranceCarQueue = new CarQueue();
        entrancePassQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();
        price = 2.4;
        priceReduced = 2.0;
        turnoverTotal = 0.0;
        simulatorView = new SimulatorView(3, 6, 30);
    }

    /**
     * Starts the simulation for 10.000 ticks, each tick representing 1 minute.
     */
    public void run() {
        for (int i = 0; i < 10000; i++) {
            tick();
        }
    }

    /**
     * Progressess the application for 1 minute.
     */
    private void tick() {
        advanceTime();
        handleExit();
        updateViews();
        // Pause.
        try {
            Thread.sleep(tickPause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        handleEntrance();
    }

    /**
     * Advance the time by one minute.
     */
    private void advanceTime(){
        minute++;
        while (minute > 59) {
            minute -= 60;
            hour++;
        }
        while (hour > 23) {
            hour -= 24;
            day++;
        }
        while (day > 6) {
            day -= 7;
        }

    }

    /**
     * Handles cars entering the car park.
     */
    private void handleEntrance(){
        carsArriving();
        carsEntering(entrancePassQueue);
        carsEntering(entranceCarQueue);
    }

    /**
     * Handles cars exiting the car park.
     */
    private void handleExit(){
        carsReadyToLeave();
        carsPaying();
        carsLeaving();
    }

    /**
     * Updates the car park view.
     */
    private void updateViews(){
        simulatorView.tick(turnoverTotal);
        simulatorView.updateView();
    }

    /**
     * Adds arriving cars to their representative queues.
     */
    private void carsArriving(){
        int numberOfCars=getNumberOfCars(weekDayArrivals, weekendArrivals);
        addArrivingCars(numberOfCars, AD_HOC);
        numberOfCars=getNumberOfCars(weekDayPassArrivals, weekendPassArrivals);
        addArrivingCars(numberOfCars, PASS);
        numberOfCars=getNumberOfCars(weekDayReservedArrivals, weekendReservedArrivals);
        addArrivingCars(numberOfCars, RESERVED);
    }

    /**
     * Removes cars from the front of the queue and assigns them to a parking space. After the amount of cars that can enter per minute has been handled, the rest of the queue is dismissed.
     *
     * @param queue Queue at the entrance of the car park.
     */
    private void carsEntering(CarQueue queue){
        int i=0;
        while(queue.carsInQueue() > 0 && i<enterSpeed && ((queue.peekCar().getHasReserved() && simulatorView.getNumberOfOpenReservedSpots() > 0) || (!queue.peekCar().getHasReserved() && simulatorView.getNumberOfOpenReservedSpots() > 0) ) ) {
            if(queue.peekCar().getHasReserved() && simulatorView.getNumberOfOpenReservedSpots() > 0) {
                Car car = queue.removeCar();
                Location freeLocation = simulatorView.getFirstFreeReservedLocation();
                simulatorView.setCarAt(freeLocation, car);
                i++;
            } else if(!queue.peekCar().getHasReserved() && simulatorView.getNumberOfOpenSpots() > 0) {
                Car car = queue.removeCar();
                Location freeLocation = simulatorView.getFirstFreeLocation();
                simulatorView.setCarAt(freeLocation, car);

                if(!car.getHasToPay()) {
                    double priceTemp = priceReduced * (car.getMinutesTotal() / (double) 60);
                    turnoverTotal += priceTemp;
                }

                i++;
            }
        }
     }

    /**
     * Removes cars from parking spots and if necessary, adds cars to the payment queue.
     */
    private void carsReadyToLeave(){
        Car car = simulatorView.getFirstLeavingCar();
        while (car!=null) {
            if (car.getHasToPay()){
                car.setIsPaying(true);
                paymentCarQueue.addCar(car);
            }
            else {
                carLeavesSpot(car);
            }
            car = simulatorView.getFirstLeavingCar();
        }
    }

    /**
     * Processess payment. Cars currently just leave the payment queue and leave their spot.
     */
    private void carsPaying(){
        int i=0;
        while (paymentCarQueue.carsInQueue()>0 && i < paymentSpeed){
            Car car = paymentCarQueue.removeCar();

            double priceTemp = price * (car.getMinutesTotal() / (double)60);
            turnoverTotal += priceTemp;

            carLeavesSpot(car);
            i++;
        }
    }

    /**
     * Cars leave the queue and are removed from the application.
     */
    private void carsLeaving(){
        // Let cars leave.
        int i=0;
        while (exitCarQueue.carsInQueue()>0 && i < exitSpeed){
            exitCarQueue.removeCar();
            i++;
        }
    }

    /**
     * Calculates the amount of cars that are entering this minute, based on the day of the week, the average amount of cars per hour and a random number.
     * Number is rounded down to the closest integer.
     * Varies from Math.round((average number of cars per hour) / 60) to Math.round((average number of cars per hour) * 1,3 / 60)
     *
     * @param weekDay   Average number of cars per hour on a weekday
     * @param weekend   Average number of cars per hour on a weekendday.
     * @return          Number of cars entering this minute.
     */
    private int getNumberOfCars(int weekDay, int weekend){
        Random random = new Random();

        // Get the average number of cars that arrive per hour.
        int averageNumberOfCarsPerHour = day < 5
                ? weekDay
                : weekend;

        // Calculate the number of cars that arrive this minute.
        double standardDeviation = averageNumberOfCarsPerHour * 0.3;
        double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
        return (int)Math.round(numberOfCarsPerHour / 60);
    }

    /**
     * Adds arriving cars to their representative entrance queue.
     *
     * @param numberOfCars  Number of cars entering this minute.
     * @param type          Type of car.
     */
    private void addArrivingCars(int numberOfCars, String type){
        switch(type) {
            case AD_HOC:
                for (int i = 0; i < numberOfCars; i++) {
                    entranceCarQueue.addCar(new AdHocCar());
                }
                break;
            case PASS:
                for (int i = 0; i < numberOfCars; i++) {
                    entrancePassQueue.addCar(new ParkingPassCar());
                }
                break;
            case RESERVED:
                for (int i = 0; i < numberOfCars; i++) {
                    entrancePassQueue.addCar(new ReservedCar());
                }
                break;
        }
    }

    /**
     * Car leaves spot and joins the exit queue.
     *
     * @param car   Car that is leaving his spot.
     */
    private void carLeavesSpot(Car car){
        simulatorView.removeCarAt(car.getLocation());
        exitCarQueue.addCar(car);
    }
}
