import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

// The Elf would first like to know which games would have been possible if the bag contained only 12 red cubes,
// 13 green cubes, and 14 blue cubes?

public class Day2Challenge1 {
    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File("puzzleInputs/PuzzleInputDay2.txt"))) {
            int lineIDCount = 1;
            int totalSumIDs = 0;
            int sumIDsNotPossible = 0;
            boolean notPossibleIDsSumHasBeenUpdated = false;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(":");
                line = parts[1].trim();
                String[] numbersArray = line.split("\\D+");

                //Skip line if all numbers are < 12
                boolean allLessThanOrEqualTo12 = true;
                for (String element : numbersArray) {
                    if (Integer.parseInt(element) > 12) {
                        allLessThanOrEqualTo12 = false;
                        break;
                    }
                }

                //Process the line if at least one  number is > 12 and if the sumIDsNotPossible has not been updated for this line
                if (!allLessThanOrEqualTo12) {
                    for (String element : numbersArray) {
                        if (!notPossibleIDsSumHasBeenUpdated && !element.isBlank() && Integer.parseInt(element) > 12) {
                            int indexOfNumber = line.indexOf(element);
                            indexOfNumber += 1;

                            if (Integer.parseInt(element) > 14) {
                                sumIDsNotPossible += lineIDCount;
                                notPossibleIDsSumHasBeenUpdated = true;
                            } else if (Integer.parseInt(element) > 13 && indexOfNumber <= (line.length() - 1 - (" green").length())) {
                                if (line.startsWith(" g", indexOfNumber + 1)) {
                                    sumIDsNotPossible += lineIDCount;
                                    notPossibleIDsSumHasBeenUpdated = true;
                                }
                            } else if (Integer.parseInt(element) > 12 && indexOfNumber <= (line.length() - 1 - (" red").length())) {
                                if (line.startsWith(" r", indexOfNumber + 1)) {
                                    sumIDsNotPossible += lineIDCount;
                                    notPossibleIDsSumHasBeenUpdated = true;
                                }
                            }
                        }
                    }
                }
                totalSumIDs += lineIDCount;
                lineIDCount ++;
                notPossibleIDsSumHasBeenUpdated = false;
            }

            int sumIDsPossible = totalSumIDs - sumIDsNotPossible;
            System.out.println("The total sum of IDs is " + totalSumIDs + " and the total sum of game IDs that are possible is " + sumIDsPossible);
        }
    }
}
