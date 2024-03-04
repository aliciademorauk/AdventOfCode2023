import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day2Challenge2 {
    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File("puzzleInputs/PuzzleInputDay2.txt"))) {
            String line;
            String[] parts;
            String part;
            int[] numbersArray;
            int lineIndex = 0;
            String redString = " r";
            String blueString = " b";
            String greenString = " g";
            List<Integer> redList;
            List<Integer> blueList;
            List<Integer> greenList;
            int indexAfterNumber;
            int startFromPosition;
            int totalSumPower = 0;

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                parts = line.split(":");
                line = parts[1].trim();
                numbersArray = Arrays.stream(line.split("\\D+"))
                        .filter(s -> !s.isEmpty())
                        .mapToInt(Integer::parseInt)
                        .toArray();
                redList = new ArrayList<>();
                blueList = new ArrayList<>();
                greenList = new ArrayList<>();
                startFromPosition = 0;
                for (int number: numbersArray) {
                    indexAfterNumber = calculateIndexAfterNumber(line, number, startFromPosition);
                    startFromPosition = indexAfterNumber;
                    if (line.startsWith(redString, indexAfterNumber)) {
                        redList.add(number);
                    } else if (line.startsWith(blueString, indexAfterNumber)) {
                        blueList.add(number);
                    } else if (line.startsWith(greenString, indexAfterNumber)) {
                        greenList.add(number);
                    }
                }
                totalSumPower += (Collections.max(redList) * Collections.max(blueList) * Collections.max(greenList));
                lineIndex ++;
            }

            System.out.println("The total sum of the cube powers across all games is " + totalSumPower + ".");
        }
    }

    public static int calculateIndexAfterNumber (String line, int number, int startFromPosition) {
        int indexAfterNumber = line.indexOf(String.valueOf(number), startFromPosition) + 1;
        if (number > 9) {
            indexAfterNumber += 1;
        }
        return indexAfterNumber;
    }
}
