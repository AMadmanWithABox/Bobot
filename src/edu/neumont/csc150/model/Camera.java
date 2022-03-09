package edu.neumont.csc150.model;

import com.github.sarxos.webcam.*;
import com.github.sarxos.webcam.util.jh.JHFlipFilter;
import edu.neumont.csc150.view.BobotUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;


public class Camera implements WebcamMotionListener, WebcamImageTransformer {

    private final int VIEW_SIZE_HEIGHT = WebcamResolution.VGA.getHeight();
    private final int VIEW_SIZE_WIDTH = WebcamResolution.VGA.getWidth();
    private final int MOTION_INTERVAL = 500;
    private final BufferedImageOp FILTER = new JHFlipFilter(6);
    private static WebcamPanel panel;
    private static WebcamMotionDetector md;
    private List<Object> detectedObjects = new ArrayList<>();
    private BobotUI bobotUI = new BobotUI();
    private boolean cameraStarted = false;
    private boolean motionDetectionStarted = false;
    private Webcam myWebcam;
    MotorController bobotController = new MotorController();

    /**
     * This method returns the panel that the camara is displayed on, for use in other methods
     *
     * @return
     */
    public WebcamPanel getPanel() {
        return panel;
    }

    /**
     * This method returns the motion detector for use in other methods
     *
     * @return
     */
    public WebcamMotionDetector getMd() {
        return md;
    }

    /**
     * When called this method creates a camera as well as the Jframe panel and window
     *
     * @param camera
     */
    public void createCamera(int camera) throws MalformedURLException {

        myWebcam = Webcam.getWebcams().get(camera);
        myWebcam.close();
        myWebcam.setViewSize(WebcamResolution.VGA.getSize());
        myWebcam.open();


        myWebcam.setImageTransformer(this);

        md = new WebcamMotionDetector(Webcam.getWebcams().get(camera));
        JFrame window = new JFrame("Robot View");
        panel = new WebPanel(Webcam.getWebcams().get(camera), md);

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

    }

    /**
     * Starts motion detection
     */
    public void startDetection(int camera) {
        motionDetectionStarted = true;
        md = new WebcamMotionDetector(Webcam.getWebcams().get(camera));
        md.setInterval(MOTION_INTERVAL); // one check per 500 ms
        md.addMotionListener(this);
        md.clearInertia();
        md.start();
    }

    /**
     * Stops camera
     */
    public void stopCamera(){
        myWebcam.close();
    }

    /**
     * This method when called starts the motion detection for the specified duration
     *
     * @param duration
     */
    public void startDetectionForDuration(int duration) {

        motionDetectionStarted = true;
        md.setInterval(MOTION_INTERVAL); // one check per 500 ms
        md.addMotionListener(this);
        md.clearInertia();
        md.start();

        for (int i = 0; i < duration; i++) ;

        stopDetection();

        motionDetectionStarted = false;

    }

    /**
     * This method when called stops the motion detection
     */
    public void stopDetection() {
        md.stop();
        motionDetectionStarted = false;
    }

    /**
     * When called this method triggers a Webcam Motion Event, this method is called at the interval set by the motion detector
     *
     * @param webcamMotionEvent
     */
    @Override
    public void motionDetected(WebcamMotionEvent webcamMotionEvent) {

        addDetectedObject(webcamMotionEvent.getPreviousImage(), webcamMotionEvent.getCog().y, webcamMotionEvent.getCog().x, webcamMotionEvent.getArea());

        String xDirection = getXDirectionOfMovement(webcamMotionEvent, webcamMotionEvent.getCog().getLocation().x);

        Timer timer = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.repaint();
            }
        });


        for (int i = 0; i < 10000; i++) {

        }

        switch(xDirection){
            case "Left":
                bobotController.turnLeft();
                bobotController.goForward();
                break;

            case "Right":
                bobotController.turnRight();
                bobotController.goForward();
                break;
        }

    }

    /**
     * This method returns the direction where the movement is on the x-axis (Left or Right)
     *
     * @param webcamMotionEvent
     * @param x
     * @return
     */
    public String getXDirectionOfMovement(WebcamMotionEvent webcamMotionEvent, double x) {
        if (x > VIEW_SIZE_WIDTH / 2) {
            return "Right";
        } else {
            return "Left";
        }
    }

    /**
     * This method returns the direction where the movement is on the y-axis (Top or Bottom)
     *
     * @param webcamMotionEvent
     * @param y
     * @return
     */
    public String getYDirectionOfMovement(WebcamMotionEvent webcamMotionEvent, double y) {
        if (y > VIEW_SIZE_HEIGHT / 2) {
            return "Bottom";
        } else {
            return "Top";
        }
    }

    /**
     * This method returns x position of the moving object
     *
     * @return
     */
    public int getMovementXPOS() {
        return md.getMotionCog().x;
    }

    /**
     * This method returns the y position of the moving object
     *
     * @return
     */
    public int getMovementYPOS() {
        return md.getMotionCog().y;
    }

    /**
     * This method sets the interval of when the camera will check for motion, is measured in milliseconds
     *
     * @param motionInterval
     */
    public void setMotionInterval(int motionInterval) {
        if (motionInterval < 100) {
            bobotUI.printIntervalToLow();
        }
    }

    /**
     * This method gets all the connected cameras and prints them to console
     */
    public void getAllConnectedCameras() {

        List<Webcam> webcamList;
        webcamList = Webcam.getWebcams();
        bobotUI.printAllCameras(webcamList);
    }

    /**
     * This method adds a new Object to the detected objects list whenever a new object is detected
     *
     * @param image
     * @param y
     * @param x
     * @param area
     */
    public void addDetectedObject(BufferedImage image, int y, int x, double area) {

        Object d = new Object();
        d.setObjectImage(image);
        d.setXPos(x);
        d.setYPos(y);
        d.setArea(area);
        detectedObjects.add(d);
    }

    /**
     * Returns if the camera has been started
     *
     * @return
     */
    public boolean getCameraStarted() {
        return cameraStarted;
    }

    /**
     * Sets the camara as started or not
     *
     * @param cameraStarted
     */
    public void setCameraStarted(boolean cameraStarted) {
        this.cameraStarted = cameraStarted;
    }

    /**
     * Returns weather the motion detection is on
     *
     * @return
     */
    public boolean getMotionDetectionStarted() {
        return motionDetectionStarted;
    }

    /**
     * Sets the motion detector as on or off
     *
     * @param motionDetectionStarted
     */
    public void setMotionDetectionStarted(boolean motionDetectionStarted) {
        this.motionDetectionStarted = motionDetectionStarted;
    }

    /**
     * This flips the image input
     * @param bufferedImage
     * @return
     */
    @Override
    public BufferedImage transform(BufferedImage bufferedImage) {
        return FILTER.filter(bufferedImage,null);
    }
}
