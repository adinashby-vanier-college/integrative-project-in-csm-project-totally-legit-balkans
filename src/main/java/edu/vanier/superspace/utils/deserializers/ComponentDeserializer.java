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
import edu.vanier.superspace.simulation.components.Component;
import java.lang.reflect.Type;
import lombok.SneakyThrows;

/**
 *
 * @author crist
 */
public class ComponentDeserializer implements JsonDeserializer<Component>{

    @Override @SneakyThrows
    public Component deserialize(JsonElement json, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        
        JsonObject deserialized = json.getAsJsonObject();
        
        String fullyQualifiedName = deserialized.get("className").getAsString();
        
        Class<?> objectClass = Class.forName(fullyQualifiedName);
        Component object = (Component)objectClass.getConstructor().newInstance();
        
        for(var field : objectClass.getDeclaredFields()){
            
            Object deserializedType = jdc.deserialize(deserialized.get(field.getName()), field.getType());
            field.setAccessible(true);
            field.set(object,deserializedType);
            
        }
        
        
        return object;
        
    }
    
}
