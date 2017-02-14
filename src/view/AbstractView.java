package view;

import javax.swing.JPanel;

import model.Model;

/**
 * Created by Timothy on 6-2-2017.
 */

public abstract class AbstractView extends JPanel{
    protected Model model;

    public AbstractView(Model model) {
        this.model = model;
        model.addView(this);
    }

    public Model getModel(){
        return model;
    }

    public void updateView(){
        repaint();
    }
}
