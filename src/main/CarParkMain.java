package main;

import javax.swing.JFrame;

import model.Model;
import view.SimulatorView;

/**
 * Created by Timothy on 6-2-2017.
 */

public class CarParkMain {
    private JFrame screen;
    private Model model;

    public CarParkMain() {
        model = new Model();
        SimulatorView observer = new SimulatorView(model);
        model.setObserver(observer);
        model.run();
    }
}
