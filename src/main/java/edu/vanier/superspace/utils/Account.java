/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.superspace.utils;

import java.util.ArrayList;
import lombok.Getter;


public class Account {
    
    @Getter
    private String username;
    @Getter
    private String password;
    
    public static ArrayList<Account> accounts = new ArrayList<>();
    
    public Account(){
        username = "";
        password = "";
    }
    
    public Account(String username, String password){
        this.username = username;
        this.password = password;
    }
    
    public static ArrayList<Account> encrypt(){
        
        ArrayList<Account> toReturn = new ArrayList<>();
        
        for(int i = 0; i < accounts.size(); i++){
            
            toReturn.add(new Account(accounts.get(i).getUsername(), EncryptDecrypt.encrypt(accounts.get(i).getPassword(), accounts.get(i).getUsername().charAt(0), false)));
            
        }
        
        return toReturn; 
        
    }
    
    public static void decrypt(ArrayList<Account> toTreat){
        
        for(int i = 0; i < toTreat.size(); i++){
            
            accounts.add(new Account(toTreat.get(i).getUsername(), EncryptDecrypt.encrypt(toTreat.get(i).getPassword(), toTreat.get(i).getUsername().charAt(0), true)));
            
        }
        
    }
    
    public static void save(){
        
        String asJson = JsonHelper.serialize(encrypt());
        
    }
    
    
    
}
