package view;

import javax.swing.JFrame;
import model.*;
/**
 * Created by Timothy on 6-2-2017.
 */

public abstract class AbstractView extends JFrame{
    protected Model model;
    public AbstractView(Model model) {
        this.model = model;
        model.addView(this);
    }

    public Model getModel() {
        return model;
    }

    public void updateView(){
        repaint();
    }
}
