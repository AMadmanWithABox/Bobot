package edu.neumont.csc150.model;

import com.github.sarxos.webcam.*;
import com.jcraft.jsch.JSchException;
import edu.neumont.csc150.view.BobotUI;
import net.schmizz.sshj.connection.ConnectionException;
import net.schmizz.sshj.transport.TransportException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Camera implements WebcamMotionListener {

    private static WebcamPanel panel;
    private static WebcamMotionDetector md;
    private final int MOTION_INTERVAL = 500;
    private final int VIEW_SIZE_HEIGHT = WebcamResolution.VGA.getHeight();
    private final int VIEW_SIZE_WIDTH = WebcamResolution.VGA.getWidth();
    private List<Object> detectedObjects = new ArrayList<>();
    private BobotUI bobotUI = new BobotUI();

    /**
     * This method returns the panel that the camara is displayed on, for use in other methods
     * @return
     */
    public WebcamPanel getPanel() {
        return panel;
    }

    /**
     * This method returns the motion detector for use in other methods
     * @return
     */
    public WebcamMotionDetector getMd() {
        return md;
    }

    /**
     * When called this method creates a camera as well as the Jframe panel and window
     * @param camara
     */
    public void createCamera(int camara) {

        Webcam myWebcam = Webcam.getWebcams().get(camara);
        myWebcam.close();
        myWebcam.setViewSize(WebcamResolution.VGA.getSize());
        myWebcam.open();
        JFrame window = new JFrame("Robot View");
        md = new WebcamMotionDetector(Webcam.getDefault());
        panel = new WebPanel(Webcam.getDefault(), md);

        panel.setFPSDisplayed(true);
        panel.setDisplayDebugInfo(true);
        panel.setImageSizeDisplayed(true);
        panel.setMirrored(true);

        Timer timer = new Timer(30, new ActionListener() {
            @Override
           public void actionPerformed(ActionEvent e) {
                panel.repaint();
           }
        });


        window.add(panel);
        window.setResizable(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.pack();
        window.setLayout(null);

        md.setInterval(MOTION_INTERVAL); // one check per 500 ms
        md.addMotionListener(this);
        md.start();
        md.clearInertia();
        md.setPixelThreshold(50);

    }

    /**
     * When called this method triggers a Webcam Motion Event, this method is called at the interval set by the motion detector
     * @param webcamMotionEvent
     */
    @Override
    public void motionDetected(WebcamMotionEvent webcamMotionEvent) {

        Graphics g = panel.getGraphics();
        g.setPaintMode();

        SSHConnection sshConnection = new SSHConnection();
        try {
            sshConnection.connectToSSHServer("192.168.137.18","pi","BeepBoop",22);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            sshConnection.commandSSHClient("python3 motorTest.py");
        } catch (TransportException e) {
            e.printStackTrace();
        } catch (ConnectionException e) {
            e.printStackTrace();
        } catch (JSchException e) {
            e.printStackTrace();
        }
        try {
            sshConnection.sshDisconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //g.setColor(Color.green);
        //g.drawOval(md.getMotionCog().getLocation().x,md.getMotionCog().getLocation().y,100,100);

        addDetectedObject(webcamMotionEvent.getPreviousImage(),webcamMotionEvent.getCog().y,webcamMotionEvent.getCog().x,webcamMotionEvent.getArea());

       bobotUI.printMovementLocation(getYDirectionOfMovement(webcamMotionEvent,webcamMotionEvent.getCog().getLocation().y), getXDirectionOfMovement(webcamMotionEvent,webcamMotionEvent.getCog().getLocation().x));

       // TimerTask timerTask = TimerTask();
    }




    /**
     * This method returns the direction where the movement is on the x-axis (Left or Right)
     * @param webcamMotionEvent
     * @param x
     * @return
     */
    public String getXDirectionOfMovement(WebcamMotionEvent webcamMotionEvent, double x){
        if(x > VIEW_SIZE_WIDTH/2 ){
            return "Right";
        }else {
            return "Left";
        }
    }

    /**
     * This method returns the direction where the movement is on the y-axis (Top or Bottom)
     * @param webcamMotionEvent
     * @param y
     * @return
     */
    public String getYDirectionOfMovement(WebcamMotionEvent webcamMotionEvent, double y){
        if (y > VIEW_SIZE_HEIGHT/2){
            return "Bottom";
        }else{
            return "Top";
        }
    }

    /**
     * This method returns x position of the moving object
     * @return
     */
    public int getMovementXPOS(){
        return md.getMotionCog().x;
    }

    /**
     * This method returns the y position of the moving object
     * @return
     */
    public int getMovementYPOS(){
        return md.getMotionCog().y;
    }

    /**
     * This method sets the interval of when the camera will check for motion, is measured in milliseconds
     * @param motionInterval
     */
    public void setMotionInterval(int motionInterval) {
        if (motionInterval < 100){
            bobotUI.printIntervalToLow();
        }
    }

    /**
     * This method gets all the connected cameras and prints them to console
     */
    public void getAllConnectedCameras(){
        List<Webcam> webcamList;
        webcamList = Webcam.getWebcams();
        bobotUI.printAllCameras(webcamList);

    }

    /**
     * This method adds a new Object to the detected objects list whenever a new object is detected
     * @param image
     * @param y
     * @param x
     * @param area
     */
    public void addDetectedObject(BufferedImage image, int y, int x, double area){
        Object d = new Object();
        d.setObjectImage(image);
        d.setXPos(x);
        d.setYPos(y);
        d.setArea(area);
        detectedObjects.add(d);
    }

}
