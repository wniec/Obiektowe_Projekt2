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
        if (this.x <= other.x && this.y <= other.y) {
            return true;
        }
        return false;
    }

    public boolean follows(Vector2D other) {
        if (this.x >= other.x && this.y >= other.y) {
            return true;
        }
        return false;
    }

    public Vector2D add(Vector2D other) {
        return new Vector2D(this.x + other.x, this.y + other.y);
    }

    public Vector2D subtract(Vector2D other) {
        Vector2D result = new Vector2D(this.x - other.x, this.y - other.y);
        return result;
    }

    public Vector2D upperRight(Vector2D other) {
        int xmax;
        int ymax;
        if (this.x >= other.x) {
            xmax = this.x;
        } else {
            xmax = other.x;
        }
        if (this.y >= other.y) {
            ymax = this.y;
        } else {
            ymax = other.y;
        }
        Vector2D result = new Vector2D(xmax, ymax);
        return result;
    }

    public Vector2D lowerLeft(Vector2D other) {
        int xmin;
        int ymin;
        if (this.x <= other.x) {
            xmin = this.x;
        } else {
            xmin = other.x;
        }
        if (this.y <= other.y) {
            ymin = this.y;
        } else {
            ymin = other.y;
        }
        Vector2D result = new Vector2D(xmin, ymin);
        return result;
    }

    public Vector2D opposite() {
        Vector2D result = new Vector2D(-this.x, -this.y);
        return result;
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