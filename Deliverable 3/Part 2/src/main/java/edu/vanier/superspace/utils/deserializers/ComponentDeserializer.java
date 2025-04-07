package edu.vanier.superspace.utils.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import edu.vanier.superspace.simulation.components.Component;
import java.lang.reflect.Type;
import lombok.SneakyThrows;

public class ComponentDeserializer implements JsonDeserializer<Component>{

    @Override @SneakyThrows
    public Component deserialize(JsonElement json, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        
        JsonObject deserialized = json.getAsJsonObject();
        String fullyQualifiedName = deserialized.get("className").getAsString();
        Class<?> objectClass = Class.forName(fullyQualifiedName);
        
        try {
            if (objectClass.getConstructor().getParameterCount() != 0){ 
                throw new Exception();
            }
            Component object = (Component)objectClass.getConstructor().newInstance();
            DeserializerHelper.readField(object, objectClass, deserialized, jdc);
            return object;
        } catch(Exception exception){
            System.out.println("Invalid number of parameters in constructor!");
        }
        
        return null;
    }
    
}
