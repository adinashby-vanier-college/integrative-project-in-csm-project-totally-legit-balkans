/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.superspace.utils.serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import edu.vanier.superspace.simulation.Simulation;
import java.lang.reflect.Type;
import lombok.SneakyThrows;

/**
 *
 * @author crist
 */
public class SimulationSerializer implements JsonSerializer<Simulation>{

    @Override @SneakyThrows
    public JsonElement serialize(Simulation t, Type type, JsonSerializationContext jsc) {
        
        JsonObject serialized = new JsonObject();
        serialized.add("version", jsc.serialize("0"));
        
        for(var field : t.getClass().getDeclaredFields()){
            
            field.setAccessible(true);
            serialized.add(field.getName(), jsc.serialize(field.get(t)));
            
        }
        
        return serialized;
        
    }
    
}
