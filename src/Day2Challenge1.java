import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class Day2Challenge1 {
    public static void main(String[] args) throws FileNotFoundException{
        try (Scanner scanner = new Scanner(new File("puzzleInputs/PuzzleInputDay2"))) {
            int lineIDCount = 1;
            int totalSumIDs = 0;
            int sumIDsNotPossible = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String [] numbersArray = line.split("\\D+");
                for () {

                }

            }

        }

    }
}
