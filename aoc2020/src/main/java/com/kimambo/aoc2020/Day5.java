package com.kimambo.aoc2020;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Day5 {

    class BoardingPass implements Comparable<BoardingPass> {
        int row;
        int col;
        int seatID;
        String encodedPass;
        String encodedRow;
        String encodedCol;

        BoardingPass(String passData) {
            encodedPass = passData;
            encodedRow = getRow(encodedPass);
            encodedCol = getColumn(encodedPass);
            decodeBoardingPass(encodedRow, encodedCol);
        }

        private String getRow(String boardingPass) {
            
            String[] parts = boardingPass.split("L|R");

            try {
                return parts[0];
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }

            return "";
        }

        private String getColumn(String boardingPass) {

            String[] parts = boardingPass.split("B|F");

            int lastInx = parts.length - 1;

            try {
                return parts[lastInx];
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
            return "";
        }

        private void decodeBoardingPass(String encodedRowData, String encodedColData) {

            char[] rowData = encodedRowData.toCharArray();
            char[] colData = encodedColData.toCharArray();

            row = decode(rowData, 0, 128, 'F','B', 0);
            col = decode(colData, 0, 8, 'L', 'R', 0);
            seatID = row * 8 + col; 
        }

        private int decode(char[] data, int low, int high, Character lower, Character upper, int currentPos) {



            // base case
            if (currentPos == data.length) {
                // low is inclusive 
                // high is exclusive thats why we return low.
                return low; 
            }

            int mid = (high + low) / 2;
            Character currentSymbol = data[currentPos];

            if (currentSymbol.equals(upper) && currentPos < data.length) {
                low = mid;
                return decode(data, low, high, lower, upper, currentPos + 1);
            }
            if (currentSymbol.equals(lower) && currentPos < data.length) {
                high = mid;
                return decode(data, low, high, lower, upper, currentPos + 1);
            }

            return -1;
        }

        @Override
        public int compareTo(BoardingPass o) {
            if (this.seatID < o.seatID){
                return -1; 
            }else if (this.seatID > o.seatID){
                return 1; 
            }
            return 0;
        }
    }

    private ResourceFileReader reader;
    private List<String> inputData;
    private List<BoardingPass> boardingPasses;

    public Day5(ResourceFileReader fr) {
        reader = fr;

        try {
            inputData = reader.getContents();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private List<BoardingPass> parseBoardingPasses(List<String> boardingPassData) {

        List<BoardingPass> boardingPasses = new ArrayList<>();
        for (String boardingPass : boardingPassData) {

            BoardingPass bp = new BoardingPass(boardingPass);
            boardingPasses.add(bp);

        }

        return boardingPasses;
    }

    private int Solve1() {
        // find the highest ID
        this.boardingPasses = parseBoardingPasses(inputData);
        int highestId = Integer.MIN_VALUE; 
        for(BoardingPass bp : boardingPasses){
            highestId = Math.max(bp.seatID, highestId); 
        }

        return highestId;
    }

    private int Solve2(){

        try
        {
            Collections.sort(this.boardingPasses); 
        }catch(NullPointerException e){
            e.printStackTrace();
        }
        int missingSeatId =0; 
        
        for (int i=0; i< this.boardingPasses.size(); i++){

            int nextID = +1; 
            int prevID = i -1; 

            if (prevID > -1 && nextID < this.boardingPasses.size()){
                // check 

                BoardingPass currentSeat = this.boardingPasses.get(i); 
                BoardingPass previousSeat = this.boardingPasses.get(prevID); 
                BoardingPass nextSeat = this.boardingPasses.get(nextID); 

                if (currentSeat.seatID -1 != previousSeat.seatID){
                    missingSeatId = previousSeat.seatID +1; 
                }
            }
        }

        return missingSeatId; 
    }

    public void Solve() {
        int result1 = Solve1();
        System.out.println("----------------- Day 5 -----------------");
        System.out.println("highest boarding pass ID: " + result1);
        int result2 = Solve2(); 
        System.out.println("My seat ID " + result2);
    }
}
