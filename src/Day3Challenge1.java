import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Day3Challenge1 {

    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File("puzzleInputs/PuzzleInputDay3.txt"))) {
            String line;
            String previousLine = "";
            String nextLine = "";
            String[] numbersArray;
            String[] linesArray = new String[3];
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

                linesArray[0] = previousLine;
                linesArray[1] = line;
                linesArray[2] = nextLine;
                numbersArray = line.split("\\D+");

                for (String number : numbersArray) {
                    indexOfNumber = line.indexOf(number);
                    indexAfterNumber = (indexOfNumber + number.length() - 1 < line.length() - 1) ? indexOfNumber + number.length() : indexOfNumber + number.length() - 1;
                    indexBeforeNumber = indexOfNumber == 0 ? indexOfNumber : indexOfNumber - 1;

                    for (String thisLine : linesArray) {
                        if (!thisLine.isBlank()) {
                            for (int i = indexBeforeNumber; i <= indexAfterNumber; i++) {
                                if ((thisLine.charAt(i) != '.' && !Character.isDigit(thisLine.charAt(i)))) {
                                    sumParts += Integer.parseInt(number);
                                }
                            }
                        }
                    }
                }
                previousLine = line;
            }
            System.out.println("The sum of parts is " + sumParts + ".");
        }
    }
}
