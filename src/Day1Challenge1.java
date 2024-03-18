//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day1Challenge1 {
    public static void main(String[] args) {

        //Part 1

        try (Scanner scanner = new Scanner(new File("src/puzzleInputs/PuzzleInputDay1.txt"))) {
            int totalSumPartOne = 0;
            String line;
            String numbersOnly;
            String stringNumber;

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                //Remove all chars except digits from 1-9 inclusive
                numbersOnly = line.replaceAll("[^1-9]", "");
                stringNumber = "" + numbersOnly.charAt(0) + numbersOnly.charAt(numbersOnly.length() - 1);
                totalSumPartOne += Integer.parseInt(stringNumber);
            }

            System.out.printf("The total sum of calibration values in Part 1 is %d", totalSumPartOne);

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }


}