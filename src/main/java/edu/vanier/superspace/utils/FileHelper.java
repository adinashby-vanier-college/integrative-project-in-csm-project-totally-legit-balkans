package edu.vanier.superspace.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class that helps with files, meaning simulation saves
 */
public class FileHelper {
    public static final String SIMULATION_FILE_EXTENSION = ".sim";
    public static final String SIMULATION_ICON_EXTENSION = ".icon";

    /**
     * Reads all the lines of a file
     * @param filepath the file path
     * @return what's in the file
     */
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

    /**
     * Writes data to a file
     * @param filepath the file path to write to
     * @param data the data to be written
     */
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
