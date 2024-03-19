package solutions;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day4 extends Day {

    public Day4() throws IOException {
        super(4);
    }

    @Override
    public int solvePart1() {
        String[] lineParts = new String[2];
        List<Integer> winningNumbers;
        List<Integer> myNumbers;
        int pointsMultiplier = 2;
        int totalMatches = 0;
        int output = 0;
        for (String line : input) {
            lineParts = (line.split(":"))[1].split("\\|");
            winningNumbers = extractNumbers(lineParts[0]);
            myNumbers = extractNumbers(lineParts[1]);

            for (int winner : winningNumbers) {
                if (myNumbers.contains(winner)) {
                    totalMatches++;
                }
            }
            output += (int) Math.pow(pointsMultiplier, totalMatches - 1);
            totalMatches = 0;
        }
        return output;
    }

    @Override
    public int solvePart2() {
        int output = 0;
        return output;
    }

    private List<Integer> extractNumbers(String line) {
        // Split the string by whitespace and convert the resulting array to a list of integers
        return Arrays.stream(line.trim().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
