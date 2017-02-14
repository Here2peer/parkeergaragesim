package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.Model;


/**
 * Created by timothy on 6-2-17.
 */

public class SimulatorController extends AbstractController implements ActionListener {

    public SimulatorController(Model model){
        super(model);
        /**
         * *knopnaam* = new JButton("Knopnaam")
         */
        this.setLayout(null);

        /**
         * add(*KNOPNAAM*)
         * Zo voeg je een knop toe

        */
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

    }

}
