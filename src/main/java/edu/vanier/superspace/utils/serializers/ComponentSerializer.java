package edu.vanier.superspace.utils.serializers;

import com.google.gson.FieldAttributes;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import edu.vanier.superspace.simulation.components.Component;
import java.lang.reflect.Type;
import lombok.SneakyThrows;

public class ComponentSerializer implements JsonSerializer<Component>{

    @Override @SneakyThrows
    public JsonElement serialize(Component t, Type type, JsonSerializationContext jsc) {
        JsonObject serialized = new JsonObject();
        serialized.add("className", jsc.serialize(t.getClass()));
        SerializerHelper.assignField(t, serialized, jsc);
        return serialized;
    }
    
}
