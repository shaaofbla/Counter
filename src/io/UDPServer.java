package io;

import java.io.*;
import java.net.*;
import com.illposed.osc.OSCMessage;


public class UDPServer {
    public int port;
    public DatagramSocket UDPSocket;
    public InetAddress ip;

    public UDPServer(InetAddress _ip, int _port) {
        port = _port;
        ip = _ip;
        try {
            UDPSocket = new DatagramSocket(port);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void send(String message) throws Exception {
        byte[] messageBytes;
        messageBytes = message.getBytes();
        DatagramPacket sendMessage = new DatagramPacket(messageBytes, messageBytes.length, ip, port);
        this.UDPSocket.send(sendMessage);
    }

    public void sendOSC(String message, String identifier) throws Exception {
        OSCMessage OscMsg = new OSCMessage();
        OscMsg.setAddress(identifier);
        OscMsg.addArgument(message);
        byte[] messageBytes;
        messageBytes = OscMsg.getByteArray();
        DatagramPacket sendMessage = new DatagramPacket(messageBytes,messageBytes.length,ip,port);
        UDPSocket.send(sendMessage);
    }

}

class TestUDPServer {
    public static void main(String args[]) throws Exception {
        System.out.println("Test UDPServer: send, sendOSC");
        int port = 6007;
        InetAddress ip = InetAddress.getByName("192.168.0.31");
        UDPServer testServer = new UDPServer(ip,port);
        String message = "test";
        testServer.send(message);
        testServer.sendOSC(message,"/x");
        System.out.println("Sending Done..");
    }
}



