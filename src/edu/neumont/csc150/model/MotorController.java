package edu.neumont.csc150.model;

import com.jcraft.jsch.JSchException;
import net.schmizz.sshj.connection.ConnectionException;
import net.schmizz.sshj.transport.TransportException;

import java.io.IOException;

public class MotorController {

    /**
     * Commands bot to turn left
     */
    public void turnLeft(){
        startConnection("192.168.34.18", "pi", "BeepBoop", 22, "python3 Directions/Left.py");
    }

    /**
     * Commands bot to turn right
     */
    public void turnRight(){

        startConnection("192.168.34.18", "pi", "BeepBoop", 22, "python3 Directions/Right.py");
    }

    /**
     * Commands bot to go forward
     */
    public void goForward(){
        startConnection("192.168.34.18", "pi", "BeepBoop", 22, "python3 Directions/Forward.py");
    }

    /**
     * Commands bot to reverse
     */
    public void reverse(){

        startConnection("192.168.34.18", "pi", "BeepBoop", 22, "python3 Directions/Reverse.py");
    }

    /**
     * When called opens connection to pi then sends given command
     *
     * @param ip
     * @param username
     * @param password
     * @param port
     * @param command
     */
    public void startConnection(String ip, String username, String password, int port, String command) {
        SSHConnection sshConnection = new SSHConnection();
        try {
            sshConnection.connectToSSHServer(ip, username, password, port);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            sshConnection.commandSSHClient(command);
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
    }
}
