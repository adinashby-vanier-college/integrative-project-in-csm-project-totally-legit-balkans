package edu.vanier.superspace.utils.deserializers;

import com.google.gson.FieldAttributes;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import edu.vanier.superspace.utils.serializers.SerializationExclusionStrategy;
import lombok.SneakyThrows;

/**
 * Helper for the deserializer
 */
public class DeserializerHelper {
    /**
     * Reads a field
     * @param object the object
     * @param objectClass the object class
     * @param deserialized the deserialized object
     * @param jdc the json deserialization context
     */
    @SneakyThrows
    public static void readField(Object object, Class<?> objectClass, JsonObject deserialized, JsonDeserializationContext jdc) {
        for (var field : objectClass.getDeclaredFields()) {
            if (!(SerializationExclusionStrategy.toSkip(new FieldAttributes(field)))) {
                field.setAccessible(true);
                Object deserializedType = jdc.deserialize(deserialized.get(field.getName()), field.getType());
                field.set(object, deserializedType);
            }
        }
    }
}
