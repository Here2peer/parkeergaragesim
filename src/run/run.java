package run;

/**
 * Created by timothy on 7-2-17.
 */

import model.Simulator;

public class run {
        public static void main(String[] args) {
            Thread thread = new Thread(new Simulator());
            thread.start();

        }
}
