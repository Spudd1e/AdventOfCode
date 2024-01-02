package Day2.Part2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
/* DESCRIPTION
Finds the power set of the minimum amount of cubes per game, and adds it to the total sum.
 */
public class Main {
    public static void main(String[] args) {
        Path textFile = Paths.get("./src/Day2/input.txt");
        try (BufferedReader br = Files.newBufferedReader(textFile)) {
            int result = 0;
            String currentLine = "";
            while ((currentLine = br.readLine()) != null) { // each game
                int colon = currentLine.indexOf(":");
                String[] sets = currentLine.substring(colon + 2).split("; ");
                int redMin = 0;
                int blueMin = 0;
                int greenMin = 0;
                for (String set : sets) {
                    String[] cubeType = set.split(", ");
                    for (String cube : cubeType) {
                        String[] detail = cube.split(" ");
                        int count = Integer.parseInt(detail[0]);
                        switch (detail[1]) {
                            case "red":
                                if (count > redMin) {
                                    redMin = count;
                                }
                                break;
                            case "green":
                                if (count > greenMin) {
                                    greenMin = count;
                                }
                                break;
                            case "blue":
                                if (count > blueMin) {
                                    blueMin = count;
                                }
                                break;
                        }
                    }
                }
                int power = (redMin * blueMin * greenMin);
                result += power;
            }
            System.out.println("Result: " + result);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}