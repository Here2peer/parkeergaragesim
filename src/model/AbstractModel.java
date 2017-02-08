package model;

import java.util.ArrayList;
import java.util.List;

import view.*;
/**
 * Created by timothy on 6-2-17.
 */

public abstract class AbstractModel {
    private List<AbstractView> views;

    public AbstractModel() {
        views = new ArrayList<AbstractView>();
    }

    public void addView(AbstractView view) {
        views.add(view);
    }

    public void notifyViews(){
        for(AbstractView v : views) v.updateView();
    }

}
