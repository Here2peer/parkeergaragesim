package view;

import java.awt.*;

import model.Model;

public class SimulatorView extends AbstractView {
    private CarParkView carParkView;
    private Model model;

    public SimulatorView(Model model) {

        carParkView = new CarParkView(this, model);

        Container contentPane = getContentPane();
        contentPane.add(carParkView, BorderLayout.CENTER);
        pack();
        setVisible(true);

        updateView();
    }

    public void setModel(Model model){
        this.model = model;
    }

    /**
     * Updates the car park view.
     */
    public void updateView() {
        carParkView.updateView();
    }


}