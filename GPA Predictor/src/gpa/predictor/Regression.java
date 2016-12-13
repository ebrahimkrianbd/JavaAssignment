/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gpa.predictor;

/**
 *
 * @author rianix
 */
public class Regression {
    private double x;
    private double y;
    private double xy;
    private double xSqr;

    public Regression() {
    }

    public Regression(double x, double y, double xy, double xSqr) {
        this.x = x;
        this.y = y;
        this.xy = xy;
        this.xSqr = xSqr;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getXy() {
        return xy;
    }

    public double getxSqr() {
        return xSqr;
    }
    
    
}
