package superheroes;
import java.util.Objects;
public class Vector2D {
    public final int x;
    public final int y;

    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public String toString() {
        return ("(" + this.x + "," + this.y + ")");
    }

    public boolean precedes(Vector2D other) {
        return this.x <= other.x && this.y <= other.y;
    }

    public boolean follows(Vector2D other) {
        return this.x >= other.x && this.y >= other.y;
    }

    public Vector2D add(Vector2D other) {
        return new Vector2D(this.x + other.x, this.y + other.y);
    }

    public Vector2D subtract(Vector2D other) {
        return new Vector2D(this.x - other.x, this.y - other.y);
    }

    public Vector2D upperRight(Vector2D other) {
        int xmax;
        int ymax;
        xmax = Math.max(this.x, other.x);
        ymax = Math.max(this.y, other.y);
        return new Vector2D(xmax, ymax);
    }

    public Vector2D lowerLeft(Vector2D other) {
        int xMin;
        int yMin;
        xMin = Math.min(this.x, other.x);
        yMin = Math.min(this.y, other.y);
        return new Vector2D(xMin, yMin);
    }

    public Vector2D opposite() {
        return new Vector2D(-this.x, -this.y);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Vector2D vector2D = (Vector2D) other;
        return x == vector2D.x && y == vector2D.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }


}