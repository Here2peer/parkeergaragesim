package main;

import java.awt.BorderLayout;

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

    private SimulatorController controller;

    public CarParkMain() {
        model = new Model();
        controller = new SimulatorController(model);
        carparkview = new CarParkView(model);
        screen = new JFrame("MVC Garage");
        screen.setSize(800, 600);
        screen.setResizable(false);

        screen.getContentPane().add(carparkview, BorderLayout.CENTER);
        screen.getContentPane().add(controller, BorderLayout.SOUTH);
        screen.setVisible(true);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        model.start();
    }

}
