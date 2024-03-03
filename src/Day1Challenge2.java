import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class Day1Challenge2 {
    public static void main(String[] args) {

        //Part 2

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

        try (Scanner scanner = new Scanner(new File("puzzleInputs/PuzzleInputDay1.txt"))) {
            int totalSumPartTwo = 0;
            String line;

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                for (String key : numberMap.keySet()) {
                    line = line.replaceAll(key, numberMap.get(key));
                }

                //Remove all chars except digits from 1-9 inclusive
                String numbersOnly = line.replaceAll("[^1-9]", "");
                String stringNumber = "" + numbersOnly.charAt(0) + numbersOnly.charAt(numbersOnly.length() - 1);
                totalSumPartTwo += Integer.parseInt(stringNumber);
            }

            System.out.printf("The total sum of calibration values in Part 1 is %d", totalSumPartTwo);

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }
}
