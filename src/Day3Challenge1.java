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
            String[] numbersArray;
            int indexOfNumber;
            int indexBeforeNumber;
            int indexAfterNumber;
            int sumParts = 0;

            while (scanner.hasNextLine()) {
                if (!nextLine.isBlank()) {
                    line = nextLine;
                } else {line = scanner.nextLine();}

                if (scanner.hasNextLine()) {
                    nextLine = scanner.nextLine();
                }

                numbersArray = Arrays.stream(line.split("\\D+"))
                        .filter(s -> !s.isEmpty())
                        .toArray(String[]::new);

                for (String number : numbersArray) {
                    indexOfNumber = line.indexOf(number);
                    indexBeforeNumber = indexOfNumber == 0 ? indexOfNumber : indexOfNumber - 1;
                    indexAfterNumber = indexOfNumber;
                    if (indexOfNumber < line.length() - 1) {
                        indexAfterNumber = indexOfNumber + 1;
                        if (number.length() >= 2 && indexOfNumber < line.length() - 2) {
                            indexAfterNumber += 1;
                        }
                        if (number.length() >= 3 && indexOfNumber < line.length() - 3) {
                            indexAfterNumber += 1;
                        }
                    }

                    if (line.charAt(indexBeforeNumber) != '.' || line.charAt(indexAfterNumber) != '.') {
                        sumParts += Integer.parseInt(number);
                    } else if (nextLine.substring(indexBeforeNumber, indexAfterNumber + 1).chars().anyMatch(ch -> ch != '.')) {
                        sumParts += Integer.parseInt(number);
                    } else if (!previousLine.isBlank()) {
                        if (previousLine.substring(indexBeforeNumber, indexAfterNumber + 1).chars().anyMatch(ch -> ch != '.')) {
                            sumParts += Integer.parseInt(number);
                        }
                    }
                }
                previousLine = line;
            }
            System.out.println("The sum of parts is " + sumParts + ".");
        }
    }
}
