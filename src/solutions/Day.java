package solutions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

public abstract class Day {
    protected int day;
    protected Collection<String> input;

    public Day(int day) throws IOException {
        this.day = day;
        this.input = readInput();
    }

    Collection<String> readInput() throws IOException {
        Collection<String> list = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("inputs/puzzleInput" + day + ".txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        }
        return list;
    }

    public abstract int solvePart1();
    public abstract int solvePart2();
}
