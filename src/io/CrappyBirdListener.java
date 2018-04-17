package io;

import com.illposed.osc.OSCListener;
import com.illposed.osc.OSCMessage;

import java.util.Date;

public class CrappyBirdListener implements OSCListener{

    public int Score;
    public int HighScore;
    public Double Volume;

    public void setScore(int score) {
        this.Score = score;
    }

    public void setHighScore(int highScore) {
        this.HighScore = highScore;
    }

    public void setVolume(Double volume) {
        this.Volume = volume;
    }

    @Override
    public void acceptMessage(Date time, OSCMessage message){
        String stringMessage;
        stringMessage = message.getArguments().toString().replace("[", "").replace("]", "");
        String[] data = stringMessage.split(",\\s");
        setVolume(Double.parseDouble(data[0]));
        setScore(Integer.parseInt(data[1]));
        setHighScore(Integer.parseInt(data[2]));
    }

}
