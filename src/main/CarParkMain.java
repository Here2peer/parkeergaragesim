package main;

import model.Model;
import view.SimulatorView;

/**
 * Created by Timothy on 6-2-2017.
 */

public class CarParkMain {
    public CarParkMain() {
        Model model = new Model(3, 6, 30);
        SimulatorView observer = new SimulatorView(model);
        model.setObserver(observer);
        model.run();
    }
}
