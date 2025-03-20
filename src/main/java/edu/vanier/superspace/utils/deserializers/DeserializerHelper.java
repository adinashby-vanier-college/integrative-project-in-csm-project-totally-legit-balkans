package edu.vanier.superspace.utils.deserializers;

import com.google.gson.FieldAttributes;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import edu.vanier.superspace.utils.serializers.SerializationExclusionStrategy;
import lombok.SneakyThrows;


public class DeserializerHelper {
    
    @SneakyThrows
    public static void readField(Object object, Class<?> objectClass,
            JsonObject deserialized, JsonDeserializationContext jdc){
        
        for(var field : objectClass.getDeclaredFields()){
            
            if (!(SerializationExclusionStrategy.toSkip(new FieldAttributes(field)))){
                field.setAccessible(true);
                Object deserializedType = jdc.deserialize(deserialized.get(field.getName()), field.getType());
                field.set(object,deserializedType);
            }
            
        }
        
    }
    
}
