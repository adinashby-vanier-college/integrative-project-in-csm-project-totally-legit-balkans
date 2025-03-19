/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.superspace.utils.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import edu.vanier.superspace.simulation.Simulation;
import java.lang.reflect.Type;

/**
 *
 * @author crist
 */
public class SimulationDeserializer implements JsonDeserializer<Simulation>{

    @Override
    public Simulation deserialize(JsonElement json, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        
        JsonObject deserialized = json.getAsJsonObject();
        
        Simulation object = new Simulation();
        
        //TO BE WRITTEN
        
        
        return object;
    }
    
}
