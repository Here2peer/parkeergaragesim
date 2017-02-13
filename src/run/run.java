package run;

/**
 * Created by timothy on 7-2-17.
 */

import model.Model;
import view.SimulatorView;

public class run {
        public static void main(String[] args) {

            Model model = new Model(3, 6, 30);
            SimulatorView observer = new SimulatorView(model);
            model.setObserver(observer);
            model.run();
        }
}
