package edu.neumont.csc150.model;

import com.github.sarxos.webcam.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Camera implements WebcamMotionListener {

    Webcam myWebcam = Webcam.getDefault();
    WebcamMotionDetector md = new WebcamMotionDetector(myWebcam);
    JFrame window = new JFrame("Robot View");
    WebcamPanel panel = new WebcamPanel(myWebcam);
    public void run() {

        myWebcam.setViewSize(WebcamResolution.VGA.getSize());

        panel.setFPSDisplayed(true);
        panel.setDisplayDebugInfo(true);
        panel.setImageSizeDisplayed(true);
        panel.setMirrored(true);


        window.add(panel);
        window.setBounds(400,0,1080,1920);
        window.setResizable(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setLayout(null);

        md.setInterval(5000); // one check per 500 ms
        md.addMotionListener(this);
        md.start();
        md.setMaxMotionPoints(10);

    }

    @Override
    public void motionDetected(WebcamMotionEvent webcamMotionEvent) {

        System.out.println("movement is on the " + getYDirectionOfMovement(webcamMotionEvent,webcamMotionEvent.getPoints().get(0).getY()) + " " + getXDirectionOfMovement(webcamMotionEvent, webcamMotionEvent.getPoints().get(0).getX()));
        Graphics g = window.getGraphics();
        g.setPaintMode();
        g.setColor(Color.green);
        g.drawOval((int)webcamMotionEvent.getPoints().get(0).getX(),(int)webcamMotionEvent.getPoints().get(0).getY(),100,100);

    }

    public String getXDirectionOfMovement(WebcamMotionEvent webcamMotionEvent, double x){
        if(x > 320 ){
            return "right";
        }else {
            return "left";
        }
    }

    public String getYDirectionOfMovement(WebcamMotionEvent webcamMotionEvent, double y){
        if (y > 240){
            return "bottom";
        }else{
            return "top";
        }
    }

}
