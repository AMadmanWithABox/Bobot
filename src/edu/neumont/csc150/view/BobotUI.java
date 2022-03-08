package edu.neumont.csc150.view;

import com.github.sarxos.webcam.Webcam;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class BobotUI {

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    /**
     * This Method displays options to user
     */
    public void displayMenu(){
        System.out.println("please make a selection \r\n\t1. start camera \r\n\t2. start motion detection(stays on till ended) \r\n\t3. detect motion for duration \r\n\t4. set robot mode \r\n\t5. Exit");
    }

    /**
     * This method returns if the correct end is entered
     * @return
     * @throws IOException
     */
    public boolean getEnd() throws IOException {
        System.out.println("please type end to finish entry");
        while(true){
            String textEntered = in.readLine();
            if(!textEntered.equals("end")){
                return true;
            }else{
                return false;
            }
        }
    }

    /**
     * This method displays the current error thrown
     * @param ex
     */
    public void printError(Exception ex){
        System.out.println(ex);
    }

    /**
     * This method gets the users input as an integer between a range
     * @param min
     * @param max
     * @return
     * @throws IOException
     */
    public int getUserInt(int min, int max) throws IOException {

        System.out.println("Please enter a number between " + min + " and " + max);

        int num;
        do{
            try {
                num = Integer.parseInt(in.readLine());
                if (num < min || num > max) {

                }
                else
                {
                    return num;
                }
            } catch(NumberFormatException ex){
                System.out.println("Invalid Input: Input needs to be between " + min + "and" + max);
            }

        }while(true);
    }

    /**
     * This method displays a goodbye message
     */
    public void printGoodbye(){
        System.out.println("Thank you now exiting ");

    }


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

    /**
     * This method is used for test prints
     * @param item
     */
    public void testPrint(String item){
        System.out.println(item);
    }

    /**
     * Displays if the camera is already on
     */
    public void cameraOn(){
        System.out.println("Camera is already on");
    }

    /**
     * Displays if motion detection is already on
     */
    public void motionDetectionOn(){
        System.out.println("Motion detection is already on");
    }

    public void displayRobotMode(){

        System.out.println("1. for follow mode \r\n2. for controller mode");

    }

    public void printFollowRobotMode(){
        System.out.println("Robot set to follow mode");
    }

    public void printControllerRobotMode(){
        System.out.println("Robot set to controller mode");
    }

}
