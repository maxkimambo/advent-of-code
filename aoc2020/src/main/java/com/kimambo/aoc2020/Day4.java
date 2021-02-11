package com.kimambo.aoc2020;

import java.util.List;
import java.util.Set;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import com.kimambo.aoc2020.ResourceFileReader;

public class Day4 {

    private ResourceFileReader reader;
    List<String> contents;
    List<HashMap<String, String>> taskInput = new ArrayList<>();
    private List<HashMap<String, String>> invalidPassports = new ArrayList<>();
    private List<HashMap<String, String>> validPassports = new ArrayList<>();

    public Day4(ResourceFileReader fr) {
       
        reader = fr;

        try {
            contents = reader.getContents();
            taskInput = parseInputData(contents);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<HashMap<String, String>> parseInputData(List<String> contents) {

        List<String> passportDataSet = new ArrayList<>();
        List<HashMap<String, String>> passportData = new ArrayList<>();
        boolean next = false;

        for(int i=0; i < contents.size(); i++){
            String line = contents.get(i); 
            if (next) {
                passportDataSet = new ArrayList<>();
                next = false;
            }
            if (!line.isEmpty()) {
                passportDataSet.add(line);
            } else {
                next = true;
                passportData.add(extractPassportData(passportDataSet));
            }

            // add remaining items if file doesnt end with an empty line 
            if (i == (contents.size() -1)) {
                passportData.add(extractPassportData(passportDataSet));
            }
        }
        return passportData;
    }

    private HashMap<String, String> extractPassportData(List<String> passData) {

        List<String> tmp = new ArrayList<>();
        // put all k:v pairs into arraylist
        for (String line : passData) {
            tmp.addAll(Arrays.asList(line.split(" ")));
        }

        HashMap<String, String> passMap = new HashMap<>();
        for (String p : tmp) {
            String[] key_values = p.split(":");
            passMap.put(key_values[0], key_values[1]);
        }

        return passMap;
    }

    private boolean isValid(HashMap<String, String> pass, HashMap<String, Boolean> rules) {

        return pass.keySet().containsAll(rules.keySet());
    }

    private int Solve1(List<HashMap<String, String>> passports, HashMap<String, Boolean> rules) {

        int validPassportCount = 0;

        for (HashMap<String, String> pass : passports) {

            if (isValid(pass, rules)) {
                validPassportCount++;
            }

        }
        return validPassportCount;
    }

    public void Solve() {

        HashMap<String, Boolean> validationRules = new HashMap<>();

        validationRules.put("byr", true);
        validationRules.put("iyr", true);
        validationRules.put("eyr", true);
        validationRules.put("hgt", true);
        validationRules.put("hcl", true);
        validationRules.put("ecl", true);
        validationRules.put("pid", true);
        // validationRules.put("cid", false);

        int result = Solve1(taskInput, validationRules);
        System.out.println("Valid passports => " + result);
    }
}
