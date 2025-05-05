package edu.vanier.superspace.utils.serializers;

import edu.vanier.superspace.annotations.ToSerialize;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * Strategy for exclusion in serialization
 */
public class SerializationExclusionStrategy implements ExclusionStrategy{
    /**
     * Checks if a field should be skipped
     * @param fieldAtt the field attribute
     * @return true or false
     */
    @Override
    public boolean shouldSkipField(FieldAttributes fieldAtt) {
        return toSkip(fieldAtt);
    }

    /**
     * Checks if it should skip class
     * @param type the type
     * @return true or false
     */
    @Override
    public boolean shouldSkipClass(Class<?> type) {
        return false;
    }

    /**
     * Checks if it must be skipped
     * @param fieldAtt the field attribute
     * @return true or false
     */
    public static boolean toSkip(FieldAttributes fieldAtt){
      return (fieldAtt.getDeclaringClass().getAnnotation(ToSerialize.class) == null 
                && fieldAtt.getAnnotation(ToSerialize.class) == null);  
    }
}
