package edu.vanier.superspace.utils;

import edu.vanier.superspace.utils.serializers.SerializationExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.vanier.superspace.simulation.Entity;
import edu.vanier.superspace.simulation.Simulation;
import edu.vanier.superspace.simulation.components.Component;
import edu.vanier.superspace.utils.deserializers.ComponentDeserializer;
import edu.vanier.superspace.utils.deserializers.EntityDeserializer;
import edu.vanier.superspace.utils.deserializers.SimulationDeserializer;
import edu.vanier.superspace.utils.serializers.ComponentSerializer;
import edu.vanier.superspace.utils.serializers.EntitySerializer;
import edu.vanier.superspace.utils.serializers.SimulationSerializer;
import lombok.Getter;

/**
 * Class is used for helping with Json
 */
public class JsonHelper {
    /**
     * Instance of a defaultGson
     */
    @Getter
    private static final Gson defaultGson = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .create();

    /**
     * Instance of a gsonSerializer
     */
    private static final Gson gsonSerializer = new GsonBuilder()
            .addSerializationExclusionStrategy(new SerializationExclusionStrategy())
            .registerTypeHierarchyAdapter(Component.class,new ComponentSerializer())
            .registerTypeHierarchyAdapter(Entity.class, new EntitySerializer())
            .registerTypeHierarchyAdapter(Simulation.class, new SimulationSerializer())
            .setPrettyPrinting()
            .create();

    /**
     * Instance of a gsonDeserializer
     */
    private static final Gson gsonDeserializer = new GsonBuilder()
            .addSerializationExclusionStrategy(new SerializationExclusionStrategy())
            .registerTypeHierarchyAdapter(Component.class, new ComponentDeserializer())
            .registerTypeHierarchyAdapter(Entity.class, new EntityDeserializer())
            .registerTypeHierarchyAdapter(Simulation.class, new SimulationDeserializer())
            .create();

    /**
     * Deserializes a json file
     */
    public static <T> T deserialize(String str, Class<T> ofType) {
        return gsonDeserializer.fromJson(str, ofType);
    }

    /**
     * Serializes to a json file
     * @param object the object to serialize
     * @return a string
     */
    public static String serialize(Object object) {
        return gsonSerializer.toJson(object);
    }

    /**
     * Brings to json
     * @param object an object
     * @return a string
     */
    public static String toJson(Object object) {
        return defaultGson.toJson(object);
    }
    
}
