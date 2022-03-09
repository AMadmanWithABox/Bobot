package edu.neumont.csc150.view;

import edu.neumont.csc150.controller.BobotController;
import edu.neumont.csc150.model.Camera;
import edu.neumont.csc150.model.RobotController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BobotFrame implements KeyListener {
    private Camera bobotCam = new Camera();
    private RobotController bc = new RobotController();

    /**
     * JFrame for Bobot,
     * Instructions menu and Manual Control
     */
    public void Bobotsframe() {
        bobotCam.getAllConnectedCameras();
        //Declarations
        JFrame mainFrame = new JFrame("Bobot");

        JTextArea mainMenuText = new JTextArea("Main Menu!");
        JTextArea instructionsText = new JTextArea();

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

        //TextArea 2
        instructionsText.setText("WASD " +
                "or " +
                "← ↑ ↓ →");
        instructionsText.setFont(new Font(fontForText, Font.BOLD, titles));
        instructionsText.setLineWrap(true);
        instructionsText.setEditable(false);
        mainMenuText.setForeground(Color.DARK_GRAY);
        instructionsText.setForeground(Color.WHITE);
        instructionsText.setBounds(120,200, 150,100);

        //Button
        controlButton.setBackground(Color.WHITE);
        controlButton.setForeground(Color.BLACK);
        controlButton.setCursor(new Cursor(12));
        controlButton.setBounds(100, 400, 150, 60);
        controlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control();
            }
        });

        //Frame adding
        mainFrame.add(mainMenuText);
        mainFrame.add(controlButton);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }

    /**
     * Controller frame for Bobot
     */
    public void control() {
        JFrame controlFrame = new JFrame("Controller");
        controlFrame.setBounds(600, 400, 175, 400);
        controlFrame.getContentPane().setBackground(Color.DARK_GRAY);
        controlFrame.setResizable(false);
        controlFrame.setLayout(null);

        JTextField keyText = new JTextField();
        keyText.addKeyListener(this);
        keyText.setBackground(Color.BLACK);
        keyText.setBounds(0, 0, 160, 180);
        keyText.setEditable(false);

        JButton forwardButton = new JButton("↑");
        JButton leftButton = new JButton("←");
        JButton rightButton = new JButton("→");
        JButton backButton = new JButton("↓");

        forwardButton.setBounds(55, 205, 50, 40);
        leftButton.setBounds(5, 255, 50, 40);
        rightButton.setBounds(105, 255, 50, 40);
        backButton.setBounds(55, 305, 50, 40);

        //button customization
        forwardButton.setBackground(Color.WHITE);
        forwardButton.setForeground(Color.BLACK);
        forwardButton.setCursor(new Cursor(12));

        leftButton.setBackground(Color.WHITE);
        leftButton.setForeground(Color.BLACK);
        leftButton.setCursor(new Cursor(12));

        rightButton.setBackground(Color.WHITE);
        rightButton.setForeground(Color.BLACK);
        rightButton.setCursor(new Cursor(12));

        backButton.setBackground(Color.WHITE);
        backButton.setForeground(Color.BLACK);
        backButton.setCursor(new Cursor(12));


        forwardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bc.goForward();
            }
        });
        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bc.turnLeft();
            }
        });
        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bc.turnRight();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bc.reverse();
            }
        });

        controlFrame.add(keyText);
        controlFrame.add(forwardButton);
        controlFrame.add(leftButton);
        controlFrame.add(rightButton);
        controlFrame.add(backButton);
        controlFrame.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W) {
            bc.goForward();
            System.out.println("Forward");
        }
        if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A) {
            bc.turnLeft();
            System.out.println("Left");
        }
        if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D) {
            bc.turnRight();
            System.out.println("Right");
        }
        if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S) {
            bc.reverse();
            System.out.println("Reverse");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}