package io;


import math.geom2d.Vector2D;

import java.util.Vector;

public class TrackedPoint {
    Double xCoordinate;
    Double yCoordinate;
    Double radius; //Radius of object
    long timePoint;
    long last_timePoint;
    Double phi;
    Double psi; //Radius from cartesian coordinate system
    Double last_xCoordinate;
    Double last_yCoordinate;
    Double last_radius;
    Double last_phi;
    Double last_psi;
    Vector2D last_cartesian_speed;
    Double cartesian_acceleration;
    Vector2D cartesian_speed;

    public TrackedPoint(){
        xCoordinate = 0.0;
        yCoordinate = 0.0;
        radius = 0.0;
        phi = 0.0;
        psi = 0.0;
        last_xCoordinate = 0.0;
        last_yCoordinate = 0.0;
    }

    public void setTimePoint() {
        setLast_timePoint(this.timePoint);
        this.timePoint = System.currentTimeMillis();
    }

    public void setLast_timePoint(long last_timePoint) {
        this.last_timePoint = last_timePoint;
    }

    public void setLast_cartesian_speed(Vector2D last_cartesian_speed) {
        this.last_cartesian_speed = last_cartesian_speed;
    }

    public void setPhi(Double phi) {
        setLast_phi(this.phi);
        this.phi = phi;
    }

    public void setPsi(Double psi) {
        setLast_psi(this.psi);
        this.psi = psi;
    }

    public void setLast_phi(Double last_phi) {
        this.last_phi = last_phi;
    }

    public void setLast_psi(Double last_psi) {
        this.last_psi = last_psi;
    }

    public void setxCoordinate(Double xCoordinate) {
        setLast_xCoordinate(this.xCoordinate);
        this.xCoordinate = xCoordinate;
    }

    public void setyCoordinate(Double yCoordinate) {
        setLast_yCoordinate(this.yCoordinate);
        this.yCoordinate = yCoordinate;
    }

    public void setRadius(Double radius) {
        setLast_radius(this.radius);
        this.radius = radius;
    }

    public void setLast_xCoordinate(Double last_xCoordinate) {
        this.last_xCoordinate = last_xCoordinate;
    }

    public void setLast_yCoordinate(Double last_yCoordinate) {
        this.last_yCoordinate = last_yCoordinate;
    }

    public void setLast_radius(Double last_radius) {
        this.last_radius = last_radius;
    }

    public void setCartesian_speed(Vector2D cartesian_speed) {
        setLast_cartesian_speed(this.cartesian_speed);
        this.cartesian_speed = cartesian_speed;
    }

    public void setCartesian_acceleration(Double cartesian_acceleration) {
        this.cartesian_acceleration = cartesian_acceleration;
    }

    public void compute_Polar(){
        setPsi(FunctionCollection.PolarRadius(this.xCoordinate,this.yCoordinate));
        setPhi(FunctionCollection.PolarAngle(this.xCoordinate,this.radius));
    }

    public void compute_cartesian_speed() {
        Vector2D dxy;
        Vector2D pos;
        pos = new Vector2D(xCoordinate, yCoordinate);
        Vector2D last_pos;
        last_pos = new Vector2D(last_xCoordinate, last_yCoordinate);
        dxy = pos.minus(last_pos);
        Long dt = timePoint - last_timePoint;
        setCartesian_speed(dxy.times(1.0 / dt));
    }

}
