import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day3Challenge2 {
    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File("./puzzleInputs/PuzzleInputDay3.txt"))) {
            String currentLine;
            String previousLine = "";
            String nextLine = "";
            String[] linesArray = new String[3];
            String[] numbersArray;
            ArrayList<Integer> asteriskIndexes;
            int[] rangeToCheck;
            int[] numbersToMultiply = new int[2];
            int gearRatioTotal = 0;

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


                asteriskIndexes = extractAsteriskIndexes(currentLine);

                for (int asteriskIndex : asteriskIndexes) {
                    //what range to check for for this specific asterisk
                    rangeToCheck = getIndexRange(currentLine, asteriskIndex);
                    for (String line : linesArray) {
                        if (!line.isBlank()) {
                            int j = 0;
                            numbersToMultiply[0] = 0;
                            numbersToMultiply[1] = 0;
                            int i = rangeToCheck[0];
                            int number = 0;
                            while (i <= rangeToCheck[1]) {
                                if (Character.isDigit(line.charAt(i))) {
                                    number = (getNumberInRange(numbersArray, line, i));
                                    numbersToMultiply[j] = number;
                                    j++;
                                }
                                i += String.valueOf(number).length();
                            }
                            if (numbersToMultiply[0] != 0 && numbersToMultiply[1] != 0) {
                                gearRatioTotal += numbersToMultiply[0] * numbersToMultiply[0];
                            }
                        }
                    }
                }
                previousLine = currentLine;
            }
            System.out.println("Total gear ratios: " + gearRatioTotal);
        }
    }

    public static ArrayList<Integer> extractAsteriskIndexes(String line) {
        ArrayList<Integer> asteriskIndexes = new ArrayList<>();

        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '*') {
                asteriskIndexes.add(i);
            }
        }
        return asteriskIndexes;
    }

    public static int[] getIndexRange(String line, int iOfSymbol) {
        int[] rangeArray = new int[] {iOfSymbol - 1, iOfSymbol + 1};
        if (iOfSymbol == 0) {
            rangeArray[0] = iOfSymbol;
        } else if (iOfSymbol == line.length() - 1) {
            rangeArray[1] = iOfSymbol;
        }
        return rangeArray;
    }

    public static int getNumberInRange(String[] numbers, String line, int index) {
        int startFromPosition = 0;
        for (String number : numbers) {
            int index1stDigit = line.indexOf(number, startFromPosition);
            int indexLastDigit = index1stDigit;
            if (Integer.parseInt(number) > 9) {
                indexLastDigit += 1;
                if (Integer.parseInt(number) > 99) {
                    indexLastDigit += 1;
                }
            }

            if (index >= index1stDigit || index <= indexLastDigit) {
                return Integer.parseInt(number);
            }
            startFromPosition = indexLastDigit;
        }
        return 1;
    }
}
