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
            int lineIndex;
            String line;
            int firstIndex;
            int lastIndex;
            String firstSubstring;
            String lastSubstring;

            while (scanner.hasNextLine()) {
                lineIndex = 0;
                line = scanner.nextLine();
                firstIndex = -1;
                lastIndex = 0;
                firstSubstring = "";
                lastSubstring = "";

                for (String substring : numberMap.keySet()) {
                    int inLinePosition = line.indexOf(substring);

                    if (inLinePosition != -1 && (firstIndex == -1 || inLinePosition < firstIndex)) {
                        firstIndex = inLinePosition;
                        firstSubstring = substring;
                    }
                }

                if (!firstSubstring.isBlank()) {
                    //change -- for the 1st substring we do have to add the last letter to the number we substitute the substring by e.g. see line 323 (where 1st and last substrings overlap)
                    line = line.replaceFirst(firstSubstring, (String.valueOf(numberMap.get(firstSubstring)) + firstSubstring.charAt(firstSubstring.length() - 1)));
                }

                for (String substring : numberMap.keySet()) {
                    //change: needed to find the index starting from back in case there are two of the same numbers
                    int inLinePosition = line.lastIndexOf(substring);

                    if (inLinePosition != -1 && (lastIndex == 0 || inLinePosition > lastIndex)) {
                        lastIndex = inLinePosition;
                        lastSubstring = substring;
                    }
                }

                //change: need to not replace line but store in new line so that in line 70 we are taking the substring from the original line
                if (!lastSubstring.isBlank()) {
                    String newLine = line.substring(0, lastIndex) + numberMap.get(lastSubstring);
                    if (lastIndex + lastSubstring.length() < line.length()) {
                        newLine += line.substring(lastIndex + lastSubstring.length());
                    }
                    line = newLine;
                }

                String numbersOnly = line.replaceAll("[^1-9]", "");
                String stringNumber = "" + numbersOnly.charAt(0) + numbersOnly.charAt(numbersOnly.length() - 1);
                totalSumPartTwo += Integer.parseInt(stringNumber);

                lineIndex ++;
            }

            System.out.println("The total sum of calibration values in Part 2 is " + totalSumPartTwo);

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }
}
