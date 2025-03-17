package edu.vanier.superspace.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.vanier.superspace.simulation.components.Component;
import edu.vanier.superspace.utils.serializers.ComponentSerializer;

public class JsonHelper {
    
    private static Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(Component.class,new ComponentSerializer()).create();
    
    public static <T> T deserialize(String str, Class<T> ofType) {
        return gson.fromJson(str, ofType);
    }

    public static String serialize(Object oject) {
        return gson.toJson(oject);
    }
}
