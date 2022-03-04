package edu.neumont.csc150.model;

import com.github.sarxos.webcam.*;
import com.github.sarxos.webcam.ds.cgt.WebcamCloseTask;
import com.github.sarxos.webcam.ds.cgt.WebcamReadBufferTask;
import com.github.sarxos.webcam.ds.ipcam.IpCamDriver;
import com.github.sarxos.webcam.util.ImageUtils;
import com.sun.source.util.TaskEvent;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.connection.channel.direct.Session;
import net.schmizz.sshj.connection.channel.direct.SessionChannel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Camera implements WebcamMotionListener {

    WebcamPanel panel;
    WebcamMotionDetector md;

    public void run() throws IOException {

        Webcam myWebcam = Webcam.getDefault();
        myWebcam.close();
        myWebcam.setViewSize(WebcamResolution.VGA.getSize());
        myWebcam.open();
        JFrame window = new JFrame("Robot View");
        md = new WebcamMotionDetector(Webcam.getDefault());
        panel = new WebcamPanel(Webcam.getDefault());

        panel.setFPSDisplayed(true);
        panel.setDisplayDebugInfo(true);
        panel.setImageSizeDisplayed(true);
        panel.setMirrored(true);

        window.add(panel);
        window.setResizable(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.pack();
        window.setLayout(null);

        md.setInterval(100); // one check per 500 ms
        md.addMotionListener(this);
        md.start();
        md.clearInertia();
        md.setPixelThreshold(30);

        SSHClient sshClient = new SSHClient();
        sshClient.connect("192.168.37.18", 22);
        sshClient.authPassword("pi","BeepBoop");
        System.out.println(sshClient.getConnection());
        Session.Command exec = sshClient.startSession().exec("python3 motorTest.py");
        sshClient.disconnect();

    }

    @Override
    public void motionDetected(WebcamMotionEvent webcamMotionEvent) {

        Graphics g = panel.getGraphics();
        g.setPaintMode();
        g.setColor(Color.green);
        g.drawOval(md.getMotionCog().getLocation().x,md.getMotionCog().getLocation().y,100,100);

        System.out.println("movement is detected on the " + getYDirectionOfMovement(webcamMotionEvent,webcamMotionEvent.getCog().getLocation().y) + " " + getXDirectionOfMovement(webcamMotionEvent,webcamMotionEvent.getCog().getLocation().x));


    }

    public String getXDirectionOfMovement(WebcamMotionEvent webcamMotionEvent, double x){
        if(x > 320 ){
            return "right";
        }else {
            return "left";
        }
    }

    public String getYDirectionOfMovement(WebcamMotionEvent webcamMotionEvent, double y){
        if (y > 220){
            return "bottom";
        }else{
            return "top";
        }
    }

}
