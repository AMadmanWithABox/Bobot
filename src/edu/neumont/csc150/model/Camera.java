package edu.neumont.csc150.model;

import com.github.sarxos.webcam.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Camera implements WebcamMotionListener {

    Webcam myWebcam = Webcam.getWebcams().get(0);
    WebcamMotionDetector md = new WebcamMotionDetector(myWebcam);
    JFrame window = new JFrame("Robot View");
    WebcamPanel panel = new WebcamPanel(myWebcam);

    public void run() {

        panel.setFPSDisplayed(true);
        panel.setDisplayDebugInfo(true);
        panel.setImageSizeDisplayed(true);
        panel.setMirrored(false);
        System.out.println(panel.getAlignmentX());
        System.out.println(panel.getAlignmentY());

        window.add(panel);
        window.setBounds(0,0,320,180);
        window.setResizable(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setLayout(null);
        window.setSize(320,180);

        md.setInterval(100); // one check per 500 ms
        md.addMotionListener(this);
        md.start();
        md.setPixelThreshold(20);
        md.clearInertia();

    }

    @Override
    public void motionDetected(WebcamMotionEvent webcamMotionEvent) {

        System.out.println("movement is on the " + getYDirectionOfMovement(webcamMotionEvent,webcamMotionEvent.getCog().getY()) + " " + getXDirectionOfMovement(webcamMotionEvent, webcamMotionEvent.getCog().getX()));
        Graphics g = panel.getGraphics();
        g.setPaintMode();
        g.setColor(Color.green);
        int x = webcamMotionEvent.getCog().x;
        int y = webcamMotionEvent.getCog().y;
        g.drawOval(x,y,100,100);

    }

    public String getXDirectionOfMovement(WebcamMotionEvent webcamMotionEvent, double x){
        if(x > 160 ){
            return "right";
        }else {
            return "left";
        }
    }

    public String getYDirectionOfMovement(WebcamMotionEvent webcamMotionEvent, double y){
        if (y > 90){
            return "bottom";
        }else{
            return "top";
        }
    }

}
