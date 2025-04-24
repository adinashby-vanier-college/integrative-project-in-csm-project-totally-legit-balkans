/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.superspace.utils;


public class EncryptDecrypt {
    
    public static String encrypt(String string, char let, boolean decrypt){
        
        int key = let;
        String toReturn = "";
        
        char[] letters = string.toCharArray();
        
        if(decrypt){
            for(char l:letters){
            
            l-=key;
            toReturn = toReturn + l;
            
           } 
        }else{
           for(char l:letters){
            
            l+=key;
            toReturn = toReturn + l;
            
           } 
        }
        
        return toReturn;
        
    }
    
    
    
}
