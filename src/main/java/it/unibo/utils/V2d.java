package it.unibo.utils;

/**
 *
 * 2-dimensional vector
 * objects are completely state-less
 *
 */
public class V2d implements java.io.Serializable {

    public double x, y;

    public V2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public V2d(P2d to, P2d from) {
        this.x = to.x - from.x;
        this.y = to.y - from.y;
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public V2d sum(V2d v) {
        return new V2d(x + v.x, y + v.y);
    }

    public double module() {
        return (double) Math.sqrt(x * x + y * y);
    }

    public V2d mul(double fact) {
        return new V2d(x * fact, y * fact);
    }

    public String toString() {
        return "V2d(" + x + "," + y + ")";
    }
}
