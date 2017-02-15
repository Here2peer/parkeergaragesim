package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Model;


/**
 * Created by timothy on 6-2-17.
 */

public class SimulatorController extends AbstractController implements ActionListener {

    private JButton start;

    public SimulatorController(Model model) {
        super(model);
        setSize(200,300);
        /**
         * *knopnaam* = new JButton("Knopnaam")
         */
        start = new JButton("Start Simulation");
        start.addActionListener(this);
        this.setLayout(null);
        /**
         * add(*KNOPNAAM*)
         * Zo voeg je een knop toe
         */
        add(start);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if ("start".equals(e.getActionCommand())) {
            /*
            threads?
          */


        }
    }
}


