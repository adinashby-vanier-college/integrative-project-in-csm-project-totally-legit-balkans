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
 * Deserializes any given component
 */
public class ComponentDeserializer implements JsonDeserializer<Component>{
    /**
     * Deserializes a component
     * @param json the json element to be deserialized
     * @param type the type of the component
     * @param jdc json deserialization context
     * @return a component
     * @throws JsonParseException exception caused by a json parse issue
     */
    @Override @SneakyThrows
    public Component deserialize(JsonElement json, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        
        JsonObject deserialized = json.getAsJsonObject();
        String fullyQualifiedName = deserialized.get("className").getAsString();
        Class<?> objectClass = Class.forName(fullyQualifiedName);
        
        try {
            if (objectClass.getConstructors()[0].getParameterCount() != 0) {
                throw new Exception();
            }
            Component object = (Component)objectClass.getConstructors()[0].newInstance();
            DeserializerHelper.readField(object, objectClass, deserialized, jdc);
            return object;
        } catch (Exception exception) {
            System.out.println(1);
            System.out.println(exception.getMessage());
            System.out.println("Invalid number of parameters in constructor!");
        }
        
        return null;
    }
}
