import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
public class Day3Challenge1 {

    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File("../puzzleInputs/PuzzleInputDay3.txt"))) {
            String currentLine;
            String previousLine = "";
            String nextLine = "";
            String[] numbersArray;
            String[] linesArray = new String[3]; // store previous line [0], current line [1], next line [2]
            int indexOfNumber;
            int indexBeforeNumber;
            int indexAfterNumber;
            int startFromPosition;
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
                    indexBeforeNumber = setBeforeIndex(indexOfNumber);
                    indexAfterNumber = setAfterIndex(currentLine, number, indexOfNumber);
                    for (String line : linesArray) {
                        if (!line.isBlank()) {
                            if (hasSymbolInRange(line, indexBeforeNumber, indexAfterNumber)) {
                                sumParts += Integer.parseInt(number);
                                break;
                            }
                        }
                    }
                    startFromPosition = indexAfterNumber;
                }
                previousLine = currentLine;
            }

            //Processing of last line of file input
            numbersArray = Arrays.stream(nextLine.split("\\D+"))
                    .filter(s -> !s.isBlank())
                    .toArray(String[]::new);

            startFromPosition = 0;
            for (String number : numbersArray) {
                indexOfNumber = nextLine.indexOf(number, startFromPosition);
                indexBeforeNumber = setBeforeIndex(indexOfNumber);
                indexAfterNumber = setAfterIndex(nextLine, number, indexOfNumber);

                if (hasSymbolInRange(previousLine, indexBeforeNumber, indexAfterNumber) || hasSymbolInRange(nextLine, indexBeforeNumber, indexAfterNumber)) {
                    sumParts += Integer.parseInt(number);
                }
            }
            System.out.println("The sum of parts is " + sumParts + ".");
        }
    }

    public static int setBeforeIndex(int indexOfNumber) {
        return indexOfNumber == 0 ? indexOfNumber : indexOfNumber - 1;
    }
    private static int setAfterIndex(String line, String number, int indexOfNumber) {
        return (indexOfNumber + number.length() < line.length()) ? indexOfNumber + number.length() : indexOfNumber + number.length() - 1;
    }
    private static boolean hasSymbolInRange (String line, int indexBeforeNumber, int indexAfterNumber) {
        for (int i = indexBeforeNumber; i <= indexAfterNumber; i++) {
            if ((line.charAt(i) != '.' && !Character.isDigit(line.charAt(i)))) {
                return true;
            }
        }
        return false;
    }
}
