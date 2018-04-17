package io;


public class InputOutputStream {
    private TrackingCameraInput cameraX;
    private CrappyBirdInput CrappyBird;

    public InputOutputStream(int NInputs, int InputPort) {
        CrappyBird = new CrappyBirdInput(NInputs, InputPort, "CrappyBird");
    }

    public SendAllVars(){

    }

    public static void main(String args[]){
        // OutputPorts
        int[] OutPorts = new int[2];
        OutPorts[0] = 6007;
        OutPorts[1] = 6008;
        // Output Ips
        String[] OutIps = new String[2];
        OutIps[0] = "192.168.0.31";
        OutIps[1] = "192.168.0.19";
        //System.out.println(System.currentTimeMillis());
        InputOutputStream ioStream = new InputOutputStream(1, 10400);

        ioStream.CrappyBird.start();

    }
}
