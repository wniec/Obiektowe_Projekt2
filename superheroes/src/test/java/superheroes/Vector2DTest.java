package superheroes;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class Vector2DTest {
    Vector2D vec = new Vector2D(1, 2);

    @Test
    void equalwhenx1y2x1y2True() {
        assertEquals(vec, new Vector2D(1, 2));
    }

    @Test
    void equalwhenx1y2x2y3False() {
        assertNotEquals(vec, new Vector2D(2, 3));
    }

    @Test
    void equalwhenSecondIsNotAnObjectFalse() {
        assertNotEquals(vec, 4);
    }

    @Test
    void equalwhenSecondIsDiffrentObjectFalse() {
        assertNotEquals(vec, "Hello Main!");
    }

    @Test
    void equalx1y2ToStringTrue() {
        assertEquals(vec.toString(), "(1,2)");
    }

    @Test
    void equalx1y2ToStringFalse() {
        assertNotEquals(vec.toString(), "(1,8)");
    }

    @Test
    void precedesTrue() {
        assertEquals(vec.precedes(new Vector2D(2, 2)), true);
    }

    @Test
    void precedesFalse() {
        assertEquals(vec.precedes(new Vector2D(0, 0)), false);
    }

    @Test
    void followsTrue() {
        assertEquals(vec.follows(new Vector2D(0, 0)), true);
    }

    @Test
    void followsFalse() {
        assertEquals(vec.follows(new Vector2D(8, 9)), false);
    }

    @Test
    void upperRightTest() {
        assertEquals(vec.upperRight(new Vector2D(0, 9)), new Vector2D(1, 9));
    }
    @Test
    void lowerLeftTest() {
        assertEquals(vec.lowerLeft(new Vector2D(0, 9)), new Vector2D(0, 2));
    }
    @Test
    void addTest() {
        assertEquals(vec.add(new Vector2D(1, 9)), new Vector2D(2, 11));
    }
    @Test
    void subtractTest() {
        assertEquals(vec.subtract(new Vector2D(1, 9)), new Vector2D(0, -7));
    }
    @Test
    void oppositeTest1() {
        assertEquals(vec.opposite(), new Vector2D(-1, -2));
    }
    @Test
    void oppositeTest2() {
        assertEquals(vec.opposite().opposite(), new Vector2D(1, 2));
    }
}
