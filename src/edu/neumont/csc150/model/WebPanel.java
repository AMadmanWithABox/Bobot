package edu.neumont.csc150.model;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamMotionDetector;
import com.github.sarxos.webcam.WebcamPanel;

import javax.swing.*;
import java.awt.*;

public class WebPanel extends WebcamPanel {
    private int x;
    private int y;

    private WebcamMotionDetector md;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public WebPanel(Webcam webcam, WebcamMotionDetector md) {
        super(webcam);
        this.md = md;
    }

    public void paint(Graphics g) {
        super.paint(g);

        // draw oval
        g.setColor(Color.green);

        x = md.getMotionCog().getLocation().x;
        y = md.getMotionCog().getLocation().y;

        g.drawOval(640 - x, y,100,100);

    }
}
