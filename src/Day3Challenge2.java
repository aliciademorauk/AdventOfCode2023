import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day3Challenge2 {
    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File("src/puzzleInputs/PuzzleInputDay3.txt"))) {
            String[] linesArray = new String[] {"", "", ""}; // Array with previous [0], current [1] and next [2] line
            String[][] numbersArray = new String[3][]; // Two-dimensional array to store previous [0], current [1] and next [2] numbers' arrays
            ArrayList<Integer> asteriskIndexes;
            int[] rangeToCheck;
            int[] numbersToMultiply = new int[]{0,0};
            int gearRatioTotal = 0;

            while (scanner.hasNextLine()) {
                if (!linesArray[2].isBlank()) {
                    linesArray[1] = linesArray[2];
                } else {
                    linesArray[1] = scanner.nextLine();
                }

                if (scanner.hasNextLine()) {
                    linesArray[2] = scanner.nextLine();
                }

                for (int i = 0; i < linesArray.length; i++) {
                    numbersArray[i] = extractNumbers(linesArray[i]);
                }

                asteriskIndexes = extractAsteriskIndexes(linesArray[1]);

                for (int asteriskIndex : asteriskIndexes) {
                    // What range to check for around this specific asterisk's index
                    rangeToCheck = getIndexRange(linesArray[1], asteriskIndex); // int[] e.g. [3,5]

                    // Check previous and next line for digits in the relevant range around the asterisk in current line
                    int y = 0;
                    int i = rangeToCheck[0];
                    while (i <= rangeToCheck[1]) {
                        if (Character.isDigit(linesArray[0].charAt(i))) {
                            int number = getNumberInRange(numbersArray[0], linesArray[0], i);
                            if (!contains(numbersToMultiply, number)) {
                                numbersToMultiply[y++] = number;
                            }
                        }

                        if (Character.isDigit(linesArray[2].charAt(i))) {
                            int number = getNumberInRange(numbersArray[2], linesArray[2], i);
                            if (!contains(numbersToMultiply, number)) {
                                numbersToMultiply[y++] = number;
                            }
                        }

                        i++;
                    }

                    // Check current line
                    for (int number : rangeToCheck) {
                        if (Character.isDigit(linesArray[1].charAt(number))) {
                            numbersToMultiply[y] = getNumberInRange(numbersArray[1], linesArray[1], number);
                            y++;
                        }
                    }

                    if (Arrays.stream(numbersToMultiply).allMatch(num -> num != 0)) {
                        gearRatioTotal += numbersToMultiply[0] * numbersToMultiply[1];
                    }

                    Arrays.fill(numbersToMultiply, 0);
                }
                linesArray[0] = linesArray[1];
            }
            System.out.println("Total gear ratios: " + gearRatioTotal);
        }
    }

    public static String[] extractNumbers(String line) {
        return Arrays.stream(line.split("\\D+"))
                .filter(s -> !s.isBlank())
                .toArray(String[]::new);
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
        int[] rangeArray = new int[]{iOfSymbol - 1, iOfSymbol + 1};
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
            if (index >= index1stDigit && index <= indexLastDigit) {
                return Integer.parseInt(number);
            }
            startFromPosition = indexLastDigit;
        }
        return 1;
    }

    public static boolean contains(int[] array, int otherNumber) {
        for (int number : array) {
            if (number == otherNumber) {
                return true;
            }
        }
        return false;
    }
}
