package com.kimambo.aoc;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Day3 {
    class Slope{
        int RIGHT;
        int DOWN; 
        Slope(int r, int d){
            RIGHT = r; 
            DOWN = d; 
        }
    }

    /**
     * Datastructure to hold the terrain generated from the input file text
     */
    class TerrainMap {

        char[][] map;
        int currentRow;
        int currentCol;
        int totalRows;
        int totalCols;
        boolean finished = false;

        TerrainMap(List<String> input) {

            if (input.isEmpty()) {
                throw new InvalidParameterException("Terrain map cannot be 0");
            }
            totalRows = input.size();
            totalCols = input.get(0).length();

            map = new char[totalRows][totalCols];

            // process the input into the map
            for (int r = 0; r < input.size(); r++) {
                for (int c = 0; c < input.get(r).length(); c++) {
                    map[r][c] = input.get(r).charAt(c);
                }
            }
        }

        int getRow() {
            return currentRow;
        }

        int getCol() {
            return currentCol;
        }

        boolean isDone() {
            return finished;
        }

        boolean isTree(int row, int col) {
            char cellContent = map[row][col];
            return Character.valueOf(cellContent).equals('#');
        }

        void setStartPos(int row, int col) {
            currentRow = row;
            currentCol = col;
        }

        void moveRight() {
            if (currentCol < totalCols - 1) {
                currentCol++;
            } else {
                // reset back to the beginning
                currentCol = 0;
            }
        }

        void moveDown() {
            if (currentRow < totalRows - 1) {
                currentRow++;
            } else {
                finished = true;
            }
        }

    }

    private ResourceFileReader fileReader;

    public Day3(ResourceFileReader fr) {
        this.fileReader = fr;
    }

    private List<String> getTaskInput() {
        List<String> contents = null;
        try {
            return fileReader.getContents();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return contents;
    }

    private int Solution1(int RIGHT, int DOWN) {
        List<String> contents = getTaskInput();
        TerrainMap map = new TerrainMap(contents);
        int treeCount = 0;

        map.setStartPos(0, 0); // start top left

        while (!map.isDone()) {

            for (int i = 0; i < RIGHT; i++) {
                map.moveRight();
            }
            for (int j = 0; j < DOWN; j++) {
                map.moveDown();
            }
            // check if we hit a tree at current position
            if (map.isTree(map.currentRow, map.currentCol)) {
                treeCount++;
            }
        }

        System.out.println("Tree count " + treeCount);

        return treeCount;
    }

    private Long Solution2(){
        List<Integer> results = new ArrayList();
        // slopes to check
        List<Slope> slopes = new ArrayList<>();
        slopes.add(new Slope(1,1)); 
        slopes.add(new Slope(3,1)); 
        slopes.add(new Slope(5,1)); 
        slopes.add(new Slope(7,1)); 
        slopes.add(new Slope(1,2)); 
 
    
        for (Slope s : slopes) { 
            int res = Solution1(s.RIGHT, s.DOWN); 
            results.add(res); 
        }

        // compute product 
        long product =1; 
        for (int r : results){
            product *= r; 
        }

        return product; 
    }

    public void Solve() {

        System.out.println("-------------- Day 3 -------------");
        // scenario 1
        int scenario1Result = Solution1(3, 1);
        // scenario 2
        Long scenario2Result = Solution2();
        System.out.println("Trees found on this slope ->  " + scenario1Result);
        System.out.println("Product of trees -> " + scenario2Result);

        System.out.println("----------------------------------");
    }

}
