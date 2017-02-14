package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;

import model.CarQueue;
import model.Model;


/**
 * Created by timothy on 6-2-17.
 */

public class SimulatorController extends AbstractController implements ActionListener {
    private JButton start;

    public SimulatorController(Model model){
        super(model);
        start.setBounds(229, 10 ,70, 30);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==start) {
            model.start();
        }
    }

}
