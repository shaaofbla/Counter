package io;

import com.illposed.osc.OSCPortIn;
import java.util.concurrent.TimeUnit;

public class CrappyBirdInput implements Runnable {
    public OSCPortIn receiver;
    private String Name;
    private Thread thread;
    private CrappyBirdListener listener;
    public OscVariable2Send[] Variables = new OscVariable2Send[3];

    private void setTotalVolumne(double totalVolume) {
        this.Variables[0].Variable += totalVolume;
        this.Variables[0].setMsg();
    }

    private void setHighScore(int highScore) {
        this.Variables[1].Variable = (double) highScore;
        this.Variables[1].setMsg();
    }

    private void setTotalScore(int score) {
        this.Variables[2].Variable += score;
        this.Variables[2].setMsg();
    }

    CrappyBirdInput(int PortIn, String Name) {

        try {
            this.Name = Name;
            String OSCInAddress = "/" + Name;
            this.listener = new CrappyBirdListener();
            this.receiver = new OSCPortIn(PortIn);
            this.receiver.addListener(OSCInAddress, this.listener);
            this.Variables[0] = new OscVariable2Send("TotalVolume");
            this.Variables[1] = new OscVariable2Send("HighScore");
            this.Variables[2] = new OscVariable2Send("TotalScore");
        } catch (java.net.SocketException e) {
            e.printStackTrace();
        }
    }


    public void run(){
        readInput();
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
                this.setHighScore(this.listener.HighScore);
                this.setTotalScore(this.listener.Score);
                this.setTotalVolumne(this.listener.Volume);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        System.out.println("Starting: " + this.Name);
        if (this.thread == null) {
            this.thread = new Thread(this, this.Name);
            this.thread.start();
        }
    }

    private void readInput() {
        System.out.println("Start Listening");
        this.receiver.startListening();
        System.out.println(this.receiver.isListening());
    }

}

