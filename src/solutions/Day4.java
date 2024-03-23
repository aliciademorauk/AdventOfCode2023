package solutions;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        String[] lineParts = new String[2];
        List<Integer> winningNumbers;
        List<Integer> myNumbers;
        int totalMatches = 0;
        int output = 0;
        int i = 1;

        HashMap<Integer, Integer> lineMatches = new HashMap<>();
        for (int j = 1; j <= input.size(); j++) {
            lineMatches.put(j, 1);
        }

        for (String line : input) {
            lineParts = (line.split(":"))[1].split("\\|");
            winningNumbers = extractNumbers(lineParts[0]);
            myNumbers = extractNumbers(lineParts[1]);

            for (int winner : winningNumbers) {
                if (myNumbers.contains(winner)) {
                    totalMatches++;
                }
            }

            for (int x = i + 1; x <= i + totalMatches; x++) {
                int updatedValue = lineMatches.get(x) + 1;
                lineMatches.put(x, updatedValue);
                //lineMatches.replace(x, lineMatches.get(x), lineMatches.get(x) + 1);
            }

            totalMatches = 0;
            i++;
        }

        for (Map.Entry<Integer, Integer> entry : lineMatches.entrySet()) {
            output += entry.getValue();
        }

//        for (Map.Entry<Integer, Integer> entry : lineMatches.entrySet()) {
//            System.out.println(entry.getKey() + ": " + entry.getValue());
//        }

        return output;
    }

    private List<Integer> extractNumbers(String line) {
        // Split the string by whitespace and convert the resulting array to a list of integers
        return Arrays.stream(line.trim().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
