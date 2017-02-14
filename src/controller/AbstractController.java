package controller;

/**
 * Created by timothy on 6-2-17.
 */
import javax.swing.*;
import java.awt.event.ActionListener;

import model.Model;

public abstract class AbstractController extends  JPanel {
    protected Model model;

    public AbstractController(Model model) {
        this.model=model;
    }

}
