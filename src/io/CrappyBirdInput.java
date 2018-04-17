package io;

import com.illposed.osc.OSCPortIn;
import java.util.concurrent.TimeUnit;

public class CrappyBirdInput extends ProcessInput2Output implements Runnable {
    private OSCPortIn receiver;
    private String Name;
    private Thread CrappyThread;
    private String OSCAddress;
    private CrappyBirdListener listener;
    private double TotalVolumne;
    private int HighScore;
    private int Score;


    public void setTotalVolumne(double totalVolumne) {
        TotalVolumne += totalVolumne;
    }

    public void setHighScore(int highScore) {
        HighScore = highScore;
    }

    public void setScore(int score) {
        Score += score;
    }

    public CrappyBirdInput(int nmbrOutputs, int PortIn, String Name) {
        super(nmbrOutputs);
        try {
            this.Name = Name;
            this.OSCAddress = "/" + Name;
            System.out.println(this.OSCAddress);
            this.listener = new CrappyBirdListener();
            this.receiver = new OSCPortIn(PortIn);
            this.receiver.addListener("/CrappyBird", this.listener);//
        } catch (java.net.SocketException e) {
            e.printStackTrace();
        }
    }


    public void run(){
        readInput();
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
                setHighScore(this.listener.HighScore);
                setScore(this.listener.Score);
                setTotalVolumne(this.listener.Volume);
                System.out.println(this.TotalVolumne);
                System.out.println(this.HighScore);
                System.out.println(this.Score);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        System.out.println("Starting: " + this.Name);
        if (this.CrappyThread == null) {
            this.CrappyThread = new Thread(this, this.Name);
            this.CrappyThread.start();
        }
    }

    private void readInput() {
        System.out.println("Start Listening");
        this.receiver.startListening();
        System.out.println(this.receiver.isListening());
    }

}

