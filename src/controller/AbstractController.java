package controller;

/**
 * Created by timothy on 6-2-17.
 */
import model.*;
import javax.swing.*;

public abstract class AbstractController extends JPanel{
    protected Model model;
    public AbstractController (Model model) {
        this.model = model;
    }

}
