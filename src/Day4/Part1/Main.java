package Day4.Part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/* DESCRIPTION
Doubles the score of each card for each win the card has, starting at 1 point
a card wins if both sets seperated by | include the same number
 */
public class Main {
    public static void main(String[] args) {
        Path input = Paths.get("./src/Day4/input.txt");
        try (BufferedReader br = Files.newBufferedReader(input)) {
            String currentLine = "";
            int sum = 0;
            while((currentLine = br.readLine()) != null) {
                int winners = getWinners(currentLine);
                double result = 0;
                if (winners > 1) {
                    result = 1 * Math.pow(2, winners - 1);
                }
                if (winners == 1) {
                    result = 1;
                }
                sum += (int) result;
            }
            System.out.println("Result: " + sum);


        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static int getWinners(String currentLine) {
        String[] allNumbers = (currentLine.substring(9)).split("\\|");
        String[] numbers = allNumbers[0].split(" ");
        String[] winning = allNumbers[1].split(" ");
        int winners = 0;

        for (String number : numbers) {
            for (String s : winning) {
                if (number.equals(s) && !number.isEmpty()) {
                    winners++;
                }
            }
        }
        return winners;
    }
}
