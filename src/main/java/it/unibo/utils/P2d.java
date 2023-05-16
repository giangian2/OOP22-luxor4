package it.unibo.utils;

import java.util.Comparator;

public class P2d implements java.io.Serializable {

    public int x, y;

    public P2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        P2d other = (P2d) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }

    public P2d sum(V2d v) {
        return new P2d(x + v.x, y + v.y);
    }

    public V2d sub(P2d v) {
        return new V2d(x - v.x, y - v.y);
    }

    public String toString() {
        return "P2d(" + x + "," + y + ")";
    }

    public boolean isBetween(P2d p1, P2d p2) {
        // se sono sulla stessa ascissa
        if (this.x == p1.x && this.x == p2.x) {
            if ((this.y > p1.y && this.y < p2.y) || (this.y < p1.y && this.y > p2.y)) {
                return true;
            }
        }

        // se sono sulla stessa ordinata
        if (this.y == p1.y && this.y == p2.y) {
            if ((this.x > p1.x && this.x < p2.x) || (this.x < p1.x && this.x > p2.x)) {
                return true;
            }
        }

        // sennò
        return false;
    }

}
