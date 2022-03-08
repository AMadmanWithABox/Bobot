package edu.neumont.csc150.model;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamMotionDetector;
import com.github.sarxos.webcam.WebcamPanel;

import java.awt.*;

public class WebPanel extends WebcamPanel {
    private int x;
    private int y;

    private WebcamMotionDetector md;

    /**
     * Sets the x-position of the detected object
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the y-position of the detected object
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * This constuctor sets the webcam and the motionDetector it overwrites the standard webPanel.
     * @param webcam
     * @param md
     */
    public WebPanel(Webcam webcam, WebcamMotionDetector md) {
        super(webcam);
        this.md = md;
    }

    /**
     * This method is called to repaint the screen with every new detection.
     * @param g
     */
    public void paint(Graphics g) {
        super.paint(g);

        // draw oval
        g.setColor(Color.green);

        x = md.getMotionCog().getLocation().x;
        y = md.getMotionCog().getLocation().y;

        g.drawOval(640 - x, y,100,100);
    }
}
