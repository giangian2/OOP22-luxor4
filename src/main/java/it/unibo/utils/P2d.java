package it.unibo.utils;

import java.util.Objects;

/**
 * A 2-dimensional point represented by its x and y coordinates.
 */
public class P2d implements java.io.Serializable {

    /**
     * The serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The coordinate of the point.
     */
    private final double x, y;

    /**
     * Creates a new P2d object.
     *
     * @param x The x coordinate of the point.
     * @param y The y coordinate of the point.
     */
    public P2d(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x coordinate of this point.
     *
     * @return The x coordinate of this point.
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the y coordinate of this point.
     *
     * @return The y coordinate of this point.
     */

    public double getY() {
        return y;
    }

    /**
     * Checks if this point is equal to another object.
     *
     * @param obj The object to compare with this point.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final P2d other = (P2d) obj;
        if (Double.compare(x, other.x) != 0) {
            return false;
        }
        return Double.compare(y, other.y) == 0;
    }

    /**
     * Returns the hash code of this point.
     *
     * @return The hash code of this point.
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * Returns a new P2d object resulting from the vector sum of this point and a
     * V2d vector.
     *
     * @param v The V2d vector to be added to this point.
     * @return The resulting P2d point after the addition.
     */
    public P2d sum(final V2d v) {
        return new P2d(x + v.getX(), y + v.getY());
    }

    /**
     * Returns a new V2d vector resulting from the subtraction of another P2d point
     * from this point.
     *
     * @param v The P2d point to be subtracted from this point.
     * @return The resulting V2d vector after the subtraction.
     */
    public V2d sub(final P2d v) {
        return new V2d(x - v.x, y - v.y);
    }

    /**
     * Returns a string representation of this point in the format "P2d(x,y)".
     *
     * @return The string representation of this point.
     */
    @Override
    public String toString() {
        return "P2d(" + x + "," + y + ")";
    }

    /**
     * Calculates the module (length) of the vector represented by this point.
     *
     * @return The module of the vector.
     */
    public double module() {
        return Math.sqrt(x * x + y * y);
    }

    /**
     * Checks if this point lies between two other P2d points on the same axis.
     *
     * @param p1 The first P2d point defining the axis.
     * @param p2 The second P2d point defining the axis.
     * @return true if this point is between p1 and p2 on the same axis, false
     *         otherwise.
     */
    public boolean isBetween(final P2d p1, final P2d p2) {
        final double epsilon = 1e-9; // Soglia di tolleranza
        if (Math.abs(this.x - p1.x) < epsilon && Math.abs(this.x - p2.x) < epsilon
            && (this.y > p1.y && this.y < p2.y || this.y < p1.y && this.y > p2.y)) { 
            return true;
        }

        return Math.abs(this.y - p1.y) < epsilon && Math.abs(this.y - p2.y) < epsilon
            && (this.x > p1.x && this.x < p2.x || this.x < p1.x && this.x > p2.x);
    }

    /**
     * Calculates the sum of the x and y coordinates of this point.
     *
     * @return The sum of the x and y coordinates of this point.
     */
    public double sumOfAxis() {
        return x + y;
    }
}
