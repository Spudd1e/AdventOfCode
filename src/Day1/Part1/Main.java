package day1part1;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        int sumOfResult = 0;
            Path textFile = Paths.get("./src/day1part1/day1.txt");
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
            System.out.println(sumOfResult);
    }
}