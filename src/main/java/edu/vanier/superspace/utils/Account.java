/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.superspace.utils;

import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
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
    
    public static boolean matchAccount(String uName, String password){
        
        for(int i = 0; i < accounts.size(); i++){
            
            if(uName.equals(accounts.get(i).getUsername())&&
                    password.equals(accounts.get(i).getPassword())){
                return true;
            }
            
        }
        
        return false;
    }
    
    public static boolean sameUsername(String uName){

        for(int i = 0; i < accounts.size(); i++){
            
            if(uName.equals(accounts.get(i).getUsername())){
                
                return true;
                
            }
            
        }
        
        return false;
    }
    
    private static ArrayList<Account> encrypt(){
        
        ArrayList<Account> toReturn = new ArrayList<>();
        
        for(int i = 0; i < accounts.size(); i++){
            
            toReturn.add(new Account(accounts.get(i).getUsername(), EncryptDecrypt.encrypt(accounts.get(i).getPassword(), accounts.get(i).getUsername().charAt(0), false)));
            
        }
        
        return toReturn; 
        
    }
    
    private static void decrypt(ArrayList<Account> toTreat){
        
        for(int i = 0; i < toTreat.size(); i++){
            
            accounts.add(new Account(toTreat.get(i).getUsername(), EncryptDecrypt.encrypt(toTreat.get(i).getPassword(), toTreat.get(i).getUsername().charAt(0), true)));
            
        }
        
    }
    
    public static void save(){
        
        File file = new File("savedAccount.txt");
        String filePath = file.getAbsolutePath();
        
        String asJson = JsonHelper.getDefaultGson().toJson(encrypt());

        FileHelper.writeFileCompletely(filePath, asJson);
        
    }
    
    
    public static void load(){
        
        File file = new File("savedAccount.txt");
        String filePath = file.getAbsolutePath();
        
        String data = FileHelper.readFileCompletely(filePath);
        
        decrypt(JsonHelper.getDefaultGson().fromJson(data,new TypeToken<List<Account>>(){}.getType()));
        
    }
    
    
    
}
