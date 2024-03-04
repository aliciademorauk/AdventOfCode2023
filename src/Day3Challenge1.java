import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
public class Day3Challenge1 {
    public static void main(String[] args) throws FileNotFoundException {
        // for all lines:
        // for every number:
        // find (index of digit 1) - 1 and (index of last digit) + 1
        // using this, check: 1) if there is a previous line to the current line, if in this range of indexes (inclusive)
        // there is anything else other than a dot; and 2) if there is a next line, if in this range of indexes (inclusive)
        // there is anything else other than a dot; and 3) check if in those specific indexes there is anything else other
        // than a dot in the same line
        // if any of these == yes, add to the sum

        // 618.......272....*.........&

        try (Scanner scanner = new Scanner(new File("puzzleInputs/PuzzleInputDay3.txt"))) {
            String line;
            String previousLine = "";
            String nextLine;
            int lineIndex = 0;
            int indexBeforeNumber;
            int indexAfterNumber;
            String[] numbersArray;
            int sumParts = 0;

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                numbersArray = Arrays.stream(line.split("\\D+"))
                        .filter(s -> !s.isEmpty())
                        .toArray(String[]::new);

                for (String number : numbersArray) {
                    indexBeforeNumber = -1;
                    indexAfterNumber = -1;

                    if (line.indexOf(number) > 0) {
                        indexBeforeNumber = line.indexOf(number) - 1;
                    }

                    indexAfterNumber = indexBeforeNumber + 2;

                    if (number.length() > 1) {
                        if (number.length() == 2) {indexAfterNumber += 1;}
                        else {indexAfterNumber += 2;}
                    }
                    
                    if (indexBeforeNumber != -1 && indexAfterNumber != -1) {
                        if (line.charAt(indexBeforeNumber) != '.' || line.charAt(indexAfterNumber) != '.') {
                            sumParts += Integer.parseInt(number);
                        }

                        if (!previousLine.isEmpty()) {
                            if (previousLine.charAt(indexBeforeNumber, indexAfterNumber + 1) != '.') {

                            }
                        }
                    }
                }

                previousLine = line;
            }
        }
    }
}
