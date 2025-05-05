package edu.vanier.superspace.utils.serializers;


import com.google.gson.FieldAttributes;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import edu.vanier.superspace.utils.serializers.SerializationExclusionStrategy;
import lombok.SneakyThrows;

/**
 * Helps with serializing
 */
public class SerializerHelper {
    /**
     * Assigns a field
     * @param t the object
     * @param serialized the serialized json object
     * @param jsc the json serialization context
     */
    @SneakyThrows
    public static void assignField(Object t, JsonObject serialized, JsonSerializationContext jsc) {
        for (var field : t.getClass().getDeclaredFields()){
            
            if (!(SerializationExclusionStrategy.toSkip(new FieldAttributes(field)))){
                field.setAccessible(true);
                serialized.add(field.getName(), jsc.serialize(field.get(t)));
            }
        }
    }
}
