package io;


import com.illposed.osc.OSCPortOut;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

public class InputOutputStream {
    private TrackingCameraInput cameraA;
    private CrappyBirdInput CrappyBird;
    private OSCPortOut[] OscSender;

    private InputOutputStream(String[] OutIps, int[] OutPorts, int InputPort) {
        CrappyBird = new CrappyBirdInput(InputPort, "CrappyBird");
        cameraA = new TrackingCameraInput(10100,"CamA");
        this.OscSender = new OSCPortOut[OutIps.length];
        for (int i=0; i<OutIps.length; i++){
            try {
                InetAddress ip = InetAddress.getByName(OutIps[i]);
                this.OscSender[i] = new OSCPortOut(ip, OutPorts[i]);
            } catch (SocketException | UnknownHostException e) {
                e.printStackTrace();
            }
        }
    }

    private void SendAllVars(){
        for (int i=0; i<this.OscSender.length; i++){
            for (int j=0; j<this.CrappyBird.Variables.length; j++){
                try {
                    this.OscSender[i].send(this.CrappyBird.Variables[j].Msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    public static void main(String args[]) throws InterruptedException {
        // OutputPorts
        int[] OutPorts = new int[2];
        OutPorts[0] = 20100;
        OutPorts[1] = 20100;
        // Output Ips
        String[] OutIps = new String[2];
        OutIps[0] = "192.168.0.18";
        OutIps[1] = "192.168.0.19";
        //System.out.println(System.currentTimeMillis());
        InputOutputStream ioStream = new InputOutputStream(OutIps, OutPorts, 10400);
        ioStream.CrappyBird.start();
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        do {
            TimeUnit.MILLISECONDS.sleep(500);
            if (ioStream.CrappyBird.receiver.isListening())
                ioStream.SendAllVars();
        } while (true);
    }
}
