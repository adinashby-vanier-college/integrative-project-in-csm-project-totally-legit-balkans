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
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author crist
 */
public class SimulationDeserializer implements JsonDeserializer<Simulation>{
    
     private final static Logger logger = LoggerFactory.getLogger(SimulationDeserializer.class);

    @Override @SneakyThrows
    public Simulation deserialize(JsonElement json, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        
        JsonObject deserialized = json.getAsJsonObject();
        
        Simulation object = new Simulation();
        
        if(deserialized.get("version").equals("0")){
            
            for(var field : Class.forName("Simulation").getFields()){
                
                field.setAccessible(true);
                Object deserializedType = jdc.deserialize(deserialized.get(field.getName()), field.getType());
                field.set(object,deserializedType);
            }
            
            return object;
            
        }else{
            
           logger.info("The simulation version is invalid!"); 
           return null;
            
        }
    }
    
}
