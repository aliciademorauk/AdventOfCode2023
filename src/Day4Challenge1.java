import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day4Challenge1 {
    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File("src/puzzleInputs/PuzzleInputDay4.txt"))) {
            String[] line = new String[2];
            List<Integer> winningNumbers;
            List<Integer> myNumbers;
            int pointsMultiplier = 2;
            int totalMatches = 0;
            int total = 0;

            while (scanner.hasNextLine()) {
                line = (scanner.nextLine().split(":"))[1].split("\\|");
                winningNumbers = extractNumbers(line[0]);
                myNumbers = extractNumbers(line[1]);

                for (int winner : winningNumbers) {
                    if (myNumbers.contains(winner)) {
                        totalMatches++;
                    }
                }
                total += Math.pow(pointsMultiplier, totalMatches - 1);
                totalMatches = 0;
            }
            System.out.println(total);
        }
    }
    private static List<Integer> extractNumbers(String line) {
        // Split the string by whitespace and convert the resulting array to a list of integers
        return Arrays.stream(line.trim().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
