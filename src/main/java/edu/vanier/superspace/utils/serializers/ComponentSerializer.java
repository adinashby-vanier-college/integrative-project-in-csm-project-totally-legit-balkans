package edu.vanier.superspace.utils.serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import edu.vanier.superspace.simulation.components.Component;
import java.lang.reflect.Type;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author crist
 */
public class ComponentSerializer implements JsonSerializer<Component>{

    @Override
    public JsonElement serialize(Component t, Type type, JsonSerializationContext jsc) {
        JsonObject serialized = new JsonObject();
        serialized.add("Version", jsc.serialize(0));
        
        return null;
    }
    
}
