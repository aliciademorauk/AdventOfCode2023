package solutions;

import java.io.IOException;
import java.util.Map;

public class Day1 extends Day {

    public Day1() throws IOException {
        super(1);
    }

    @Override
    public int solvePart1(){
        for (String line : input) {
            totalPart1 += extractFirstLastDigit(line);
        }
        return totalPart1;
    }

    @Override
    public int solvePart2(){
        Map<String, String> numberMap = Map.of(
                "one", "o1e",
                "two", "t2o",
                "three", "t3e",
                "four", "f4r",
                "five", "f5e",
                "six", "s6x",
                "seven", "s7n",
                "eight", "e8t",
                "nine", "n9e"
        );
        for (String line : input) {
            for (String key : numberMap.keySet()) {
                line = line.replaceAll(key, numberMap.get(key));
            }
            totalPart2 += extractFirstLastDigit(line);
        }
        return totalPart2;
    }

    //Other approach to Part2 - would be good to clean up in the future
    //public int solvePart2OtherApproach(){}

    public int extractFirstLastDigit(String line) {
        String numbers = line.replaceAll("[^1-9]", "");
        return Integer.parseInt(numbers.charAt(0) + String.valueOf(numbers.charAt(numbers.length() - 1)));
    }
}
