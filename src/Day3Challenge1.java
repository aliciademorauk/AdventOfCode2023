import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
public class Day3Challenge1 {
    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File("puzzleInputs/PuzzleInputDay3.txt"))) {
            String line;
            String previousLine = "";
            String nextLine = "";
            int lineIndex = 0;
            int indexOfNumber;
            int indexBeforeNumber;
            int indexAfterNumber;
            String[] numbersArray;
            boolean isPart = false;
            int sumParts = 0;

            int[] rangeOfPositions = new int[2];
            int[] indexesInPreviousLine = new int[2];
            int[] indexesInNextLine = new int[2];;

            while (scanner.hasNextLine()) {
                if (!nextLine.isEmpty()) {
                    line = nextLine;
                }
                line = scanner.nextLine();
                numbersArray = Arrays.stream(line.split("\\D+"))
                        .filter(s -> !s.isEmpty())
                        .toArray(String[]::new);

                for (String number : numbersArray) {
                    indexOfNumber = line.indexOf(number);
                    indexBeforeNumber = indexOfNumber - 1;
                    rangeOfPositions[0] = indexBeforeNumber == -1 ? line.indexOf(number) : indexBeforeNumber;
                    if (indexOfNumber < line.length() - 1) {
                        indexAfterNumber = indexOfNumber + 1;
                        if (number.length() > 1) {
                            if (number.length() == 2) {indexAfterNumber += 1;}
                            else {indexAfterNumber += 2;}
                        }
                    } else {indexAfterNumber = indexOfNumber;}

                    rangeOfPositions[1] = indexAfterNumber;

                    if (line.charAt(indexBeforeNumber) != '.' || line.charAt(indexAfterNumber) != '.' ||
                            (!previousLine.isEmpty() && previousLine.charAt(indexBeforeNumber) != '.' || (!previousLine.isEmpty() && previousLine.charAt(indexAfterNumber) != '.'))) {
                        sumParts += Integer.parseInt(number);
                    }
                }

                previousLine = line;
                // fix the below since this can raise exception if we are in the last line of the file
                /* nextLine = scanner.nextLine();

                if (nextLine.charAt(indexBeforeNumber) != '.' || nextLine.charAt(indexAfterNumber) != '.' ||
                        (!nextLine.isEmpty() && nextLine.charAt(indexBeforeNumber) != '.' || (!nextLine.isEmpty() && nextLine.charAt(indexAfterNumber) != '.'))) {
                    sumParts += Integer.parseInt(number);
                } */

            }
        }
    }
}
