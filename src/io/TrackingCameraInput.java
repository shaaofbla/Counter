package io;

import java.net.InetAddress;


public class TrackingCameraInput extends ProcessInput2Output {

    private final TCPServer TCPinput;
    private TrackedPoint CamX;
    private String xIdentifier = "/x";
    private String yIdentifier = "/y";
    private String rIdentifier = "/r";
    private UDPServer[] udpOutput;

    public TrackingCameraInput(int[] OutPorts,String[] String_Ips){
        super(OutPorts.length);
        CamX = new TrackedPoint();
        TCPinput = new TCPServer(5007);
        udpOutput = makeServerList(OutPorts, String_Ips);
        }

    public TrackingCameraInput(int[] OutPorts,InetAddress[] Inet_Ips){
        super(OutPorts.length);
        TCPinput = new TCPServer(5007);
        udpOutput = makeServerList(OutPorts, Inet_Ips);
        }

    private void setxCoord(Double xCoord) {
        this.CamX.xCoordinate = xCoord;
    }

    private void setyCoord(Double yCoord) {
        this.CamX.yCoordinate = yCoord;
    }

    private void setRadius(Double radius) {
        this.CamX.radius = radius;
    }

    public void run() {
        readInputMessage();
        process();
        send();
    }

    public void process() {
        CamX.compute_Polar();
        CamX.compute_cartesian_speed();
        add_Vec2D_OutMessage(CamX.cartesian_speed,"/V");
    }

    public void readInputMessage() {
        String message = TCPinput.readMessage();
        CamX.setTimePoint();
        parseInput(message);
    }

    private void parseInput(String message) {
        String[] splits = message.split("\\s");
        Double x = Double.parseDouble(splits[0]);
        Double y = Double.parseDouble(splits[1]);
        Double r = Double.parseDouble(splits[2]);
        setxCoord(x);
        setyCoord(y);
        setRadius(r);
    }

    private void sendXYR(){
       for (UDPServer udp :udpOutput){
           System.out.println(udp.port);
           try {
               udp.sendOSC(Double.toString(CamX.xCoordinate), xIdentifier);
               udp.sendOSC(Double.toString(CamX.yCoordinate), yIdentifier);
               udp.sendOSC(Double.toString(CamX.radius), rIdentifier);
           }  catch (Exception e) {
               System.out.println("Receiver not up??");
               System.out.println(udp.ip);
               //e.printStackTrace();
           }
       }
    }

    private void send(){
       sendXYR();
    }
}
