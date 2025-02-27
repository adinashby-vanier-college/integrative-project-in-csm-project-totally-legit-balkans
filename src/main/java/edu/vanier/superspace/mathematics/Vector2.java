package edu.vanier.superspace.mathematics;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
     *
     * @return
     */
    public double sqrMagnitude() {
        return 0.0; // Method not implemented
    }

    /**
     * Calculates the vector as a unit vector, meaning it has a length of one unit, but
     * it keeps the same direction.
     * @return a new instance of a vector keeping the same direction but a length of one
     */
    public Vector2 normalized() {
        return null; // Method not implemented
    }

    /**
     *
     * @param other
     * @return
     */
    public Vector2 project(Vector2 other) {
        return null; // Method not implemented
    }

    /**
     * Multiplies its components by a given value.
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
            return Vector2.of(0.0, 0.0);
        }

        return new Vector2(x / value, y / value);
    }

    /**
     *
     * @param value
     * @return
     */
    public Vector2 clamp(double value) {
        return null; // Method not implemented
    }

    /**
     * Computes the distance between two vectors.
     * @param otherVector the vector to which the distance will be calculated
     * @return the value of the distance
     */
    public double distance(Vector2 otherVector) {
        return 0; // Method not implemented
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
     *
     * @param firstVector
     * @param secondVector
     * @param value
     * @return
     */
    public Vector2 slerp(Vector2 firstVector, Vector2 secondVector, double value) {
        return null; // Method not implemented
    }

    /**
     * Calculates the normal of a vector.
     * @return a new instance of a vector containing the normal's components
     */
    public Vector2 normal() {
        return null; // Method not implemented
    }

    /**
     * Rotates the vector based on a certain rotation given by the user.
     * @param rotation the value of the rotation in radians
     * @return a new instance of a vector with the new components after being rotated
     */
    public Vector2 rotate(double rotation) {
        return null; // Method not implemented
    }

    /**
     * Checks if two different vectors are equal by comparing their components.
     * @param otherVector a second vector to be compared to
     * @return true or false
     */
    public boolean equals(Vector2 otherVector) {
        return this.x == otherVector.x && this.y == otherVector.y;
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
