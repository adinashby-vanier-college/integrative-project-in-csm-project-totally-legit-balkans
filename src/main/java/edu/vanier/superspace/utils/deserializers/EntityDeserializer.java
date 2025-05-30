package edu.vanier.superspace.utils.deserializers;

import com.google.gson.*;
import edu.vanier.superspace.simulation.Entity;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

import edu.vanier.superspace.simulation.components.Component;
import edu.vanier.superspace.utils.serializers.SerializationExclusionStrategy;
import lombok.SneakyThrows;

/**
 * Deserializer for the entity class
 */
public class EntityDeserializer implements JsonDeserializer<Entity>{
    /**
     * Deserializes an entity
     * @param json the json element to be deserialized
     * @param type the type
     * @param jdc the json deserialization context
     * @return the entity value
     * @throws JsonParseException thrown when there is a json parsing issue
     */
    @Override @SneakyThrows
    public Entity deserialize(JsonElement json, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        Entity entity = new Entity();
        JsonObject jsonObject = json.getAsJsonObject();
        
        try {
            for (Field declaredField : Entity.class.getDeclaredFields()) {
                if (SerializationExclusionStrategy.toSkip(new FieldAttributes(declaredField))) {
                    continue;
                }

                if (declaredField.getName().equals("components")) {
                    Component[] components = jdc.deserialize(jsonObject.getAsJsonArray("components"), Component[].class);
                    for (Component component : components) {
                        entity.addComponent(component);
                    }
                    continue;
                }

                declaredField.setAccessible(true);
                declaredField.set(entity, jdc.deserialize(jsonObject.get(declaredField.getName()), declaredField.getType()));
            }

            System.out.println(entity.getName());
            return entity;
        } catch(Exception exception){
            System.out.println(exception.getMessage());
        }
        
        return null;
    }
}
