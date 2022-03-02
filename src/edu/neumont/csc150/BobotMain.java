package edu.neumont.csc150;

import edu.neumont.csc150.model.Camera;
import edu.neumont.csc150.view.BobotFrame;

public class BobotMain {
    public static void main(String[] args) {
        BobotFrame frame = new BobotFrame();
        frame.frameStuff();
        Camera bobotCam = new Camera();
        bobotCam.run();
    }
}
