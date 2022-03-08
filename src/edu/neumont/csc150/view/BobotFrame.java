package edu.neumont.csc150.view;

import edu.neumont.csc150.model.Camera;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BobotFrame implements KeyListener {
    Camera bobotCam = new Camera();

    public void Bobotsframe() {
        bobotCam.getAllConnectedCameras();
        //Declarations
        JFrame mainFrame = new JFrame("Bobot");

        JTextArea mainMenuText = new JTextArea("Main Menu!");
        //remove?
        JButton followButton = new JButton("Follow");
        //remove too?
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
        followButton.setCursor(new Cursor(12));
        followButton.setBounds(100, 200, 150, 60);
        followButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                follow();
            }
        });

        //Button2
        detectButton.setBackground(Color.WHITE);
        detectButton.setForeground(Color.BLACK);
        detectButton.setCursor(new Cursor(12));
        detectButton.setBounds(100, 300, 150, 60);
        detectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detect();
            }
        });

        //Button3
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
        mainFrame.add(followButton);
        mainFrame.add(detectButton);
        mainFrame.add(controlButton);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }

    public void follow() {
        JFrame followFrame = new JFrame("Following");
        followFrame.setBounds(600, 200, 300, 400);
        followFrame.setLayout(null);
        followFrame.setVisible(true);
    }

    public void detect() {
        JFrame objectDetectFrame = new JFrame("Object Detection");
        objectDetectFrame.setBounds(600, 200, 300, 400);
        objectDetectFrame.setLayout(null);
        objectDetectFrame.setVisible(true);
    }

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

            }
        });
        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //go backwards
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

            System.out.println("Forward");
        }
        if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A) {
            System.out.println("Left");
        }
        if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D) {
            System.out.println("Right");
        }
        if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S) {
            System.out.println("Reverse");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}