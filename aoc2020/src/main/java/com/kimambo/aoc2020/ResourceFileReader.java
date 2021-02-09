package com.kimambo.aoc2020;

import java.io.IOException;
import java.util.List;


public interface ResourceFileReader {

        List<String> getContents() throws IOException; 

}
