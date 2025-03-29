package edu.vanier.superspace.utils.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import edu.vanier.superspace.simulation.Simulation;
import edu.vanier.superspace.utils.serializers.SimulationSerializer;
import java.lang.reflect.Type;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimulationDeserializer implements JsonDeserializer<Simulation>{
    
     private final static Logger logger = LoggerFactory.getLogger(SimulationDeserializer.class);

    @Override @SneakyThrows
    public Simulation deserialize(JsonElement json, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        
        JsonObject deserialized = json.getAsJsonObject();
        Simulation simulation = Simulation.getInstance();
        
        try {
            if (!deserialized.get("version").getAsString().equals(SimulationSerializer.getVersion())) {
                throw new Exception("The simulation version is invalid!");
            }
            DeserializerHelper.readField(simulation, Simulation.class, deserialized, jdc);
            return simulation;
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        return null;
    }
    
}
