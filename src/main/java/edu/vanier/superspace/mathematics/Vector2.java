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
     * Parameterized constructor that will be proper to only this class
     * @param x the x component of the vector
     * @param y the y component of the vector
     */
    private Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a new vector using the constructor
     * @param x the x component of a vector
     * @param y the y component of a vector
     * @return the new vector
     */
    public static Vector2 of(double x, double y) {
        return new Vector2(x, y);
    }

    /**
     * Creates a new vector by having an angle as an input and its length
     * @param angle the angle of the vector
     * @param length the length of the vector
     * @return a new vector with the calculated values for the components using the angle and the length
     */
    public static Vector2 ofAngle(double angle, double length) {
        return new Vector2(Math.cos(angle), Math.sin(angle)).multiplyAssign(length);
    }

    /**
     * Negates the vector by changing the direction of both its components
     * @return the same vector but with a change in its direction
     */
    public Vector2 negateAssign() {
        this.x = -this.x;
        this.y = -this.y;
        return this;
    }

    /**
     * Negates the components of the vector (switches direction)
     * @return a new vector with the negated components instead of the vector itself
     */
    public Vector2 negate() {
        return new Vector2(-this.x, -this.y);
    }

    /**
     * Adds the components of another vector to its components
     * @param other the vector that will have its components added
     * @return the same vector to which the components of the other vector have been added
     */
    public Vector2 addAssign(Vector2 other) {
        this.x += other.x;
        this.y += other.y;
        return this;
    }

    public Vector2 add(Vector2 other) {
        return new Vector2(x + other.x, y + other.y);
    }

    public Vector2 subtractAssign(Vector2 other) {
        this.x -= other.x;
        this.y -= other.y;
        return this;
    }

    public Vector2 subtract(Vector2 other) {
        return new Vector2(x - other.x, y - other.y);
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    public double sqrMagnitude() {
        return 0.0; // Method not implemented
    }

    public Vector2 normalized() {
        return null; // Method not implemented
    }

    public Vector2 project(Vector2 other) {
        return null; // Method not implemented
    }

    public Vector2 multiplyAssign(double value) {
        this.x *= value;
        this.y *= value;
        return this;
    }

    public Vector2 multiply(double value) {
        return new Vector2(x * value, y * value);
    }

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

    public Vector2 divide(double value) {
        if (value == 0) {
            return Vector2.of(0.0, 0.0);
        }

        return new Vector2(x / value, y / value);
    }

    public Vector2 clamp(double value) {
        return null; // Method not implemented
    }

    public double distance(Vector2 otherVector) {
        return 0; // Method not implemented
    }

    /**
     * Computes the dot product between two vectors
     * @param otherVector another vector
     * @return The computation of the dot product
     */
    public double dot(Vector2 otherVector) {
        return this.x * otherVector.x + this.y * otherVector.y;
    }

    public Vector2 s_lerp(Vector2 firstVector, Vector2 secondVector, double value) {
        return null; // Method not implemented
    }

    public Vector2 normal() {
        return null; // Method not implemented
    }

    public Vector2 rotate(double rotation) {
        return null; // Method not implemented
    }

    /**
     * Checks if two different vectors are equal by comparing their components
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
