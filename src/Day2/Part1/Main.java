package Day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

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
                System.out.println("GAME NUM: " + gameNumber);
                String[] sets = currentLine.substring(colon + 2).split("; ");

                for (String set : sets) {
                    int redCount = 0;
                    int blueCount = 0;
                    int greenCount = 0;
                    System.out.println("Set: " + set);
                    String[] marbleType = set.split(", ");
                    for (String marble : marbleType) {
                        String[] detail = marble.split(" ");
                        System.out.println(detail[1]);
                        System.out.println(detail[0]);
                        switch (detail[1]) {
                            case "red" -> redCount += Integer.parseInt(detail[0]);
                            case "green" -> greenCount += Integer.parseInt(detail[0]);
                            case "blue" -> blueCount += Integer.parseInt(detail[0]);
                        }
                        if (redCount > 12 || blueCount > 14 || greenCount > 13) {
                            valid = false;
                        }
                        System.out.println("Red: " + redCount + ", Green: " + greenCount + ", Blue: " + blueCount);


                    }

                }
                if (valid) {
                    result += Integer.parseInt(gameNumber);
                }
            }
            System.out.println(result);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}