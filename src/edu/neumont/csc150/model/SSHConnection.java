package edu.neumont.csc150.model;

import com.jcraft.jsch.*;
import net.schmizz.sshj.connection.ConnectionException;
import net.schmizz.sshj.transport.TransportException;


import java.io.IOException;

public class SSHConnection {
    JSch jSch = new JSch();
    Session session;
    ChannelSftp sftpChannel;
    Channel channel;

    /**
     * This method takes in the username of the network, password of the network, port of the network, and the ip address of the network.
     * then establishes a connection to the network via SSH
     * @param ip
     * @param username
     * @param password
     * @param port
     * @throws IOException
     */
    public void connectToSSHServer(String ip, String username, String password, int port) throws Exception {

        session = jSch.getSession(username,ip, port);
        session.setPassword(password);
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
    }

    /**
     * This method sends a command for the network to execute
     * @param command
     * @throws TransportException
     * @throws ConnectionException
     */
    public void commandSSHClient(String command) throws TransportException, ConnectionException, JSchException {

        sftpChannel = (ChannelSftp) session.openChannel("sftp");
        sftpChannel.connect();
        channel = session.openChannel("exec");
        ((ChannelExec)channel).setCommand(command);
        channel.connect();
    }

    /**
     * This method disconnects the SSH connection to the network
     * @throws IOException
     */
    public void sshDisconnect() throws IOException {
        sftpChannel.disconnect();
    }
}
