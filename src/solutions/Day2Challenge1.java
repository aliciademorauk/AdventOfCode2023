package solutions;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;

public class Day2Challenge1 {
    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File("src/puzzleInputs/PuzzleInputDay2.txt"))) {
            String line;
            int[] numbersArray; // array of numbers in each Game
            int startFromPosition;
            int lineIDNumber = 1;
            boolean isGameIDPossible;
            int sumIDsPossible = 0;

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                line = (line.split(":"))[1].trim();
                numbersArray = Arrays.stream(line.split("\\D+"))
                        .filter(s -> !s.isEmpty())
                        .mapToInt(Integer::parseInt)
                        .toArray();
                isGameIDPossible = true;
                startFromPosition = 0;
                for (int number : numbersArray) {
                    startFromPosition = calculateIndexAfter2ndDigit(line, number, startFromPosition);
                    if (number > 12 && (line.startsWith(" r", startFromPosition) || number > 14 || (number > 13 && line.startsWith(" g", startFromPosition)))) {
                        isGameIDPossible = false;
                        break;
                    }
                }

                if (isGameIDPossible) {
                    sumIDsPossible += lineIDNumber;
                }
                lineIDNumber ++;
            }
            System.out.println("The total sum of game IDs that are possible is " + sumIDsPossible + ".");
        }
    }

    public static int calculateIndexAfter2ndDigit (String line, int number, int startFromPosition) {
        return line.indexOf(String.valueOf(number), startFromPosition) + 2;
    }
}