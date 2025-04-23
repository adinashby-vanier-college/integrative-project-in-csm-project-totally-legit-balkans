package edu.vanier.tests;

import edu.vanier.superspace.mathematics.Vector2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2Test {
    public static class VectorAssertions {
        public static void assertVectorsEqual(Vector2 vec1, Vector2 vec2) {
            if (vec1.equals(vec2)) return;

            fail("Expected: " + vec1 + ", got " + vec2);
        }
    }

    @Test
    void copyOf() {
        Vector2 vec = Vector2.of(200, 200);
        Vector2 vecLiteralCopy = Vector2.of(vec.getX(), vec.getY());
        Vector2 vec2 = Vector2.copyOf(vec);
        vec.setX(20);
        VectorAssertions.assertVectorsEqual(vecLiteralCopy, vec2);
    }

    @Test
    void ofAngle() {
        for (int i = 0; i < 360; i += 5) {
            final double length = 2;
            Vector2 vec = Vector2.ofAngle(i, length);
            assertEquals(vec.getX(), Math.cos(i) * length);
            assertEquals(vec.getY(), Math.sin(i) * length);
        }
    }

    @Test
    void negateAssign() {
        final double x = 2;
        final double y = 4;
        Vector2 original = Vector2.of(x, y);
        original.negateAssign();
        VectorAssertions.assertVectorsEqual(Vector2.of(-x, -y), original);
    }

    @Test
    void negate() {
        final double x = 2;
        final double y = 4;
        Vector2 original = Vector2.of(x, y);
        Vector2 copy = original.negate();
        VectorAssertions.assertVectorsEqual(Vector2.of(x, y), original);
        VectorAssertions.assertVectorsEqual(Vector2.of(-x, -y), copy);
    }

    @Test
    void addAssign() {
        final double x1 = 2;
        final double y1 = 4;
        final double x2 = 7;
        final double y2 = 18;
        Vector2 op1 = Vector2.of(x1, y1);
        Vector2 op2 = Vector2.of(x2, y2);
        op1.addAssign(op2);
        VectorAssertions.assertVectorsEqual(Vector2.of(x1 + x2, y1 + y2), op1);
        VectorAssertions.assertVectorsEqual(Vector2.of(x2, y2), op2);
    }

    @Test
    void add() {
        final double x1 = 2;
        final double y1 = 4;
        final double x2 = 7;
        final double y2 = 18;
        Vector2 op1 = Vector2.of(x1, y1);
        Vector2 op2 = Vector2.of(x2, y2);
        Vector2 result = op1.add(op2);
        VectorAssertions.assertVectorsEqual(Vector2.of(x1 + x2, y1 + y2), result);
        VectorAssertions.assertVectorsEqual(Vector2.of(x1, y1), op1);
        VectorAssertions.assertVectorsEqual(Vector2.of(x2, y2), op2);
    }

    @Test
    void subtractAssign() {
        final double x1 = 2;
        final double y1 = 4;
        final double x2 = 7;
        final double y2 = 18;
        Vector2 op1 = Vector2.of(x1, y1);
        Vector2 op2 = Vector2.of(x2, y2);
        op1.subtractAssign(op2);
        VectorAssertions.assertVectorsEqual(Vector2.of(x1 - x2, y1 - y2), op1);
        VectorAssertions.assertVectorsEqual(Vector2.of(x2, y2), op2);
    }

    @Test
    void subtract() {
        final double x1 = 2;
        final double y1 = 4;
        final double x2 = 7;
        final double y2 = 18;
        Vector2 op1 = Vector2.of(x1, y1);
        Vector2 op2 = Vector2.of(x2, y2);
        Vector2 result = op1.subtract(op2);
        VectorAssertions.assertVectorsEqual(Vector2.of(x1 - x2, y1 - y2), result);
        VectorAssertions.assertVectorsEqual(Vector2.of(x1, y1), op1);
        VectorAssertions.assertVectorsEqual(Vector2.of(x2, y2), op2);
    }

    @Test
    void magnitude() {
        Vector2 vec = Vector2.of(1, 1);
        double magnitude = vec.magnitude();
        assertEquals(1, magnitude);

        vec = Vector2.of(2, 2);
        magnitude = vec.magnitude();
        assertEquals(Math.sqrt(8), magnitude);

        vec = Vector2.of(7, 12);
        magnitude = vec.magnitude();
        assertEquals(Math.sqrt(193), magnitude);
    }

    @Test
    void sqrMagnitude() {
        Vector2 vec = Vector2.of(1, 1);
        double magnitude = vec.magnitude();
        assertEquals(1, magnitude);

        vec = Vector2.of(2, 2);
        magnitude = vec.magnitude();
        assertEquals(8, magnitude);

        vec = Vector2.of(7, 12);
        magnitude = vec.magnitude();
        assertEquals(193, magnitude);
    }

    @Test
    void normalized() {
        double x = 172;
        double y = 922;
        double magnitude = Math.sqrt(x * x + y * y);
        Vector2 original = Vector2.of(x, y);
        Vector2 copy = original.normalized();
        VectorAssertions.assertVectorsEqual(Vector2.of(x, y), original);
        VectorAssertions.assertVectorsEqual(Vector2.of(x / magnitude, y / magnitude), copy);
    }

    @Test
    void normalize() {
        double x = 172;
        double y = 922;
        double magnitude = Math.sqrt(x * x + y * y);
        Vector2 original = Vector2.of(x, y);
        original.normalize();
        VectorAssertions.assertVectorsEqual(Vector2.of(x / magnitude, y / magnitude), original);
    }

    @Test
    void divide() {
        double x1 = 2;
        double y1 = 4;
        double op2 = 2;
        Vector2 op1 = Vector2.of(x1, y1);
        Vector2 result = op1.divide(op2);
        VectorAssertions.assertVectorsEqual(Vector2.of(x1 / op2, y1 / op2), result);
        VectorAssertions.assertVectorsEqual(Vector2.of(x1, y1), op1);

        op2 = 0;
        op1 = Vector2.of(x1, y1);
        result = op1.divide(op2);
        VectorAssertions.assertVectorsEqual(Vector2.of(0, 0), result);
        VectorAssertions.assertVectorsEqual(Vector2.of(x1, y1), op1);
    }

    @Test
    void clampMagnitude() {
        double x = 172;
        double y = 922;
        Vector2 vec = Vector2.of(x, y);
        Vector2 clamp01 = vec.clampMagnitude(1);
        Vector2 clamp9 = vec.clampMagnitude(9);
        Vector2 clampInf = vec.clampMagnitude(Double.MAX_VALUE);
        assertEquals(1, clamp01.magnitude());
        assertEquals(9, clamp9.magnitude());
        assertEquals(vec.magnitude(), clampInf.magnitude());
    }

    @Test
    void distanceTo() {
        Vector2 vec1 = Vector2.of(2, 2);
        Vector2 vec2 = Vector2.of(0, 0);
        double diff12 = Vector2.of(2, 2).magnitude();
        assertEquals(diff12, vec1.distanceTo(vec2));

        Vector2 vec3 = Vector2.of(7, 5);
        Vector2 vec4 = Vector2.of(16, 22);
        double diff34 = Vector2.of(16 - 7, 22 - 5).magnitude();
        assertEquals(diff34, vec3.distanceTo(vec4));
    }

    @Test
    void dot() {
        Vector2 vec1 = Vector2.of(20, 25);
        Vector2 vec2 = Vector2.of(-12, -15);
        double dot = vec1.dot(vec2);
        assertEquals(dot, 20 * -12 + 25 * -15);
    }

    @Test
    void lerp() {
        Vector2 from = Vector2.of(2, 2);
        Vector2 to = Vector2.of(3, 3);
        double timeConstant = 0.5;
        Vector2 lerped = Vector2.lerp(from, to, timeConstant);
        VectorAssertions.assertVectorsEqual(Vector2.of(2.5, 2.5), lerped);
    }

    @Test
    void perpendicular() {
        double x = 12;
        double y = 7;
        Vector2 vec = Vector2.of(x, y);
        Vector2 perpendicular = vec.perpendicular();
        VectorAssertions.assertVectorsEqual(Vector2.of(x, -y), perpendicular);
    }
}