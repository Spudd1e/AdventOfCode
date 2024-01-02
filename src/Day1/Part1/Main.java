package Day1.Part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/*  DESCRIPTION
1. Takes first and last digit from a line
2. Forms 2 digit number with the digits
3. Adds 2 digit number to sum
4. Outputs the sum of all the 2 digit numbers
 */


public class Main {
    public static void main(String[] args) {
        int sumOfResult = 0;
            Path textFile = Paths.get("./src/Day1/input.txt");
            try(BufferedReader br = Files.newBufferedReader(textFile)){
                String currentLine = "";
                while((currentLine = br.readLine()) != null) {
                    StringBuilder numbers = new StringBuilder();
                    String result = "";
                    for (int i = 0; i < currentLine.length(); i++) {
                        char x = currentLine.charAt(i);
                        if (Character.isDigit(x)) {
                            numbers.append(x);
                        }
                    }
                    result += numbers.charAt(0);
                    result += numbers.charAt(numbers.length() - 1);
                    sumOfResult += Integer.parseInt(result);
                }
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }
            System.out.println("Result: " + sumOfResult);
    }
}