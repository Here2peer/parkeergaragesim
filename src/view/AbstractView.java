package view;

import javax.swing.JFrame;
import model.*;
/**
 * Created by Timothy on 6-2-2017.
 */

public abstract class AbstractView extends JFrame{
    public AbstractView() {
    }

    public void updateView(){
        repaint();
    }
}
