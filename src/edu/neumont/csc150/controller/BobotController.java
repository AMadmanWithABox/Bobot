package edu.neumont.csc150.controller;

import edu.neumont.csc150.model.Camera;
import edu.neumont.csc150.view.BobotFrame;
import edu.neumont.csc150.view.BobotUI;
import java.io.IOException;

public class BobotController {

    BobotUI bobotUI =new BobotUI();

    /**
     * When called this runs all robot functions and UI
     */
    public void run() {

        BobotFrame frame = new BobotFrame();

        boolean followMode = true;
        boolean controllerMode = false;
        boolean keepLooping = true;

        try {
            do {

                bobotUI.displayMenu();
                int selection = bobotUI.getUserInt(1, 6);
                Camera bobotCam = new Camera();
                BobotUI bobotUI = new BobotUI();

                switch (selection) {
                    case 1: // 1. start camera

                        bobotCam.createCamera(1);

                        break;
                    case 2: // 2. start motion detection(stays on till ended)

                        if (!bobotCam.getMotionDetectionStarted()) {
                            bobotCam.startDetection(1);
                        } else {
                            bobotUI.motionDetectionOn();
                        }
                        do {

                        }
                        while (!bobotUI.getEnd());

                        bobotCam.stopDetection();


                        break;
                    case 3: // 3. detect motion for duration

                        int duration = bobotUI.getUserInt(1, 100000000);

                        do {

                            bobotCam.startDetectionForDuration(duration);

                        } while (bobotUI.getEnd());

                        break;
                    case 4: // 4. set robot mode

                        bobotUI.displayRobotMode();

                        selection = bobotUI.getUserInt(1, 2);

                        switch (selection) {

                            case 1:

                                followMode = true;

                                controllerMode = false;

                                bobotUI.printFollowRobotMode();

                                break;
                            case 2:

                                followMode = false;

                                controllerMode = true;

                                frame.frameStuff();

                                bobotUI.printControllerRobotMode();

                                break;
                        }

                        break;
                    case 5: // 5. Exit

                        bobotCam.stopDetection();
                        bobotCam.stopCamera();
                        bobotUI.printGoodbye();
                        keepLooping = false;

                        break;
                }
            } while (keepLooping);
        }catch(Exception ex){
            bobotUI.printError(ex);
        }
    }
}
