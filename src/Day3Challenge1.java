import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
public class Day3Challenge1 {

    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File("puzzleInputs/PuzzleInputDay3.txt"))) {
            String currentLine;
            String previousLine = "";
            String nextLine = "";
            String[] numbersArray;
            String[] linesArray = new String[3];
            int indexOfNumber;
            int startFromPosition;
            int indexBeforeNumber;
            int indexAfterNumber;
            int sumParts = 0;

            while (scanner.hasNextLine()) {
                if (!nextLine.isBlank()) {
                    currentLine = nextLine;
                } else {currentLine = scanner.nextLine();}

                if (scanner.hasNextLine()) {
                    nextLine = scanner.nextLine();
                }

                linesArray[0] = previousLine;
                linesArray[1] = currentLine;
                linesArray[2] = nextLine;
                numbersArray = Arrays.stream(currentLine.split("\\D+"))
                        .filter(s -> !s.isBlank())
                        .toArray(String[]::new);

                startFromPosition = 0;
                for (String number : numbersArray) {
                    indexOfNumber = currentLine.indexOf(number, startFromPosition);
                    indexAfterNumber = (indexOfNumber + number.length() < currentLine.length()) ? indexOfNumber + number.length() : indexOfNumber + number.length() - 1;
                    indexBeforeNumber = indexOfNumber == 0 ? indexOfNumber : indexOfNumber - 1;

                    checkNumber:
                    for (String line : linesArray) {
                        if (!line.isBlank()) {
                            for (int i = indexBeforeNumber; i <= indexAfterNumber; i++) {
                                if ((line.charAt(i) != '.' && !Character.isDigit(line.charAt(i)))) {
                                    sumParts += Integer.parseInt(number);
                                    break checkNumber;
                                }
                            }
                        }
                    }
                    startFromPosition = indexAfterNumber;
                }
                previousLine = currentLine;
            }
            System.out.println("The sum of parts is " + sumParts + ".");
        }
    }
}
