package edu.vanier.superspace.mathematics;

public class Vector2 {
    private double x;
    private double y;

    public Vector2() {

    }

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2 of(double num1, double num2) {
        return null; // Method not implemented
    }

    public Vector2 ofAngle(double firstAngle, double secondAngle) {
        return null; // Method not implemented
    }

    public Vector2 negateAssign() {
        this.x = -this.x;
        this.y = -this.y;
        return this;
    }

    public Vector2 negate() {
        return new Vector2(-this.x, -this.y);
    }

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

    public Vector2 multiplyAssign(Vector2 other) {
        this.x *= other.x;
        this.y *= other.y;
        return this;
    }

    public Vector2 multiply(double value) {
        return new Vector2(x * value, y * value);
    }

    public Vector2 divideAssign(Vector2 other) {
        this.x /= other.x;
        this.y /= other.y;
        return this;
    }

    public Vector2 divide(double value) {
        return new Vector2(x / value, y / value);
    }

    public Vector2 clamp(double value) {
        return null; // Method not implemented
    }

    public double distance(Vector2 otherVector) {
        return 0; // Method not implemented
    }

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

    public boolean equals(Vector2 otherVector) {
        return this.x == otherVector.x && this.y == otherVector.y;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
