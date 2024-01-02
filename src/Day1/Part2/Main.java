package Day1.Part2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


// DESCRIPTION  - Same as Part 1, however also includes written numbers
public class Main {
    public static void main(String[] args) {
        int sumOfResult = 0;
        Path textFile = Paths.get("./src/Day1/input.txt");
        try (BufferedReader br = Files.newBufferedReader(textFile)) {
            String currentLine = "";

            while ((currentLine = br.readLine()) != null) {
                StringBuilder numbers = new StringBuilder();
                for (int i = 0; i < currentLine.length(); i++) { // for each character in line

                    StringBuilder number = new StringBuilder(); //new word

                    for (int x = i; x < currentLine.length(); x++) { //add letter to word unless number
                        if (!Character.isDigit(currentLine.charAt(x))) {
                            number.append(currentLine.charAt(x));
                        }
                        if (Character.isDigit(currentLine.charAt(x))) {
                            numbers.append(currentLine.charAt(x));
                            break;
                        }
                        if (number.length() > 5) {
                            break;
                        }
                        if (getIntValue(number.toString()) > -1) {
                            numbers.append(getIntValue(number.toString()));
                            i = x - 1;
                            break;
                        }
                        if (x + 1 != currentLine.length()) {
                            if (Character.isDigit(currentLine.charAt(x + 1))) {
                                break;
                            }
                        }
                    }
                }
                //when line is read
                String twoDigit = String.valueOf(numbers.charAt(0));
                twoDigit += String.valueOf(numbers.charAt(numbers.length() - 1));
                sumOfResult += Integer.parseInt(twoDigit);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        System.out.println("RESULT: " + sumOfResult);
    }

    public static int getIntValue(String number) {
        return switch (number.toLowerCase()) {
            case "one" -> 1;
            case "two" -> 2;
            case "three" -> 3;
            case "four" -> 4;
            case "five" -> 5;
            case "six" -> 6;
            case "seven" -> 7;
            case "eight" -> 8;
            case "nine" -> 9;
            case "zero" -> 0;
            default -> -1;
        };
    }
}