package Day2.Part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/* DESCRIPTION
Finds the sum of the game IDs of valid games

1. For each row, separates the count of each cube type for
2. Checks each cube type if the count is over the maximum
3. If the count is over the max, the game is invalid, else the game ID is added to the final sum

 */
public class Main {
    public static void main(String[] args) {
        Path textFile = Paths.get("./src/Day2/input.txt");
        try(BufferedReader br = Files.newBufferedReader(textFile)) {
            int result = 0;
            String currentLine = "";
            while((currentLine = br.readLine()) != null) { // each game
                boolean valid = true; // valid game
                int colon = currentLine.indexOf(":");
                String gameNumber = currentLine.substring(5, colon);
                String[] sets = currentLine.substring(colon + 2).split("; ");

                for (String set : sets) {
                    int redCount = 0;
                    int blueCount = 0;
                    int greenCount = 0;
                    String[] cubeType = set.split(", ");
                    for (String cube : cubeType) {
                        String[] detail = cube.split(" ");
                        switch (detail[1]) {
                            case "red" -> redCount += Integer.parseInt(detail[0]);
                            case "green" -> greenCount += Integer.parseInt(detail[0]);
                            case "blue" -> blueCount += Integer.parseInt(detail[0]);
                        }
                        if (redCount > 12 || blueCount > 14 || greenCount > 13) {
                            valid = false;
                        }


                    }

                }
                if (valid) {
                    result += Integer.parseInt(gameNumber);
                }
            }
            System.out.println("Result: " + result);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}