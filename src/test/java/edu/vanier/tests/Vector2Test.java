package edu.vanier.tests;

import edu.vanier.superspace.mathematics.Vector2;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2Test {
    
    private ArrayList<Vector2> vectors = new ArrayList<>();
    
    Vector2Test(){
        vectors.add(Vector2.of(200, 200));
        vectors.add(Vector2.of(0, 0));
        vectors.add(Vector2.of(-10, -10));
        vectors.add(Vector2.of(0, 13));
        vectors.add(Vector2.of(0, -700));
        vectors.add(Vector2.of(45, 23));
        vectors.add(Vector2.of(-19, 0));
        vectors.add(Vector2.of(298, 1928));
        vectors.add(Vector2.of(-12993, -2123));
        vectors.add(Vector2.of(12343, -29831));
        vectors.add(Vector2.of(-1283, 129381));
        vectors.add(Vector2.of(3984, 48534));
        vectors.add(Vector2.of(0, 3453));
        vectors.add(Vector2.of(96857, 3409586));
        vectors.add(Vector2.of(9685, 94754));
    }
    
    
    
    public static class VectorAssertions {
        public static void assertVectorsEqual(Vector2 vec1, Vector2 vec2) {
            
            if (vec1.equals(vec2)) return;

            fail("Expected: " + vec1 + ", got " + vec2);
        }
    }

    @Test
    void copyOf() {
        
        
        for(Vector2 vec: vectors){
            Vector2 vecLiteralCopy = Vector2.of(vec.getX(), vec.getY());
            Vector2 vec2 = Vector2.copyOf(vec);
            assertEquals(vecLiteralCopy,vec2);
        }
        
        
        
        
    }

    @Test
    void ofAngle() {
        
        ArrayList<Double> lengths = new ArrayList<>();
        
        lengths.add(0d);
        lengths.add(2d);
        lengths.add(200d);
        lengths.add(3000d);
        lengths.add(69d);
        lengths.add(-1000d);
        lengths.add(-123d);
        lengths.add(231.2d);
        lengths.add(999d);
        lengths.add(-1d);
        lengths.add(4.23617d);
        lengths.add(2931d);
        lengths.add(9483d);
        lengths.add(-1298d);
        lengths.add(1234d);
        
        for(Double length: lengths){
            for (int i = 0; i < 360; i += 5) {
                Vector2 vec = Vector2.ofAngle(i, length);
                assertEquals(vec.getX(), Math.cos(i) * length);
                assertEquals(vec.getY(), Math.sin(i) * length);
            }
        }
    }
    
    

    @Test
    void negateAssign() {
        
        for(Vector2 vec: vectors){
            Vector2 negated = Vector2.copyOf(vec);
            negated.negateAssign();
            VectorAssertions.assertVectorsEqual(Vector2.of(-vec.getX(), -vec.getY()), negated);
        }
        
        
    }
    
    
    

    @Test
    void negate() {
        
        for(Vector2 vec: vectors){
        
            Vector2 original = Vector2.copyOf(vec);
            Vector2 copy = original.negate();
            VectorAssertions.assertVectorsEqual(Vector2.of(vec.getX(), vec.getY()), original);
            VectorAssertions.assertVectorsEqual(Vector2.of(-vec.getX(), -vec.getY()), copy);
            
        }
    }

    @Test
    void addAssign() {
        
        for(int i = 0; i < vectors.size()-1; i++){
            
            final double x1 = vectors.get(i).getX();
            final double y1 = vectors.get(i).getY();
            final double x2 = vectors.get(i+1).getX();
            final double y2 = vectors.get(i+1).getY();
            Vector2 op1 = Vector2.of(x1, y1);
            Vector2 op2 = Vector2.of(x2, y2);
            op1.addAssign(op2);
            VectorAssertions.assertVectorsEqual(Vector2.of(x1 + x2, y1 + y2), op1);
            VectorAssertions.assertVectorsEqual(Vector2.of(x2, y2), op2);
            
        }
        
        
    }

    @Test
    void add() {
        for(int i = 0; i < vectors.size()-1; i++){
            
            final double x1 = vectors.get(i).getX();
            final double y1 = vectors.get(i).getY();
            final double x2 = vectors.get(i+1).getX();
            final double y2 = vectors.get(i+1).getY();
            Vector2 op1 = Vector2.of(x1, y1);
            Vector2 op2 = Vector2.of(x2, y2);
            Vector2 result = op1.add(op2);
            VectorAssertions.assertVectorsEqual(Vector2.of(x1 + x2, y1 + y2), result);
            VectorAssertions.assertVectorsEqual(Vector2.of(x1, y1), op1);
            VectorAssertions.assertVectorsEqual(Vector2.of(x2, y2), op2);
        }
    }

    @Test
    void subtractAssign() {
      for(int i = 0; i < vectors.size()-1; i++){
            
            final double x1 = vectors.get(i).getX();
            final double y1 = vectors.get(i).getY();
            final double x2 = vectors.get(i+1).getX();
            final double y2 = vectors.get(i+1).getY();
            Vector2 op1 = Vector2.of(x1, y1);
            Vector2 op2 = Vector2.of(x2, y2);
            op1.subtractAssign(op2);
            VectorAssertions.assertVectorsEqual(Vector2.of(x1 - x2, y1 - y2), op1);
            VectorAssertions.assertVectorsEqual(Vector2.of(x2, y2), op2);
      }
    }

    @Test
    void subtract() {
        for(int i = 0; i < vectors.size()-1; i++){
            
            final double x1 = vectors.get(i).getX();
            final double y1 = vectors.get(i).getY();
            final double x2 = vectors.get(i+1).getX();
            final double y2 = vectors.get(i+1).getY();
            Vector2 op1 = Vector2.of(x1, y1);
            Vector2 op2 = Vector2.of(x2, y2);
            Vector2 result = op1.subtract(op2);
            VectorAssertions.assertVectorsEqual(Vector2.of(x1 - x2, y1 - y2), result);
            VectorAssertions.assertVectorsEqual(Vector2.of(x1, y1), op1);
            VectorAssertions.assertVectorsEqual(Vector2.of(x2, y2), op2);
        }
    }

    @Test
    void magnitude() {
        
        for(Vector2 vec: vectors){
            double magnitude = vec.magnitude();
            assertEquals(Math.sqrt(Math.pow(vec.getX(),2) + Math.pow(vec.getY(),2)), magnitude);
        }

        
    }

    @Test
    void sqrMagnitude() {
        
        for(Vector2 vec: vectors){
            double magnitude = vec.sqrMagnitude();
            assertEquals(Math.pow(vec.getX(),2) + Math.pow(vec.getY(),2), magnitude);
        }
        
        
    }

    @Test
    void normalized() {
        
        for(Vector2 vec: vectors){
            
            double magnitude = vec.magnitude();
            
            //the one special case where vector (0,0) exists
            if (magnitude==0) magnitude = 1; 
            
            Vector2 original = Vector2.copyOf(vec);
            Vector2 copy = original.normalized();
            VectorAssertions.assertVectorsEqual(vec, original);
            VectorAssertions.assertVectorsEqual(Vector2.of(vec.getX() / magnitude, vec.getY() / magnitude), copy);
        
        }
        
        
    }

    @Test
    void normalize() {
        
        for(Vector2 vec: vectors){
            
            double magnitude = vec.magnitude();
            
            //the one special case where vector (0,0) exists
            if (magnitude==0) magnitude = 1; 
            
            Vector2 original = Vector2.copyOf(vec);
            original.normalize();
            VectorAssertions.assertVectorsEqual(Vector2.of(vec.getX() / magnitude, vec.getY() / magnitude), original);
            
        }
        
        
    }

    @Test
    void divide() {
        
        ArrayList<Double> divi = new ArrayList<>();
        divi.add(12.7d);
        divi.add(3.45d);
        divi.add(1d);
        divi.add(-23d);
        divi.add(214d);
        divi.add(-274d);
        divi.add(98d);
        divi.add(-9754d);
        divi.add(6483d);
        divi.add(73d);
        divi.add(87619d);
        divi.add(837491d);
        divi.add(-8421d);
        divi.add(-213d);
        divi.add(28742d);
        
        for(Vector2 vec: vectors){
            for(int i = 0; i < divi.size(); i++){

                Vector2 op1 = Vector2.copyOf(vec);
                Vector2 result = op1.divide(divi.get(i));
                VectorAssertions.assertVectorsEqual(Vector2.of(vec.getX() / divi.get(i), vec.getY() / divi.get(i)), result);
                VectorAssertions.assertVectorsEqual(Vector2.copyOf(vec), op1);

            }
            
            Vector2 op1 = Vector2.copyOf(vec);
            Vector2 result = op1.divide(0);
            VectorAssertions.assertVectorsEqual(Vector2.of(0,0), result);
            VectorAssertions.assertVectorsEqual(Vector2.copyOf(vec), op1);
            
        }
        
    }
    
    @Test
    void divideAssign(){
        
        ArrayList<Double> divi = new ArrayList<>();
        divi.add(12.7d);
        divi.add(3.45d);
        divi.add(1d);
        divi.add(-23d);
        divi.add(214d);
        divi.add(-274d);
        divi.add(98d);
        divi.add(-9754d);
        divi.add(6483d);
        divi.add(73d);
        divi.add(87619d);
        divi.add(837491d);
        divi.add(-8421d);
        divi.add(-213d);
        divi.add(28742d);
        
        for(Vector2 vec: vectors){
            for(int i = 0; i < divi.size(); i++){

                Vector2 op1 = Vector2.copyOf(vec);
                Double divideable = divi.get(i);
                op1.divideAssign(divideable);
                VectorAssertions.assertVectorsEqual(op1,
                        Vector2.of(vec.getX()/divideable, vec.getY()/divideable));
                assertEquals(divi.get(i), divideable);

            }
            
            Vector2 op1 = Vector2.copyOf(vec);
            op1.divideAssign(0);
            VectorAssertions.assertVectorsEqual(Vector2.of(0,0), op1);
            
        }
        
    }

    @Test
    void clampMagnitude() {
        
        ArrayList<Double> clamps = new ArrayList<>();
        clamps.add(1d);
        clamps.add(9d);
        clamps.add(1d);
        clamps.add(219d);
        clamps.add(134d);
        clamps.add(1d);
        clamps.add(11d);
        clamps.add(440d);
        clamps.add(10d);
        clamps.add(187d);
        clamps.add(721d);
        clamps.add(358d);
        clamps.add(658d);
        clamps.add(712d);
        
        for(Vector2 vec: vectors){
            
            Vector2 vecCopy = Vector2.copyOf(vec);
            
            for(int i = 0; i < clamps.size(); i++){
                
                Vector2 clampVec = vecCopy.clampMagnitude(clamps.get(i));
                
                if(vecCopy.magnitude()<=clamps.get(i)){
                    VectorAssertions.assertVectorsEqual(clampVec, vecCopy);
                }else{
                    assertEquals(Math.round(clampVec.magnitude()), clamps.get(i));
                }
            }
            
        }
    }

    @Test
    void distanceTo() {
        
        for(int i =0; i<vectors.size()-1; i++){
            
            double diff = Vector2.of(vectors.get(i+1).getX()-vectors.get(i).getX(), vectors.get(i+1).getY()-vectors.get(i).getY()).magnitude();
            assertEquals(diff, vectors.get(i).distanceTo(vectors.get(i+1)));
            
        }
    }

    @Test
    void dot() {
        
        for(int i = 0; i < vectors.size()-1; i++){
            
            double dot = vectors.get(i).dot(vectors.get(i+1));
            assertEquals(dot, vectors.get(i).getX() * vectors.get(i+1).getX() + vectors.get(i).getY() * vectors.get(i+1).getY());
            
        }
        
        
        
    }

    @Test
    void lerp() {
        
        ArrayList<Double> evaluators = new ArrayList<>();
        evaluators.add(0.1d);
        evaluators.add(0.2d);
        evaluators.add(0.7d);
        evaluators.add(0.5d);
        evaluators.add(0.8d);
        evaluators.add(1.3d);
        evaluators.add(10.1d);
        evaluators.add(29.1d);
        evaluators.add(4d);
        evaluators.add(8d);
        evaluators.add(721d);
        evaluators.add(241d);
        evaluators.add(0.9d);
        evaluators.add(123d);
        
        for(int i = 0; i < vectors.size()-1; i++){
            
            for(Double evaluator: evaluators){
                
                Vector2 lerped = Vector2.lerp(vectors.get(i),vectors.get(i+1), evaluator);
                VectorAssertions.assertVectorsEqual(Vector2.of(vectors.get(i).getX()+(vectors.get(i+1).getX()-vectors.get(i).getX())*evaluator,
                        vectors.get(i).getY()+(vectors.get(i+1).getY()-vectors.get(i).getY())*evaluator), lerped);
                
            }
            
        }
        
    }

    @Test
    void perpendicular() {
        
        for(Vector2 vec: vectors){
            Vector2 perpendicular = vec.perpendicular();
            VectorAssertions.assertVectorsEqual(Vector2.of(vec.getY(), -vec.getX()), perpendicular);
        }
    }
    
    @Test
    void negateOnRenderAxis(){
        
        for(Vector2 vec: vectors){
            
            Vector2 original = Vector2.copyOf(vec);
            Vector2 compare = original.negateOnRenderAxis();
            VectorAssertions.assertVectorsEqual(Vector2.of(vec.getX(), vec.getY()), original);
            VectorAssertions.assertVectorsEqual(Vector2.of(-vec.getX(),vec.getY()), compare);
            
        }
        
    }
    
    @Test
    void multiply() {
        
        ArrayList<Double> multi = new ArrayList<>();
        multi.add(12.7d);
        multi.add(3.45d);
        multi.add(1d);
        multi.add(-23d);
        multi.add(214d);
        multi.add(-274d);
        multi.add(98d);
        multi.add(-9754d);
        multi.add(6483d);
        multi.add(73d);
        multi.add(87619d);
        multi.add(837491d);
        multi.add(-8421d);
        multi.add(-213d);
        multi.add(28742d);
        
        for(Vector2 vec: vectors){
            for(int i = 0; i < multi.size(); i++){

                Vector2 op1 = Vector2.copyOf(vec);
                Vector2 result = op1.multiply(multi.get(i));
                VectorAssertions.assertVectorsEqual(Vector2.of(vec.getX() * multi.get(i), vec.getY() * multi.get(i)), result);
                VectorAssertions.assertVectorsEqual(Vector2.copyOf(vec), op1);

            }
            
        }
        
    }
    
    @Test
    void multiplyAssign(){
        
        ArrayList<Double> multi = new ArrayList<>();
        multi.add(12.7d);
        multi.add(3.45d);
        multi.add(1d);
        multi.add(-23d);
        multi.add(214d);
        multi.add(-274d);
        multi.add(98d);
        multi.add(-9754d);
        multi.add(6483d);
        multi.add(73d);
        multi.add(87619d);
        multi.add(837491d);
        multi.add(-8421d);
        multi.add(-213d);
        multi.add(28742d);
        
        for(Vector2 vec: vectors){
            for(int i = 0; i < multi.size(); i++){

                Vector2 op1 = Vector2.copyOf(vec);
                Double multipliable = multi.get(i);
                op1.multiplyAssign(multipliable);
                VectorAssertions.assertVectorsEqual(op1,
                        Vector2.of(vec.getX()*multipliable, vec.getY()*multipliable));
                assertEquals(multi.get(i), multipliable);

            }
            
        }
        
    }
    
    @Test
    void signedAngle() {
        
        for(Vector2 vec: vectors){
            
            assertEquals(Math.atan2(vec.getY(), vec.getX()), vec.signedAngle());
            
        }
    }
    
    void angle(){
        
        for(Vector2 vec: vectors){
            
            assertEquals(Math.abs(Math.atan2(vec.getY(), vec.getX())), vec.angle());
            
        }
        
    }
    
    
    void rotate(){
        
        for(Vector2 vec: vectors){
            for(int i = 0; i<360; i++){
                
                double angle = Math.toRadians(i);
                
                Vector2 turned = Vector2.of(vec.getX() * Math.cos(angle) - vec.getY() * Math.sin(angle),
                        vec.getX()*Math.sin(angle)+ vec.getY()*Math.cos(angle));
                
                VectorAssertions.assertVectorsEqual(turned, vec.rotate(i));
                        
            }
        }
        
    }
    
    
}