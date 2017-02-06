package src.logic;

/**
 * Created by timothy on 6-2-17.
 */
import javax.swing.*;

import src.Simulator;
import src.controller.AbstractController;

@SuppressWarnings("Serial")
public abstract class AbstractModel extends JPanel{
    protected SimulatorLogic simulator;

    public AbstractController(SimulatorLogic simulator){
        this.simulator = simulator;
    }
}
