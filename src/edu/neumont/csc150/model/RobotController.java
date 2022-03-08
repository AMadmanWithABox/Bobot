package edu.neumont.csc150.model;

import com.jcraft.jsch.JSchException;
import net.schmizz.sshj.connection.ConnectionException;
import net.schmizz.sshj.transport.TransportException;

import java.io.IOException;

public class RobotController {

    public void turnLeft(){
        startConnection("192.168.160.18", "pi", "BeepBoop", 22, "python3 Directions/Left.py");
    }

    public void turnRight(){
        startConnection("192.168.160.18", "pi", "BeepBoop", 22, "python3 Directions/Right.py");
    }

    public void goForward(){
        startConnection("192.168.160.18", "pi", "BeepBoop", 22, "python3 Directions/Forward.py");
    }

    public void reverse(){
        startConnection("192.168.160.18", "pi", "BeepBoop", 22, "python3 Directions/Reverse.py");
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
