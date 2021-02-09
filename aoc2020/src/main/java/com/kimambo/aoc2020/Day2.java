package com.kimambo.aoc2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day2 {

    ResourceFileReader taskFile;
    private List<String> contents;
    private List<PwData> taskInput;

    public Day2(ResourceFileReader res) {

        taskFile = res;

        try {

            contents = taskFile.getContents();
            taskInput = parseTaskInput(contents);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    class PwData {

        int min;
        int max;
        char letter;
        String password;

        public PwData(int min, int max, char l, String pwd) {
            this.min = min;
            this.max = max;
            this.letter = l;
            this.password = pwd;
        }
    }


    public void Solve() {

        System.out.println("------------- Day 2 --------------");
        int result1 = SolveScenario1();
        int result2 = SolveScenario2();

        System.out.println("Valid password count : " + result1);
        System.out.println("Valid password count after new policy: " + result2);

        System.out.println("----------------------------------");
    }

    public int SolveScenario1() {

        int validPassCount = 0;

        for (PwData data : taskInput) {
            if (isValidPassword(data.min, data.max, data.letter, data.password)) {
                validPassCount++;
            }
        }
        return validPassCount;
    }

    private int SolveScenario2() {

        int validPassCount = 0;
        for (PwData data : taskInput) {
            if (isValidPassword2(data.min, data.max, data.letter, data.password)) {
                validPassCount++;
            }
        }

        return validPassCount;
    }

    private List<PwData> parseTaskInput(List<String> contents) {

        List<PwData> taskData = new ArrayList<>();

        for (String l : contents) {
            PwData data = parseLineInput(l);
            taskData.add(data);
        }
        return taskData;
    }

    private PwData parseLineInput(String inputLine) {

        String[] parts = inputLine.split(" ");
        String letterPart = parts[1].replace(':', ' ');
        char l = letterPart.charAt(0);

        String[] firstPart = parts[0].split("-");
        int min = Integer.parseInt(firstPart[0]);
        int max = Integer.parseInt(firstPart[1]);
        String passwordPart = parts[2];
        return new PwData(min, max, l, passwordPart);
    }

    private boolean isValidPassword2(int min, int max, char letter, String password) {

        min--;
        max--;

        if (max < password.length() && !password.equals("")) {
            return Character.valueOf(password.charAt(min)).equals(letter)
                    ^ Character.valueOf(password.charAt(max)).equals(letter);
        } else {
            return Character.valueOf(password.charAt(min)).equals(letter);
        }
    }

    private boolean isValidPassword(int min, int max, char letter, String password) {

        char[] passChars = password.toCharArray();
        int occurance = 0;

        for (char c : passChars) {

            if (c == letter) {
                occurance++;
            }
            if (occurance > max) {
                return false;
            }

        }

        return min <= occurance && occurance <= max;
    }

}
