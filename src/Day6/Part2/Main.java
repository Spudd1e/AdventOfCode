package Day6.Part2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


// DESCRIPTION - same as part1 however the input is much larger, and the output is the number of ways to win the race


public class Main {

    public static void main(String[] args) {
        Path file = Paths.get("./src/Day6/input.txt");
        try (BufferedReader br = Files.newBufferedReader(file)) {

            String times = br.readLine();
            String distances = br.readLine();
            String[] timeList = times.split("\\D");
            StringBuilder sb = new StringBuilder();
            for (String s : timeList){
                if(!s.isEmpty()){
                    sb.append(s);
                }
            }
            int time = Integer.parseInt(sb.toString());
            String[] distanceList = distances.split("\\D");
            sb.setLength(0);
            for (String s : distanceList){
                if(!s.isEmpty()){
                    sb.append(s);
                }
            }
            long distance = Long.parseLong(sb.toString());
            int count = 0;
            for(long i = 0; i < time;i ++){
                long raceDistance =  i * (time - i);
                if(raceDistance > distance){
                    count ++;
                }
            }
            System.out.println("Result: " + count);

        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}
