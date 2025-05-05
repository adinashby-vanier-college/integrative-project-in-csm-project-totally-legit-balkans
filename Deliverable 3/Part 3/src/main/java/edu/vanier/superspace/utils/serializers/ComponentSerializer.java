package edu.vanier.superspace.utils.serializers;

import com.google.gson.FieldAttributes;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import edu.vanier.superspace.simulation.components.Component;
import java.lang.reflect.Type;
import lombok.SneakyThrows;

/**
 * Serializes any given component
 */
public class ComponentSerializer implements JsonSerializer<Component>{
    /**
     * Serializes a component
     * @param t the component to be serialized
     * @param type the type
     * @param jsc the json serialization context
     * @return json element with the component serialized
     */
    @Override @SneakyThrows
    public JsonElement serialize(Component t, Type type, JsonSerializationContext jsc) {
        JsonObject serialized = new JsonObject();
        serialized.addProperty("className", type.getTypeName());
        SerializerHelper.assignField(t, serialized, jsc);
        return serialized;
    }
    
}
