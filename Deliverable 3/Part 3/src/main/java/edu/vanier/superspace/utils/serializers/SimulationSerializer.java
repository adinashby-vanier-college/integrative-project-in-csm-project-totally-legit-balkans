package edu.vanier.superspace.utils.serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import edu.vanier.superspace.simulation.Simulation;
import java.lang.reflect.Type;
import lombok.Getter;
import lombok.SneakyThrows;

/**
 * Serializer for a simulation
 */
public class SimulationSerializer implements JsonSerializer<Simulation>{
    /**
     * Data field
     */
    @Getter
    private static String version = "0";

    /**
     * Serializes a simulation
     * @param t the simulation to be serialized
     * @param type the type
     * @param jsc json serialization context
     * @return the json element
     */
    @Override @SneakyThrows
    public JsonElement serialize(Simulation t, Type type, JsonSerializationContext jsc) {
        
        JsonObject serialized = new JsonObject();
        serialized.add("version", jsc.serialize(version));
        SerializerHelper.assignField(t, serialized, jsc);
        return serialized;
        
    }
}
