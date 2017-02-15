package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.Model;


/**
 * Created by timothy on 6-2-17.
 */

public class SimulatorController extends AbstractController implements ActionListener {

    private JButton start;
    private JButton stop;

    public SimulatorController(Model model) {
        super(model);
        /**
         * *knopnaam* = new JButton("Knopnaam")
         */
        setSize(100,600);
        start = new JButton("Start Simulation");
        start.addActionListener(this);
        stop = new JButton("Pauze Simulation");
        stop.addActionListener(this);

        /**
         * add(*KNOPNAAM*)
         * Zo voeg je een knop toe
         */
        this.setLayout(null);
        add(start);
        add(stop);
        start.setBounds(0, 20, 110, 50);
        stop.setBounds(0, 70, 110, 50);

        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == start) {
            model.start();

        }

        if (e.getSource() == stop) {
            model.tick(0);

        }
            /*
            threads?
          */


        }
    }


