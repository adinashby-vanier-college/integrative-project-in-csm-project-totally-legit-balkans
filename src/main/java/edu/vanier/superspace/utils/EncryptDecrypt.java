/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.superspace.utils;

/**
 * Class is used for the encryption and decryption for the logging
 */
public class EncryptDecrypt {
    /**
     * Encrypts a string or decrypts it
     * @param string the string
     * @param let the ley key
     * @param decrypt if it should decrypt
     * @return the encrypted or decrypted string
     */
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
