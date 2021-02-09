package com.kimambo.aoc2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;



public class ResourceFileReaderImpl implements ResourceFileReader {
    
    String resourceFilePath; 

    private final static Logger Log = Logger.getLogger(ResourceFileReaderImpl.class.getName()); 

    public ResourceFileReaderImpl(String path){
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

    public String getAll() throws IOException {

        String contents; 

        try (BufferedReader reader = new BufferedReader(new FileReader(this.resourceFilePath))) {

            String line = reader.readLine();

            do {

                contents += reader.readLine();
                if (line != null) {
                    contents += line; 
                }

            } while (line != null);

        } catch (IOException e) {
            Log.info(e.getMessage());
            throw e; 
        }

        return contents;
    }
}
