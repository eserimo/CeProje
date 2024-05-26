/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Efe
 */
public class Analyser {
    

    
    
    public static double compareNotepadFiles(String path1, String path2) {
        try {
            // Read contents of file 1
            String content1 = new String(Files.readAllBytes(Paths.get(path1)));
            // Read contents of file 2
            String content2 = new String(Files.readAllBytes(Paths.get(path2)));

            // Calculate similarity percentage
            double similarityPercentage = calculateSimilarityPercentage(content1, content2);

            return similarityPercentage;
        } catch (IOException e) {
            System.err.println("An error occurred while comparing files: " + e.getMessage());
            return -1; // Return -1 if an error occurs
        }
    }

    // Method to calculate similarity percentage between two strings
    private static double calculateSimilarityPercentage(String s1, String s2) {
        // Convert strings to lowercase to make comparison case-insensitive
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        int maxLength = Math.max(s1.length(), s2.length());

        // Calculate number of matching characters
        int matchingCount = 0;
        for (int i = 0; i < Math.min(s1.length(), s2.length()); i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                matchingCount++;
            }
        }

        // Calculate similarity percentage
        double similarityPercentage = ((double) matchingCount / maxLength) * 100;

        return similarityPercentage;
    }


    public static void readNotebooks(String directoryPath, List<String> notebookNames, List<Double> pointValues) throws IOException {
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        try {
                            double value = Double.parseDouble(line.trim());
                            notebookNames.add(file.getName());
                            pointValues.add(value);
                            System.out.println(value);
                            break; // We are only interested in the first double value
                        } catch (NumberFormatException e) {
                            // Couldn't parse to double, move to the next line
                        }
                    }
                    reader.close();
                }
            }
        }
    }    
    
}
