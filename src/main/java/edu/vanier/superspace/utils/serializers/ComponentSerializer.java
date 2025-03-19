package edu.vanier.superspace.utils.serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import edu.vanier.superspace.simulation.components.Component;
import java.lang.reflect.Type;
import lombok.SneakyThrows;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author crist
 */

public class ComponentSerializer implements JsonSerializer<Component>{

    @Override @SneakyThrows
    public JsonElement serialize(Component t, Type type, JsonSerializationContext jsc) {
        JsonObject serialized = new JsonObject();
        serialized.add("className", jsc.serialize(t.getClass()));
        
        for (var field : t.getClass().getDeclaredFields()){
            
            serialized.add(field.getName(), jsc.serialize(field.get(t)));
            
        }
        
        return serialized;
    }
    
}
