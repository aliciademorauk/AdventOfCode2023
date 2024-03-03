import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;

public class Day2Challenge1 {
    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File("puzzleInputs/PuzzleInputDay2.txt"))) {
            int lineIDCount = 1;
            int sumIDsPossible = 0;
            String line;
            String[] parts;
            String part;
            boolean isGameIDPossible;
            int[] numbersArray;

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                parts = line.split(":");
                part = parts[1].trim();
                numbersArray = Arrays.stream(part.split("\\D+"))
                        .filter(s -> !s.isEmpty())
                        .mapToInt(Integer::parseInt)
                        .toArray();
                isGameIDPossible = true;

                for (int number : numbersArray) {
                    int indexAfterNumber = line.indexOf(String.valueOf(number)) + 2;
                    if (number > 12 && (line.startsWith(" r", indexAfterNumber) || number > 14 || (number > 13 && line.startsWith(" g", indexAfterNumber)))) {
                        isGameIDPossible = false;
                        break;
                    }
                }

                if (isGameIDPossible) {
                    sumIDsPossible += lineIDCount;
                }
                lineIDCount ++;
            }

            System.out.println("The total sum of game IDs that are possible is " + sumIDsPossible + ".");
        }
    }
}