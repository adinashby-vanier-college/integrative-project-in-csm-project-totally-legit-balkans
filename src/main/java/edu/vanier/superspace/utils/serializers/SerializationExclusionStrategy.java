package edu.vanier.superspace.utils.serializers;

import annotations.ToSerialize;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class SerializationExclusionStrategy implements ExclusionStrategy{

    @Override
    public boolean shouldSkipField(FieldAttributes fieldAtt) {
        return toSkip(fieldAtt);
    }

    @Override
    public boolean shouldSkipClass(Class<?> type) {
        return false;
    }
    
    
    public static boolean toSkip(FieldAttributes fieldAtt){
      return (fieldAtt.getDeclaringClass().getAnnotation(ToSerialize.class) == null 
                && fieldAtt.getAnnotation(ToSerialize.class) == null);  
    }
    
}
