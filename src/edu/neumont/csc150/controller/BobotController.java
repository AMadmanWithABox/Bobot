package edu.neumont.csc150.controller;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import edu.neumont.csc150.model.Camera;
import edu.neumont.csc150.model.SSHConnection;
import edu.neumont.csc150.view.BobotUI;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;

import java.io.IOException;

public class BobotController {

    /**
     * When called this runs all robot functions and UI
     * @throws IOException
     */
    public void run() throws Exception {

        BobotUI bobotUI = new BobotUI();

//      BobotFrame frame = new BobotFrame();
//      frame.frameStuff();
//
        Camera bobotCam = new Camera();
        bobotCam.createCamera(0);

        bobotCam.getAllConnectedCameras();



    }

}
