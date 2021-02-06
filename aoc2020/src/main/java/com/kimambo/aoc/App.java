package com.kimambo.aoc;


public class App 
{
    public static void main( String[] args )
    {

        ResourceFileReader day1Resource = new ResourceFileReader("/Users/kimambo/dev/github.com/maxkimambo/codekatas/aoc/aoc2020/resources/Day1.txt"); 
        Day1 d1 = new Day1(day1Resource); 
        d1.Solve();


        ResourceFileReader day2Resource = new ResourceFileReader("/Users/kimambo/dev/github.com/maxkimambo/codekatas/aoc/aoc2020/resources/Day2.txt"); 
        Day2 d2 = new Day2(day2Resource); 
        d2.Solve();

        ResourceFileReader fr = new ResourceFileReader("/Users/kimambo/dev/github.com/maxkimambo/codekatas/aoc/aoc2020/resources/Day3.txt");
        Day3 d3 = new Day3(fr); 
        d3.Solve();
    }
}
