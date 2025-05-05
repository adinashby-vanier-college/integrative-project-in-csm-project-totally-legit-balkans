/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.tests;

import edu.vanier.superspace.mathematics.Constants;
import edu.vanier.superspace.mathematics.Physics;
import edu.vanier.superspace.mathematics.Vector2;
import java.util.ArrayList;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author crist
 */
public class PhysicsTest {
    
    @Test
    void computeForce(){
        
        ArrayList<Double> distances = new ArrayList<>();
        distances.add(4d);
        distances.add(12d);
        distances.add(300d);
        distances.add(11d);
        distances.add(821d);
        distances.add(4831d);
        distances.add(1293d);
        distances.add(9743d);
        distances.add(6542d);
        distances.add(87d);
        distances.add(9d);
        distances.add(998d);
        distances.add(123d);
        distances.add(1283d);
        distances.add(434d);
        
        ArrayList<Double> mass1 = new ArrayList<>();
        mass1.add(12d);
        mass1.add(321d);
        mass1.add(1d);
        mass1.add(98412d);
        mass1.add(123d);
        mass1.add(43d);
        mass1.add(456d);
        mass1.add(987d);
        mass1.add(54d);
        mass1.add(45d);
        mass1.add(81d);
        mass1.add(100d);
        mass1.add(109d);
        mass1.add(214d);
        mass1.add(764d);
        
        ArrayList<Double> mass2 = new ArrayList<>();
        mass2.add(7812d);
        mass2.add(18293d);
        mass2.add(342d);
        mass2.add(654d);
        mass2.add(9189d);
        mass2.add(432d);
        mass2.add(123d);
        mass2.add(654d);
        mass2.add(1726d);
        mass2.add(673d);
        mass2.add(422d);
        mass2.add(2749d);
        mass2.add(2894d);
        mass2.add(1982d);
        mass2.add(8913d);
        
        for(int i = 0; i < distances.size(); i++){
            
            double original = Physics.computeForce(distances.get(i),
                    mass1.get(i), mass2.get(i));
            
            double compare = (Constants.SIMULATION_GRAVITATIONAL_CONSTANT* mass1.get(i)* mass2.get(i))/
                    (Math.pow(distances.get(i), 2));
            
             assertEquals(original, compare);
        }
        
    }
    
}
