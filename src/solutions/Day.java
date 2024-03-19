package solutions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

public abstract class Day {
    protected int day;

    public Day(int day) {
        this.day = day;
        try {
            solution();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract void solution() throws IOException;

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

}
