package solutions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Day3 extends Day {
    public Day3() throws IOException {
        super(3);
    }

    @Override
    public int solvePart1() {
        String currentLine;
        String previousLine = "";
        String nextLine = "";
        String[] numbersArray;
        String[] linesArray = new String[3]; // store previous line [0], current line [1], next line [2]
        int indexOfNumber;
        int indexBeforeNumber;
        int indexAfterNumber;
        int startFromPosition;
        int output = 0;
        Iterator<String> iterator = input.iterator();

        while (iterator.hasNext()) {
            if (!nextLine.isBlank()) {
                currentLine = nextLine;
            } else {currentLine = iterator.next();}

            if (iterator.hasNext()) {
                nextLine = iterator.next();
            }

            linesArray[0] = previousLine;
            linesArray[1] = currentLine;
            linesArray[2] = nextLine;
            numbersArray = extractNumbers(currentLine);

            startFromPosition = 0;
            for (String number : numbersArray) {
                indexOfNumber = currentLine.indexOf(number, startFromPosition);
                indexBeforeNumber = setBeforeIndex(indexOfNumber);
                indexAfterNumber = setAfterIndex(currentLine, number, indexOfNumber);
                for (String line : linesArray) {
                    if (!line.isBlank()) {
                        if (hasSymbolInRange(line, indexBeforeNumber, indexAfterNumber)) {
                            output += Integer.parseInt(number);
                            break;
                        }
                    }
                }
                startFromPosition = indexAfterNumber;
            }
            previousLine = currentLine;
        }
        //Processing of last line of file input
        numbersArray = extractNumbers(nextLine);
        startFromPosition = 0;
        for (String number : numbersArray) {
            indexOfNumber = nextLine.indexOf(number, startFromPosition);
            indexBeforeNumber = setBeforeIndex(indexOfNumber);
            indexAfterNumber = setAfterIndex(nextLine, number, indexOfNumber);

            if (hasSymbolInRange(previousLine, indexBeforeNumber, indexAfterNumber) || hasSymbolInRange(nextLine, indexBeforeNumber, indexAfterNumber)) {
                output += Integer.parseInt(number);
            }
        }
        return output;
    }

    @Override
    public int solvePart2() {
        String[] linesArray = new String[] {"", "", ""}; // Array with previous [0], current [1] and next [2] line
        String[][] numbersArray = new String[3][]; // Two-dimensional array to store previous [0], current [1] and next [2] numbers' arrays
        ArrayList<Integer> asteriskIndexes;
        int[] rangeToCheck;
        int[] numbersToMultiply = new int[]{0,0};
        int output = 0;
        Iterator<String> iterator = input.iterator();

        while (iterator.hasNext()) {
            if (!linesArray[2].isBlank()) {
                linesArray[1] = linesArray[2];
            } else {
                linesArray[1] = iterator.next();
            }

            if (iterator.hasNext()) {
                linesArray[2] = iterator.next();
            }

            for (int i = 0; i < linesArray.length; i++) {
                numbersArray[i] = extractNumbers(linesArray[i]);
            }

            asteriskIndexes = extractAsteriskIndexes(linesArray[1]);
            for (int asteriskIndex : asteriskIndexes) {
                rangeToCheck = getIndexRange(linesArray[1], asteriskIndex);
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
                    output += numbersToMultiply[0] * numbersToMultiply[1];
                }
                Arrays.fill(numbersToMultiply, 0);
            }
            linesArray[0] = linesArray[1];
        }
        return output;
    }

    private String[] extractNumbers(String line) {
        return Arrays.stream(line.split("\\D+"))
                .filter(s -> !s.isBlank())
                .toArray(String[]::new);
    }

    private int setBeforeIndex(int indexOfNumber) {
        return indexOfNumber == 0 ? indexOfNumber : indexOfNumber - 1;
    }

    private int setAfterIndex(String line, String number, int indexOfNumber) {
        return (indexOfNumber + number.length() < line.length()) ? indexOfNumber + number.length() : indexOfNumber + number.length() - 1;
    }

    private boolean hasSymbolInRange (String line, int indexBeforeNumber, int indexAfterNumber) {
        for (int i = indexBeforeNumber; i <= indexAfterNumber; i++) {
            if ((line.charAt(i) != '.' && !Character.isDigit(line.charAt(i)))) {
                return true;
            }
        }
        return false;
    }

    private ArrayList<Integer> extractAsteriskIndexes(String line) {
        ArrayList<Integer> asteriskIndexes = new ArrayList<>();
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '*') {
                asteriskIndexes.add(i);
            }
        }
        return asteriskIndexes;
    }

    private int[] getIndexRange(String line, int iOfSymbol) {
        int[] rangeArray = new int[]{iOfSymbol - 1, iOfSymbol + 1};
        if (iOfSymbol == 0) {
            rangeArray[0] = iOfSymbol;
        } else if (iOfSymbol == line.length() - 1) {
            rangeArray[1] = iOfSymbol;
        }
        return rangeArray;
    }

    private int getNumberInRange(String[] numbers, String line, int index) {
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

    private boolean contains(int[] array, int otherNumber) {
        for (int number : array) {
            if (number == otherNumber) {
                return true;
            }
        }
        return false;
    }
}
