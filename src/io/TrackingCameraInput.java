package io;

import com.illposed.osc.OSCPortIn;

import java.net.InetAddress;
import java.net.SocketException;
import java.util.concurrent.TimeUnit;


public class TrackingCameraInput extends ProcessInput2Output {
    public OSCPortIn receiver;
    private String Name;
    private Thread thread;
    private TrackingCamereListener listener;
    public OscVariable2Send[] Variables;

    //private TrackedPoint CamX;
    TrackingCameraInput(int PortIn, String Name) {
        try {
            this.Name = Name;
            String OSCInAddress = "/" + Name;
            this.listener = new TrackingCamereListener();
            this.receiver = new OSCPortIn(PortIn);
            this.receiver.addListener(OSCInAddress, this.listener);
            this.Variables[0] = new OscVariable2Send("x");
            this.Variables[1] = new OscVariable2Send("y");
            this.Variables[2] = new OscVariable2Send("r");
            } catch (SocketException e) {
                e.printStackTrace();
        }
    }

    private void setxCoord(Double xCoord) {
        this.Variables[0].Variable = xCoord;
        this.Variables[0].setMsg();
    }

    private void setyCoord(Double yCoord) {
        this.Variables[1].Variable = yCoord;
        this.Variables[1].setMsg();
    }

    private void setRadius(Double radius) {
        this.Variables[2].Variable = radius;
        this.Variables[2].setMsg();
    }

    public void run() {
        this.receiver.startListening();
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
                this.setxCoord(this.listener.x);
                this.setyCoord(this.listener.y);
                this.setRadius(this.listener.r);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
