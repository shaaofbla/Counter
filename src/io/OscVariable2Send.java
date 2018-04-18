package io;

import com.illposed.osc.OSCMessage;


public class OscVariable2Send {
    public Double Variable;
    String Name;
    OSCMessage Msg;

    public OscVariable2Send(String name) {
        this.Variable = 0.0;
        this.Name = name;
        this.Msg = null;
    }

    public void setMsg() {
        Msg = new OSCMessage("/"+this.Name);
        Msg.addArgument(this.Variable);
    }
}
