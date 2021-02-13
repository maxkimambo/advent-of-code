package com.kimambo.aoc2020;


public class App 
{
    public static void main( String[] args )
    {

        // TODO: use a relative path (don't hardcode absolute path)
        String resources = "/Users/kimambo/dev/github.com/maxkimambo/codekatas/aoc/aoc2020/resources/";
        ResourceFileReader day1Resource = new ResourceFileReaderImpl(resources + "Day1.txt"); 
        Day1 d1 = new Day1(day1Resource); 
        d1.Solve();


        ResourceFileReader day2Resource = new ResourceFileReaderImpl(resources + "Day2.txt"); 
        Day2 d2 = new Day2(day2Resource); 
        d2.Solve();

        ResourceFileReader day3Resource = new ResourceFileReaderImpl(resources +"Day3.txt");
        Day3 d3 = new Day3(day3Resource); 
        d3.Solve();

        ResourceFileReader day4Resource = new ResourceFileReaderImpl(resources + "Day4.txt"); 
        Day4 d4 = new Day4(day4Resource); 
        d4.Solve(); 
    }
}
