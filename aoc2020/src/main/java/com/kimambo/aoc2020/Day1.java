package com.kimambo.aoc2020;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;


public class Day1 {
    private List<String> contents;
    private List<Integer> taskInput;

    public Day1(ResourceFileReader fr) {

        try {
            contents = fr.getContents();
            taskInput = parseTaskInput(contents);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Integer> parseTaskInput(List<String> content) {

        List<Integer> input = new ArrayList();

        try {
            for (String s : content) {
                input.add(Integer.parseInt(s));
            }
            return input;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return input;
    }

    public void Solve() {

        System.out.println("------------ Day 1  --------------");
        int result1 = solve1(taskInput, 2020);
        int result2 = solvePart2(taskInput, 2020);

        System.out.println("Product of 2 numbers that add up to 2020 -> " + result1);
        System.out.println("Product of 3 numbers that add up to 2020 -> " + result2);

        System.out.println("----------------------------------");
    }

    private int solve1(List<Integer> input, int sumsTo) {

        HashMap<Integer, Integer> seen = new HashMap<>();

        for (int i : input) {
            int complement = sumsTo - i;
            if (seen.containsKey(complement)) {
                return complement * i;
            } else {
                seen.put(i, complement);
            }
        }

        return 0;
    }

    public int solvePart2(List<Integer> input, int sumsTo) {

        HashMap<Integer, Integer> seen = new HashMap<>();
        // find 3 numbers
        for (int i = 0; i < input.size(); i++) {
            int first = input.get(i);

            for (int j = i; j < input.size(); j++) {
                int second = input.get(j);
                int sum = first + second;
                int prod = first * second;
                seen.put(sum, prod);
            }
        }

        for (int n : input) {
            int complement = sumsTo - n;
            if (seen.containsKey(complement)) {
                return seen.get(complement) * n;
            }
        }

        return -1;
    }

}
