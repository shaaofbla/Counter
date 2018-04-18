package io;

import com.illposed.osc.OSCListener;
import com.illposed.osc.OSCMessage;

import java.util.Date;

public class TrackingCamereListener implements OSCListener{
    public Double x;
    public Double y;
    public Double r;


    private void setX(Double x) {
        this.x = x;
    }

    private void setY(Double y) {
        this.y = y;
    }

    private void setR(Double r) {
        this.r = r;
    }

    @Override
    public void acceptMessage(Date time, OSCMessage message){
        String stringMessage;
        stringMessage = message.getArguments().get(0).toString();
        String[] data = stringMessage.split("\\s");
        setX(Double.parseDouble(data[0]));
        setY(Double.parseDouble(data[1]));
        setR(Double.parseDouble(data[2]));
    }
}
