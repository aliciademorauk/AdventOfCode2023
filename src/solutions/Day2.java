package solutions;

import java.io.IOException;
import java.util.Arrays;

public class Day2 extends Day {

    public Day2() throws IOException {
        super(2);
    }

    @Override
    public int solvePart1() {
        int[] numbersArray;
        int startFromPosition;
        int lineID = 1;
        boolean isGameIDPossible;
        int output = 0;

        for (String line : input) {
            line = (line.split(":"))[1].trim();
            numbersArray = extractNumbers(line);
            isGameIDPossible = true;
            startFromPosition = 0;
            for (int number : numbersArray) {
                startFromPosition = calculateIndexAfter2ndDigit(line, number, startFromPosition);
                if (number > 12 && (line.startsWith(" r", startFromPosition) || number > 14 || (number > 13 && line.startsWith(" g", startFromPosition)))) {
                    isGameIDPossible = false;
                    break;
                }
            }

            if (isGameIDPossible) {
                output += lineID;
            }
            lineID++;
        }
        return output;
    }

    @Override
    public int solvePart2() {
        int[] numbersArray;
        String[] coloursArray = {" r", " b", " g"};
        int[] maxValuesArray = new int[3]; // max values, indexes correspond with red [0], blue [1], green [2], matching order in array above
        int startFromPosition;
        int product;
        int output = 0;

        for (String line : input) {
            line = (line.split(":"))[1].trim();
            numbersArray = extractNumbers(line);
            startFromPosition = 0;
            for (int number: numbersArray) {
                startFromPosition = calculateIndexAfterNumber(line, number, startFromPosition);

                for (int i = 0; i < maxValuesArray.length; i++) {
                    if (line.startsWith(coloursArray[i], startFromPosition) && number > maxValuesArray[i]) {
                        maxValuesArray[i] = number;
                    }
                }
            }
            product = 1;
            for (int number : maxValuesArray) {
                product *= number;
            }
            output += product;
            Arrays.fill(maxValuesArray, 0);
        }
        return output;
    }

    private int[] extractNumbers(String line) {
        return Arrays.stream(line.split("\\D+"))
                .filter(s -> !s.isEmpty())
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private int calculateIndexAfter2ndDigit (String line, int number, int startFromPosition) {
        return line.indexOf(String.valueOf(number), startFromPosition) + 2;
    }

    public static int calculateIndexAfterNumber (String line, int number, int startFromPosition) {
        int indexAfterNumber = line.indexOf(String.valueOf(number), startFromPosition) + 1;
        return number > 9 ? indexAfterNumber + 1 : indexAfterNumber;
    }
}
