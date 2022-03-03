package edu.neumont.csc150.view;


import javax.swing.*;
import java.awt.*;

public class BobotFrame {

    JFrame frame = new JFrame("Bobot");
    JTextArea textArea = new JTextArea();

    public void frameStuff(){
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setBounds(200,200,700,700);
        frame.setLayout(null);

        textArea.setText("Testing for main menu");
        frame.add(textArea);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);






    }

}
