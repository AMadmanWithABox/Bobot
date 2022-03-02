package edu.neumont.csc150.view;


import javax.swing.*;
import java.awt.*;

public class BobotFrame {

    public void frameStuff() {
        //Declarations
        JFrame mainFrame = new JFrame("Bobot");
        JFrame followFrame = new JFrame("Following");
        JFrame ControlFrame = new JFrame("Controller");

        JTextArea mainMenuText = new JTextArea("Main Menu!");

        JButton followButton = new JButton("Follow");
        JButton detectButton = new JButton("Object Detection");
        JButton controlButton = new JButton("Control");

        //font type
        String fontForText = "Monospaced";
        int titles = 20;

        //Frame settings
        mainFrame.getContentPane().setBackground(Color.DARK_GRAY);
        mainFrame.setBounds(100, 100, 350, 600);
        mainFrame.setResizable(false);
        mainFrame.setLayout(null);

        //TextArea
        mainMenuText.setFont(new Font(fontForText, Font.BOLD, titles));
        mainMenuText.setLineWrap(true);
        mainMenuText.setEditable(false);
        mainMenuText.setBackground(Color.DARK_GRAY);
        mainMenuText.setForeground(Color.WHITE);
        mainMenuText.setBounds(120, 100, 150, 100);

        //Button1
        followButton.setBackground(Color.WHITE);
        followButton.setForeground(Color.BLACK);
        followButton.setBounds(100, 200, 150, 60);

        //Button2
        detectButton.setBackground(Color.WHITE);
        detectButton.setForeground(Color.BLACK);
        detectButton.setBounds(100, 300, 150, 60);

        //Button3
        controlButton.setBackground(Color.WHITE);
        controlButton.setForeground(Color.BLACK);
        controlButton.setBounds(100, 400, 150, 60);

        //Frame adding
        mainFrame.add(mainMenuText);
        mainFrame.add(followButton);
        mainFrame.add(detectButton);
        mainFrame.add(controlButton);

        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);


    }

}
