import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;


public class Day1Challenge2ToDebug {
    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File("src/puzzleInputs/PuzzleInputDay1.txt"))) {
            String line;
            String newLine;
            int firstIndex;
            int lastIndex;
            int inLinePosition;
            String firstSubstring;
            String lastSubstring;
            String numbersOnly;
            String stringNumber;
            int totalSumPartTwo = 0;
            Map<String, String> numberMap = Map.of(
                    "one", "1e",
                    "two", "2o",
                    "three", "3e",
                    "four", "4r",
                    "five", "5e",
                    "six", "6x",
                    "seven", "7n",
                    "eight", "8t",
                    "nine", "9e"
            );

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                firstIndex = -1;
                lastIndex = 0;
                firstSubstring = "";
                lastSubstring = "";

                for (String substring : numberMap.keySet()) {
                    inLinePosition = line.indexOf(substring);
                    if (inLinePosition != -1 && (firstIndex == -1 || inLinePosition < firstIndex)) {
                        firstIndex = inLinePosition;
                        firstSubstring = substring;
                    }
                }

                line = line.replaceFirst(firstSubstring, (String.valueOf(numberMap.get(firstSubstring))));

                for (String substring : numberMap.keySet()) {
                    inLinePosition = line.lastIndexOf(substring);
                    if (inLinePosition != -1 && (lastIndex == 0 || inLinePosition > lastIndex)) {
                        lastIndex = inLinePosition;
                        lastSubstring = substring;
                    }
                }

                newLine = line.substring(0, lastIndex) + numberMap.get(lastSubstring);
                if (lastIndex + lastSubstring.length() < line.length()) {
                    newLine += line.substring(lastIndex + lastSubstring.length());
                }

                numbersOnly = newLine.replaceAll("[^1-9]", "");
                stringNumber = "" + numbersOnly.charAt(0) + numbersOnly.charAt(numbersOnly.length() - 1);
                totalSumPartTwo += Integer.parseInt(stringNumber);
            }

            System.out.println("The total sum of calibration values in Part 2 is " + totalSumPartTwo);
        }
    }
}
