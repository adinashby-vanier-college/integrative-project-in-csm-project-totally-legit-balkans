package edu.vanier.superspace.utils.serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import edu.vanier.superspace.simulation.Entity;
import java.lang.reflect.Type;
import lombok.SneakyThrows;

/**
 * Serializes a given entity
 */
public class EntitySerializer implements JsonSerializer<Entity>{
    /**
     * Serialized a given entity
     * @param t the entity
     * @param type the type
     * @param jsc the json serialization context
     * @return the json of the entity
     */
    @Override @SneakyThrows
    public JsonElement serialize(Entity t, Type type, JsonSerializationContext jsc) {
        JsonObject serialized = new JsonObject();
        serialized.addProperty("className", type.getTypeName());
        SerializerHelper.assignField(t, serialized, jsc);
        return serialized;
    }
}
