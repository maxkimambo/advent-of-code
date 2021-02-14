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

        for (int i = 0; i < contents.size(); i++) {
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
            if (i == (contents.size() - 1)) {
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

    private List<HashMap<String, String>> validateFieldPresence(List<HashMap<String, String>> passports,
            HashMap<String, Boolean> rules) {

        List<HashMap<String, String>> validPassports = new ArrayList<>();

        for (HashMap<String, String> pass : passports) {

            if (isValid(pass, rules)) {
                validPassports.add(pass);
            }
        }
        return validPassports;
    }

    private boolean validateHeight(String hgt) {

        boolean validFormat = hgt.matches("^\\d+(cm|in)");

        if (!validFormat) {
            return false;
        }

        try {

            String[] heightValue = hgt.split("cm|in");
            int height = Integer.parseInt(heightValue[0]);
            String[] unitValue = hgt.split("\\d+");
            String unit = unitValue[1];

            if (unit.equals("cm") && (height >= 150 && height <= 193)) {
                return true;
            }

            if (unit.equals("in") && (height >= 59 && height <= 76)) {
                return true;
            }

        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            return false;
        }

        return false;
    }

    private int Solve2(List<HashMap<String, String>> passports, HashMap<String, Boolean> rules) {

        List<HashMap<String, String>> validPassports = new ArrayList<>();
        // List<HashMap<String, String>> invalidPassports = new ArrayList<>();
        // check for presence of required fields
        List<HashMap<String, String>> passportsWithRequiredFields = validateFieldPresence(passports, rules);
        // check if the fields conform to the rules
        
        for (HashMap<String, String> pass : passportsWithRequiredFields) {
            HashMap<String, Boolean> validationResults = new HashMap<>();

            validationResults.put("hgt", validateHeight(pass.get("hgt")));

            validationResults.put("byr", validateYearRange(1920, 2002, pass.get("byr")));

            validationResults.put("iyr", validateYearRange(2010, 2020, pass.get("iyr")));

            validationResults.put("eyr", validateYearRange(2020, 2030, pass.get("eyr")));

            validationResults.put("hcl", itemValidator(pass.get("hcl"), "^#[0-9a-z]{6}"));

            validationResults.put("ecl", itemValidator(pass.get("ecl"), "amb|blu|brn|gry|grn|hzl|oth"));

            validationResults.put("pid", itemValidator(pass.get("pid"), "\\d{9,9}"));

            // if all are valid perform count.
            if (!validationResults.containsValue(false)) {
                validPassports.add(pass);
            }
            // }else{
            //     invalidPassports.add(pass); 
            // }
        }

        return validPassports.size(); 
    }

    private boolean validateYearRange(int low, int high, String yearValue) {
        try {
            int year = Integer.parseInt(yearValue);
            return year >= low && year <= high;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean itemValidator(String input, String regex) {
        return input.matches(regex);
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
        System.out.println("------------- Day 4 ---------------");
        System.out.println("Valid passports => " + result);

        int result2 = Solve2(taskInput, validationRules);

        System.out.println("Valid passports with valid values => " + result2);
    }
}
