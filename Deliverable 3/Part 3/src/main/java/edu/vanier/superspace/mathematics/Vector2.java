package edu.vanier.superspace.mathematics;

import edu.vanier.superspace.annotations.ToSerialize;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@ToSerialize
public class Vector2 {
    /**
     * Data Fields
     */
    private double x;
    private double y;

    /**
     * Default Constructor: default values are 0 for both components, and it is proper to only this class
     */
    private Vector2() {
        this.x = 0.0;
        this.y = 0.0;
    }

    /**
     * Parameterized constructor that will be proper to only this class.
     * @param x the x component of the vector
     * @param y the y component of the vector
     */
    private Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Vector2 copyOf(Vector2 vector) {
        return new Vector2(vector.x, vector.y);
    }

    /**
     * Creates a new vector using the constructor.
     * @param x the x component of a vector
     * @param y the y component of a vector
     * @return the new vector
     */
    public static Vector2 of(double x, double y) {
        return new Vector2(x, y);
    }

    /**
     * Creates a new vector by having an angle as an input and its length.
     * @param angle the angle of the vector
     * @param length the length of the vector
     * @return a new vector with the calculated values for the components using the angle and the length
     */
    public static Vector2 ofAngle(double angle, double length) {
        return new Vector2(Math.cos(angle), Math.sin(angle)).multiplyAssign(length);
    }

    /**
     * Shorthand for the (-1, 0) vector.
     */
    public static Vector2 left() {
        return new Vector2(-1, 0);
    }

    /**
     * Shorthand for the (1, 0) vector.
     */
    public static Vector2 right() {
        return new Vector2(1, 0);
    }

    /**
     * Shorthand for the (0, 1) vector.
     */
    public static Vector2 up() {
        return new Vector2(0, 1);
    }

    /**
     * Shorthand for the (0, -1) vector.
     */
    public static Vector2 down() {
        return new Vector2(0, -1);
    }

    /**
     * Shorthand for the (+inf, +inf) vector.
     */
    public static Vector2 positiveInfinity() {
        return new Vector2(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    /**
     * Shorthand for the (-inf, -inf) vector.
     */
    public static Vector2 negativeInfinity() {
        return new Vector2(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
    }

    /**
     * Shorthand for the (1, 1) vector.
     */
    public static Vector2 one() {
        return new Vector2(1, 1);
    }

    /**
     * Shorthand for the (0, 0) vector.
     */
    public static Vector2 zero() {
        return new Vector2(0, 0);
    }

    /**
     * Negates the vector by changing the direction of both its components.
     * @return the same vector but with a change in its direction
     */
    public Vector2 negateAssign() {
        this.x = -this.x;
        this.y = -this.y;
        return this;
    }

    /**
     * Negates the components of the vector (switches direction).
     * @return a new vector with the negated components instead of the vector itself
     */
    public Vector2 negate() {
        return new Vector2(-this.x, -this.y);
    }

    /**
     * Negates the components of the vector to correct for rendering axis direction only.
     * @return A new vector with the negated components accounting for rendering axis directions.
     */
    public Vector2 negateOnRenderAxis() {
        return new Vector2(-this.x, this.y);
    }

    /**
     * Adds the components of another vector to its components.
     * @param other the vector that will have its components added
     * @return the same vector to which the components of the other vector have been added
     */
    public Vector2 addAssign(Vector2 other) {
        this.x += other.x;
        this.y += other.y;
        return this;
    }

    /**
     * Adds the components of another vector to its components.
     * @param other the vector that will have its components added
     * @return a new instance of a vector with both the components of both the vectors added together
     */
    public Vector2 add(Vector2 other) {
        return new Vector2(x + other.x, y + other.y);
    }

    /**
     * Subtracts the components of another vector to its components.
     * @param other the vector that will have its components subtracted from the vector calling this method
     * @return the same vector to which the components of the other vector have been subtracted
     */
    public Vector2 subtractAssign(Vector2 other) {
        this.x -= other.x;
        this.y -= other.y;
        return this;
    }

    /**
     * Subtracts the components of another vector to its components.
     * @param other the vector that will have its components subtracted from the vector calling this method
     * @return a new instance of a vector with both the components of both the vectors subtracted from one another
     */
    public Vector2 subtract(Vector2 other) {
        return new Vector2(x - other.x, y - other.y);
    }

    /**
     * Calculates the magnitude of the vector calling this function. Note that this does not return the direction.
     * @return the magnitude of the vector
     */
    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    /**
     * Calculates the square of the magnitude of the vector calling this function.
     * @return square magnitude of the vector
     */
    public double sqrMagnitude() {
        return x * x + y * y;
    }

    /**
     * Calculates the vector as a unit vector, meaning it has a length of one unit, but
     * it keeps the same direction.
     * @return a new instance of a vector keeping the same direction but a length of one
     */
    public Vector2 normalized() {
        double magnitude = magnitude();
        
        //the only time normalize could not be calculated is when Vector(0,0)
        //in that case we return Vector divided by 1 to avoid  division by 0
        if(magnitude==0) magnitude = 1;
        
        
        return new Vector2(x / magnitude, y / magnitude);
        
    }

    /**
     * Calculates the vector as a unit vector, meaning it has a length of one unit, but
     * it keeps the same direction.
     * @return This vector as a unit vector.
     */
    public Vector2 normalize() {
        double magnitude = magnitude();
        
        //the only time normalize could not be calculated is when Vector(0,0)
        //in that case we return Vector divided by 1 to avoid  division by 0
        if(magnitude==0) magnitude = 1;
        
        this.x /= magnitude;
        this.y /= magnitude;
        return this;
    }

    /**
     * Multiplies its components by a given scalar.
     * @param value the value of which the components of the vector will be multiplied by
     * @return the same vector with its components multiplied
     */
    public Vector2 multiplyAssign(double value) {
        this.x *= value;
        this.y *= value;
        return this;
    }

    /**
     * Multiplies its components by a given value.
     * @param value the value of which the components of the vector will be multiplied by
     * @return a new instance of a vector with the new values of the components
     */
    public Vector2 multiply(double value) {
        return new Vector2(x * value, y * value);
    }

    /**
     * Divides its components by a given value. Note that this value can't be zero and it is checked.
     * @param value the value of which the components of the vector will be divided by
     * @return the same vector with its components divided
     */
    public Vector2 divideAssign(double value) {
        if (value == 0) {
            this.x = 0.0;
            this.y = 0.0;
            return this;
        }

        this.x /= value;
        this.y /= value;
        return this;
    }

    /**
     * Divides its components by a given value. Note that this value can't be zero and it is checked.
     * @param value the value of which the components of the vector will be divided by
     * @return a new instance of a vector with the newly divided components
     */
    public Vector2 divide(double value) {
        if (value == 0) {
            return new Vector2();
        }

        return new Vector2(x / value, y / value);
    }

    /**
     * Clamps the magnitude within the given bound.
     * @param maxMagnitude Maximum magnitude of the vector.
     * @return New vector clamped within the given bound.
     */
    public Vector2 clampMagnitude(double maxMagnitude) {
        double magnitude = magnitude();
        if (magnitude <= maxMagnitude) return new Vector2(this.x, this.y);

        return this.multiply(maxMagnitude / magnitude);
    }

    /**
     * Computes the distance between two vectors.
     * @param otherVector the vector to which the distance will be calculated
     * @return the value of the distance
     */
    public double distanceTo(Vector2 otherVector) {
        return otherVector.subtract(this).magnitude();
    }

    /**
     * Computes the dot product between two vectors.
     * @param otherVector another vector
     * @return The computation of the dot product
     */
    public double dot(Vector2 otherVector) {
        return this.x * otherVector.x + this.y * otherVector.y;
    }

    /**
     * Linearly interpolate between two vectors.
     * @param from Origin vector
     * @param to Destination vector
     * @param evaluateAt Scalar between 0 and 1, where 0 is the beginning of the interpolation and 1 is the end.
     * @return Interpolated vector.
     */
    public static Vector2 lerp(Vector2 from, Vector2 to, double evaluateAt) {
        Vector2 additionVector = to.subtract(from);
        return from.add(additionVector.multiply(evaluateAt));
    }

    /**
     * Calculates the normal between two vectors (Pointing 90 degrees to the left of the original vector).
     * @return a new instance of a vector containing the normal's components
     */
    public Vector2 perpendicular() {
        return new Vector2(this.y, -this.x);
    }

    /**
     * Get the angle of the vector (between -pi and pi radians) relative to the Vector pointing (1, 0).
     * @return The signed angle of this vector
     */
    public double signedAngle() {
        return Math.atan2(this.y, this.x);
    }

    /**
     * Get the angle of the vector (between 0 and pi radians) relative to the Vector pointing (1, 0).
     * @return The angle of this vector.
     */
    public double angle() {
        return Math.abs(signedAngle());
    }

    /**
     * Rotates the vector based on a certain rotation given by the user.
     * @param angle the value of the rotation in radians
     * @return a new instance of a vector with the new components after being rotated
     */
    public Vector2 rotate(double angle) {
        
        angle = Math.toRadians(angle);
        
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);

        // Taken from a rotation matrix in 2D space
        return new Vector2(x * cos - y * sin, x * sin + y * cos);
    }

    /**
     * Checks if two different vectors are equal by comparing their components.
     * @param otherVector a second vector to be compared to
     * @return true or false
     */
    public boolean equals(Vector2 otherVector) {
        return this.x == otherVector.x && this.y == otherVector.y;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Vector2 vector2 = (Vector2) object;
        return equals(vector2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * Converts the components of the vector string format to be able to return the object as a string
     * @return the object in a string format
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
