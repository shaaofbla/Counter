package io;


import java.util.HashMap;
import java.util.Map;

public class FunctionCollection {
    public static Double PolarRadius(Double x, Double y){
        return Math.sqrt(x*x + y*y);
    }

    public static Double PolarAngle(Double x, Double r){
        return Math.acos(x / r);
    }
    public Map<String,Double> ToPolar(Double x, Double y) {
        Double r = this.PolarRadius(x,y);
        Double phi =  this.PolarAngle(x,r);
        HashMap<String, Double> res = new HashMap<>();
        res.put("radius", r);
        res.put("phi", phi);
        return res;
    }
}
