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

public class JsonHelper {
    
    private static final Gson gsonSerializer = new GsonBuilder()
            .addSerializationExclusionStrategy(new SerializationExclusionStrategy())
            .registerTypeHierarchyAdapter(Component.class,new ComponentSerializer())
            .registerTypeHierarchyAdapter(Entity.class, new EntitySerializer())
            .registerTypeHierarchyAdapter(Simulation.class, new SimulationSerializer())
            .create();
    
    private static final Gson gsonDeserializer = new GsonBuilder()
            .addSerializationExclusionStrategy(new SerializationExclusionStrategy())
            .registerTypeHierarchyAdapter(Component.class, new ComponentDeserializer())
            .registerTypeHierarchyAdapter(Entity.class, new EntityDeserializer())
            .registerTypeHierarchyAdapter(Simulation.class, new SimulationDeserializer())
            .create();
    
    public static <T> T deserialize(String str, Class<T> ofType) {
        return gsonDeserializer.fromJson(str, ofType);
        
    }

    public static String serialize(Object object) {
        return gsonSerializer.toJson(object);
    }
}
