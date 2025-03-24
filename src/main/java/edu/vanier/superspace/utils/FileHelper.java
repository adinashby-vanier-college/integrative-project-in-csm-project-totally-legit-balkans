package edu.vanier.superspace.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHelper {
    
    public static final String SIMULATION_FILE_EXTENSION = ".sim";
    
    public static String readFileCompletely(String filepath) {
        try {
            File newFile = new File(filepath);

            BufferedReader reader = new BufferedReader(new FileReader(newFile));
            StringBuilder contents = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                contents.append(line);
            }

            return contents.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void writeFileCompletely(String filepath, String data) {
        try {
            FileWriter writer = new FileWriter(filepath);
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
