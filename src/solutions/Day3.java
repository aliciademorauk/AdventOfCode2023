package solutions;

import java.io.IOException;
import java.util.Arrays;

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

        for (int i = 0; i < input.size() - 1; i++) {
            if (!nextLine.isBlank()) {
                currentLine = nextLine;
            } else {currentLine = input.get(i + 1);}

            if (i < input.size() - 1) {
                nextLine = input.get(i + 1);
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
        numbersArray = Arrays.stream(nextLine.split("\\D+"))
                .filter(s -> !s.isBlank())
                .toArray(String[]::new);

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
        return 0;
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
}
