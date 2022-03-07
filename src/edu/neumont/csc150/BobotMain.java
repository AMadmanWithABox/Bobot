package edu.neumont.csc150;

import edu.neumont.csc150.controller.BobotController;
import java.io.IOException;

public class BobotMain {
    /**
     * This is main mathod that calls run to start the robot originally named bobot
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws Exception {

        BobotController bobotController = new BobotController();
        bobotController.run();
    }
}
