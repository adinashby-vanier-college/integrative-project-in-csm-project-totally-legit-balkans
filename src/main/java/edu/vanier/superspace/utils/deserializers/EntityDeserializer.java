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
import edu.vanier.superspace.simulation.Entity;
import java.lang.reflect.Type;
import lombok.SneakyThrows;

/**
 *
 * @author crist
 */
public class EntityDeserializer implements JsonDeserializer<Entity>{

    @Override @SneakyThrows
    public Entity deserialize(JsonElement json, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        
        JsonObject deserialized = json.getAsJsonObject();
        
        String fullyQualifiedName = deserialized.get("className").getAsString();
        
        Class<?> objectClass = Class.forName(fullyQualifiedName);
        Entity object = (Entity)objectClass.getConstructor().newInstance();
        
        for(var field : objectClass.getDeclaredFields()){
            
            field.setAccessible(true);
            Object deserializedType = jdc.deserialize(deserialized.get(field.getName()), field.getType());
            field.set(object,deserializedType);
            
        }
        
        
            
        

        for(int i = 0; i < object.getComponents().size();i++){

            for(var field : objectClass.getDeclaredFields()){

                if(field.getClass().equals(object.getComponents().get(i).getClass())){

                    field.setAccessible(true);
                    field.set(object,object.getComponents().get(i));

                }
            }

        }

        
            
        
        
        
        return object;
        
        
        
    }
    
}
