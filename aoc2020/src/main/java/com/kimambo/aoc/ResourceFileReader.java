package com.kimambo.aoc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;



public class ResourceFileReader {
    
    String resourceFilePath; 

    private final static Logger Log = Logger.getLogger(ResourceFileReader.class.getName()); 

    public ResourceFileReader(String path){
        this.resourceFilePath = path; 
    }
    
    public List<String> getContents() throws IOException {

        List<String> contents = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(this.resourceFilePath))) {

            String line = reader.readLine();
            contents.add(line);

            do {

                line = reader.readLine();
                if (line != null) {
                    contents.add(line);
                }

            } while (line != null);

        } catch (IOException e) {
            Log.info(e.getMessage());
            throw e; 
        }

        return contents;
    }
}
