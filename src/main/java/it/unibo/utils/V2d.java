package it.unibo.utils;

/**
 * A 2-dimensional vector represented by its x and y components.
 */
public class V2d implements java.io.Serializable {
      /**
     * The component of the vector.
     */
    public double x, y;

    /**
     * Creates a new V2d object.
     *
     * @param x The x component of the vector.
     * @param y The y component of the vector.
     */
    public V2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructs a new V2d object representing the vector from one P2d point (from) to another P2d point (to).
     *
     * @param to   The destination P2d point of the vector.
     * @param from The starting P2d point of the vector.
     */
    public V2d(P2d to, P2d from) {
        this.x = to.x - from.x;
        this.y = to.y - from.y;
    }

    /**
     * Returns the x-component of the vector.
     *
     * @return The x-component of the vector.
     */
    public double getX(){
        return this.x;
    }

    /**
     * Returns the y-component of the vector.
     *
     * @return The y-component of the vector.
     */
    public double getY(){
        return this.y;
    }

    /**
     * Checks if this vector is equal to another object.
     *
     * @param obj The object to compare with this vector.
     * @return true if the objects are equal, false otherwise.
     */
    public V2d sum(V2d v) {
        return new V2d(x + v.x, y + v.y);
    }

    /**
     * Returns a new V2d vector resulting from the subtraction of another V2d vector from this vector.
     *
     * @param v The V2d vector to be subtracted from this vector.
     * @return The resulting V2d vector after the subtraction.
     */
    public double module() {
        return (double) Math.sqrt(x * x + y * y);
    }

    /**
     * Returns a new V2d vector resulting from the subtraction of another V2d vector from this vector.
     *
     * @param v The V2d vector to be subtracted from this vector.
     * @return The resulting V2d vector after the subtraction.
     */
    public V2d mul(double fact) {
        return new V2d(x * fact, y * fact);
    }

    /**
     * Returns a string representation of this vector in the format "V2d(x,y)".
     *
     * @return The string representation of this vector.
     */
    public String toString() {
        return "V2d(" + x + "," + y + ")";
    }
}
