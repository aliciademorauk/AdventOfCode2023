import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Day1Challenge2ToDebug {
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(new File("puzzleInputs/PuzzleInputDay1.txt"))) {
            int totalSumPartTwo = 0;
            Map<String, Integer> numberMap = Map.of(
                    "one", 1,
                    "two", 2,
                    "three", 3,
                    "four", 4,
                    "five", 5,
                    "six", 6,
                    "seven", 7,
                    "eight", 8,
                    "nine", 9
            );

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                int firstIndex = -1;
                int lastIndex = 0;
                String firstSubstring = "";
                String lastSubstring = "";

                for (String substring : numberMap.keySet()) {
                    int inLinePosition = line.indexOf(substring);

                    if (inLinePosition != -1 && (firstIndex == -1 || inLinePosition < firstIndex)) {
                        firstIndex = inLinePosition;
                        firstSubstring = substring;
                    }
                }

                String lineWithReplacedInitialSubstring = line;

                if (!firstSubstring.isBlank()) {
                    lineWithReplacedInitialSubstring = line.replace(firstSubstring, (String.valueOf(numberMap.get(firstSubstring)) + firstSubstring.charAt(firstSubstring.length() - 1)));
                }

                for (String substring : numberMap.keySet()) {
                    int inLinePosition = lineWithReplacedInitialSubstring.indexOf(substring);

                    if (inLinePosition != -1 && (lastIndex == 0 || inLinePosition > lastIndex)) {
                        lastIndex = inLinePosition;
                        lastSubstring = substring;
                    }
                }

                String lineWithReplacedSubstrings = lineWithReplacedInitialSubstring;

                if (!lastSubstring.isBlank()) {
                    lineWithReplacedSubstrings = lineWithReplacedInitialSubstring.replace(lastSubstring, (String.valueOf(numberMap.get(lastSubstring)) + lastSubstring.charAt(lastSubstring.length() - 1)));
                }

                String numbersOnly = lineWithReplacedSubstrings.replaceAll("[^1-9]", "");
                String stringNumber = "" + numbersOnly.charAt(0) + numbersOnly.charAt(numbersOnly.length() - 1);
                totalSumPartTwo += Integer.parseInt(stringNumber);
            }

            System.out.printf("The total sum of calibration values in Part 2 is %d", totalSumPartTwo);

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }
}
