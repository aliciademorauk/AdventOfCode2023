import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

//
public class Day2Challenge2 {
    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File("puzzleInputs/PuzzleInputDay2.txt"))) {
            String line;
            int[] numbersArray; // array of numbers in each Game
            String[] coloursArray = {" r", " b", " g"};
            int[] maxValuesArray = new int[3]; // max values, indexes correspond with red [0], blue [1], green [2], matching order in array above
            int startFromPosition;
            int product;
            int totalSumPower = 0;

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                line = (line.split(":"))[1].trim();
                numbersArray = Arrays.stream(line.split("\\D+"))
                        .filter(s -> !s.isEmpty())
                        .mapToInt(Integer::parseInt)
                        .toArray();
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
                totalSumPower += product;
                Arrays.fill(maxValuesArray, 0);
            }
            System.out.println("The total sum of the cube powers across all games is " + totalSumPower + ".");
        }
    }

    public static int calculateIndexAfterNumber (String line, int number, int startFromPosition) {
        int indexAfterNumber = line.indexOf(String.valueOf(number), startFromPosition) + 1;
        return number > 9 ? indexAfterNumber + 1 : indexAfterNumber;
    }
}
