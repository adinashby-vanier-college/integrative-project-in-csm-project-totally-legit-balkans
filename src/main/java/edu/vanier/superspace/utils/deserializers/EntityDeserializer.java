package edu.vanier.superspace.utils.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import edu.vanier.superspace.simulation.Entity;
import java.lang.reflect.Type;
import lombok.SneakyThrows;


public class EntityDeserializer implements JsonDeserializer<Entity>{

    @Override @SneakyThrows
    public Entity deserialize(JsonElement json, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        
        JsonObject deserialized = json.getAsJsonObject();
        String fullyQualifiedName = deserialized.get("className").getAsString();
        Class<?> objectClass = Class.forName(fullyQualifiedName);
        
        try{
            if(objectClass.getConstructor().getParameterCount() != 0) 
                throw new Exception();
            Entity object = (Entity)objectClass.getConstructor().newInstance();
            DeserializerHelper.readField(object, objectClass, deserialized, jdc);

            for(int i = 0; i < object.getComponents().size();i++){

                for(var field : objectClass.getDeclaredFields()){

                    if(field.getClass().equals(object.getComponents().get(i).getClass())){

                        field.setAccessible(true);
                        field.set(object,object.getComponents().get(i));

                    }
                }

            }
            return object;
        }catch(Exception exception){
            System.out.println("Invalid number of parameters in constructor!");
        }
        
        return null;
        
        
        
    }
    
}
