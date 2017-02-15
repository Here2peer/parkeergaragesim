package main;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

import controller.SimulatorController;
import model.Model;
import view.AbstractView;
import view.CarParkView;

/**
 * Created by Timothy on 6-2-2017.
 */

public class CarParkMain {
    private JFrame screen;
    private Model model;
    private AbstractView carparkview;
    private JButton stop;

    private SimulatorController controller;

    public CarParkMain() {
        model = new Model();
        controller = new SimulatorController(model);
        carparkview = new CarParkView(model);
        stop = new JButton("Stop Simulation");
        screen = new JFrame("MVC Garage");
        screen.setSize(900, 500);
        screen.setResizable(true);

        screen.getContentPane().add(carparkview, BorderLayout.NORTH);
        screen.getContentPane().add(controller, BorderLayout.WEST);
        screen.getContentPane().add(stop, BorderLayout.WEST);
        stop.setVisible(false
        );
        screen.setVisible(true);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        model.start();
    }

}
