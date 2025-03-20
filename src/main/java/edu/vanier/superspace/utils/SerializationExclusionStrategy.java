/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.superspace.utils;

import annotations.ToSerialize;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 *
 * @author crist
 */
public class SerializationExclusionStrategy implements ExclusionStrategy{

    @Override
    public boolean shouldSkipField(FieldAttributes fieldAtt) {
        return (fieldAtt.getDeclaringClass().getAnnotation(ToSerialize.class)==null 
                && fieldAtt.getAnnotation(ToSerialize.class) == null);
    }

    @Override
    public boolean shouldSkipClass(Class<?> type) {
        return false;
    }
    
}
