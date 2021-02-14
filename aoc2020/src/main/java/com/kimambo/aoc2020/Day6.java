package com.kimambo.aoc2020;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Day6 {

    class Group {

        Collection<Character> answers = new HashSet<>();
        List<HashSet<Character>> groupAnswers = new ArrayList<>();

        int groupCount = 0;
        int groupCommon = 0;

        void addAnswers(char[] cs) {

            for (char c : cs) {
                if (answers.add(c)) {
                    groupCount++;
                }
            }

        }

        int everyonesAnswers(List<String> answers) {

            for (String a : answers) {

                HashSet<Character> personAnswer = new HashSet<>();

                for (char c : a.toCharArray()) {
                    personAnswer.add(c);
                }
                this.groupAnswers.add(personAnswer);
            }

            // check what is the union for this group

            HashSet s1 = this.groupAnswers.get(0);

            for (int i = 1; i < this.groupAnswers.size(); i++) {
                s1.retainAll(this.groupAnswers.get(i));
            }

            groupCommon = s1.size(); 
            return s1.size();
        }
    }

    private ResourceFileReader reader;
    private List<String> inputData;

    public Day6(ResourceFileReader fr) {

        reader = fr;
        try {
            inputData = reader.getContents();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Group> parseAnswers(List<String> input, boolean isPart2) {

        List<String> groupAnswers = new ArrayList<>();
        List<Group> answers = new ArrayList<>();

        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            if (line.isEmpty()) {
                // parse group
                answers.add(parseGroupAnswers(groupAnswers, isPart2));
                // create new instance
                groupAnswers = new ArrayList<>();
            } else {
                groupAnswers.add(line);
            }
            if (i == input.size() - 1) {
                answers.add(parseGroupAnswers(groupAnswers, isPart2));
            }

        }

        return answers;
    }

    private Group parseGroupAnswers(List<String> groupAnswers, boolean intersect) {
        Group g = new Group();
        if (intersect) {
            g.everyonesAnswers(groupAnswers);
        } else {
            for (String userAnswer : groupAnswers) {
                g.addAnswers(userAnswer.toCharArray());
            }
        }

        return g;
    }

    private int Solve1() {

        List<Group> groupAnswers = parseAnswers(inputData, false);
        int sumAnswers = 0;
        for (Group g : groupAnswers) {
            sumAnswers += g.groupCount;
        }
        return sumAnswers;
    }

    private int Solve2() {

        List<Group> groupAnswers = parseAnswers(inputData, true);
        int sumAnswers = 0;
        for (Group g : groupAnswers) {
            sumAnswers += g.groupCommon;
        }
        return sumAnswers;
    }

    public void Solve() {

        System.out.println("------------ Day 6 ---------------");
        int result = Solve1();

        System.out.println("Sum of answers " + result);

        int result2 = Solve2(); 

        System.out.println("Sum of unique group answers " + result2);
    }

}
