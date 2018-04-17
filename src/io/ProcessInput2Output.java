package io;

import math.geom2d.Vector2D;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class ProcessInput2Output {
    public int NmbrOutputs;
    public String[] OutMessages;
    public String[] OutMessageIdentifier;

    ProcessInput2Output(int nmbrOutputs){
        NmbrOutputs = nmbrOutputs;
        OutMessages = new String[0];
        OutMessageIdentifier = new String[0];
    }

    public UDPServer[] makeServerList(int[] ports, InetAddress[] ips) {
        NmbrOutputs = ports.length;
        UDPServer[] udpOutput = new UDPServer[NmbrOutputs];
        for (int i=0; i < NmbrOutputs; i++) udpOutput[i] = new UDPServer(ips[i], ports[i]);
        return udpOutput;
    }

    public UDPServer[] makeServerList(int[] ports, String[] ips) {
        NmbrOutputs = ports.length;
        InetAddress[] InetIp = new InetAddress[NmbrOutputs];
        for (int i=0; i < NmbrOutputs; i++) {
            try {
                InetIp[i] =InetAddress.getByName(ips[i]);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
        UDPServer[] udpOutput = makeServerList(ports, InetIp);
        return udpOutput;
    }



    public String[] concantenateStringArray(String[] arrayOne, String[] arrayTwo) {
        String[] newStringArray = new String[arrayOne.length + arrayTwo.length];
        System.arraycopy(arrayOne, 0 , newStringArray, 0 ,arrayOne.length);
        System.arraycopy(arrayTwo, 0, newStringArray, 0, arrayTwo.length);
        return newStringArray;
    }

    public String[] concantenateStringArray(String[] NewArray, String NewString) {
        System.out.println(NewArray);
        System.out.println(NewString);
        String[] NewString_Array = NewString.split("");
        String[] newStringArray = new String[NewArray.length + 1];
        System.arraycopy(NewArray, 0 , newStringArray, 0 ,NewArray.length);
        System.arraycopy(NewString_Array, 0, newStringArray, 0, 1);
        return newStringArray;
    }

    public void add_OutMessage_array(String[] NewOutMessages, String[] NewOutMessagesIdentifier) {
        this.OutMessages = concantenateStringArray(this.OutMessages, NewOutMessages);
        this.OutMessageIdentifier = concantenateStringArray(this.OutMessageIdentifier, NewOutMessagesIdentifier);
    }

    public void add_One_OutMessage(String NewOutMessages, String NewOutMessagesIdentifier) {
        this.OutMessages = concantenateStringArray(this.OutMessages, NewOutMessages);
        this.OutMessageIdentifier = concantenateStringArray(this.OutMessageIdentifier, NewOutMessagesIdentifier);
    }

    public void add_Vec2D_OutMessage(Vector2D NewVector, String NewVector2DIdentifier) {
        String VectorMessage = NewVector.toString();
        add_One_OutMessage(VectorMessage, NewVector2DIdentifier);
        System.out.println(Arrays.toString(this.OutMessages));
    }
}
