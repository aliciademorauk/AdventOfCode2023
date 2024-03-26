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
        int output = 0;
        int pointsMultiplier = 2;
        for (String cardLine : input) {
            output += (int) Math.pow(pointsMultiplier, countMatches(cardLine) - 1);
        }
        return output;
    }

    @Override
    public int solvePart2() {
        HashMap<Integer, Integer> cardMatches = new HashMap<>();
        for (int j = 1; j <= input.size(); j++) {
            cardMatches.put(j, 1);
        }
        int output = 0;
        int totalMatches;
        int i = 1;

        for (String cardLine : input) {
            totalMatches = countMatches(cardLine);
            for (int x = i + 1; x <= i + totalMatches; x++) {
                cardMatches.put(x, cardMatches.get(x) + cardMatches.get(i));
            }
            i++;
        }

        for (int value : cardMatches.values()) {
            output += value;
        }
        return output;
    }

    private int countMatches(String card) {
        int totalMatches = 0;
        List<Integer> winningNumbers = extractNumbers((card.split(":"))[1].split("\\|")[0]);
        List<Integer> myNumbers = extractNumbers((card.split(":"))[1].split("\\|")[1]);

        for (int winner : winningNumbers) {
            if (myNumbers.contains(winner)) {
                totalMatches++;
            }
        }
        return totalMatches;
    }

    private List<Integer> extractNumbers(String line) {
        // Split the string by whitespace and convert the resulting array to a list of integers
        return Arrays.stream(line.trim().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}










// The lineMatches HashMap stores the card number and the number of cards of that number (e.g. (Card) 14: 5).
// Therefore, for the number of matches that we find in a given card (stored in totalMatches), we are going to add
// to the next 'totalMatches' cards (e.g. 4), the number of scratchcards that we had of the given card
// e.g. if we have 5 Card 14's and Card 14 has 4 matches, then to the next 4 cards we want to add 5 to the
// current amount of that card number that we have, since each of the Card 14s will generate another scratchcard of each
// of the next 4, so 5 Card 14s generate: 5 Card 15s, 5 Card 16s, 5 Card 17s, 5 Card 18s.
