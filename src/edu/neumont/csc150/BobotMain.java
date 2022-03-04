package edu.neumont.csc150;

import edu.neumont.csc150.model.Camera;
import edu.neumont.csc150.view.BobotFrame;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.connection.channel.direct.Session;

import java.io.IOException;

public class BobotMain {
    public static void main(String[] args) throws IOException {
//        BobotFrame frame = new BobotFrame();
//        frame.frameStuff();
        Camera bobotCam = new Camera();
        bobotCam.run();


    }
}
