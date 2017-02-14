package view;

import javax.swing.JFrame;

import model.Model;

/**
 * Created by Timothy on 6-2-2017.
 */

public abstract class AbstractView extends JFrame{
    protected Model model;

    public AbstractView(Model model) {
        this.model = model;
    }

    public Model getModel(){
        return model;
    }

    public void updateView(){
        repaint();
    }
}
