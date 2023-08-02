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
    public V2d(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructs a new V2d object representing the vector from one P2d point (from) to another P2d point (to).
     *
     * @param to   The destination P2d point of the vector.
     * @param from The starting P2d point of the vector.
     */
    public V2d(final P2d to, final P2d from) {
        this.x = to.x - from.x;
        this.y = to.y - from.y;
    }

    /**
     * Returns the x-component of the vector.
     *
     * @return The x-component of the vector.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns the y-component of the vector.
     *
     * @return The y-component of the vector.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Returns a new V2d vector resulting from the sum of another V2d vector from this vector.
     * @param v The V2d vector to be sum from this vector.
     * @return The resulting V2d vector after the sum.
     */
    public V2d sum(final V2d v) {
        return new V2d(x + v.x, y + v.y);
    }

    /**
     * Returns a new V2d vector resulting from module of this vector.
     *
     * 
     * @return The resulting V2d vector after the module.
     */
    public double module() {
        return (double) Math.sqrt(x * x + y * y);
    }

    /**
     * Returns a new V2d vector resulting from the molitiplication of this vector by a factor.
     *
     * @param fact The factor to multiply this vector by.
     * @return The resulting V2d vector after the multiplication.
     */
    public V2d mul(final double fact) {
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
