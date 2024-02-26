//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Part 1
        Scanner scanner = null;

        try {
            scanner = new Scanner(new File("PuzzleInputDay1.txt"));
            int totalSumPartOne = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String numbersOnly = line.replaceAll("[^0-9]", "");

                if (numbersOnly.length() > 1) {
                    String stringNumber = "" + numbersOnly.charAt(0) + numbersOnly.charAt(numbersOnly.length() - 1);
                    totalSumPartOne += Integer.parseInt(stringNumber);
                } else {
                    String stringNumber = "" + numbersOnly.charAt(0) + numbersOnly.charAt(0);
                    totalSumPartOne += Integer.parseInt(stringNumber);
                }
            }

            System.out.printf("The total sum of calibration values in Part 1 is %d", totalSumPartOne);

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}