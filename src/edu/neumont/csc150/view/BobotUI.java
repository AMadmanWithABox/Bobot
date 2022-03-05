package edu.neumont.csc150.view;

import com.github.sarxos.webcam.Webcam;

import java.util.List;

public class BobotUI {

    /**
     * When called prints the interval error message to console
     */
    public void printIntervalToLow(){
        System.out.println("Interval cant be less than 100 milliseconds");
    }

    /**
     * When called this method prints the location of the movement to the console,
     * it takes in the x direction of movement, and the y direction of movement
     * @param xDirection
     * @param yDirection
     */
    public void printMovementLocation(String xDirection, String yDirection){
        System.out.println("Movement is detected on the " + yDirection + " " + xDirection);
    }

    /**
     * This prints all webcams names and device names
     * @param webcamList
     */
    public void printAllCameras(List<Webcam> webcamList){
        for (Webcam webcam : webcamList) {
            System.out.println(webcam.getName() + webcam.getDevice());
        }
    }

}
