//package Parkeersimulator;
import java.util.LinkedList;
import java.util.Queue;

public class CarQueue {
    private Queue<Car> queue = new LinkedList<>();

    /**
     * Adds a car to the queue and returns the queue.
     *
     * @param car   Car to add to the queue.
     * @return  Queue with the car added.
     */
    public boolean addCar(Car car) {
        return queue.add(car);
    }

    /**
     * Removes a car from the head of the queue and returns the queue.
     *
     * @return  Queue
     */
    public Car removeCar() {
        return queue.poll();
    }

    /**
     * Returns amounts of car in the queue.
     *
     * @return  Size of queue.
     */
    public int carsInQueue(){
    	return queue.size();
    }
}
