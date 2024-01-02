package Day6.Part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/*  DESCRIPTION
Find the maximum number of ways that the distance can be beaten in the given time,
where the button is held for x ms and the speed is x mm/s
returns the product of the number of ways to beat the record for each race
 */
public class Main {
    public static void main(String[] args) {
        Path file = Paths.get("./src/Day6/input.txt");

        try (BufferedReader br = Files.newBufferedReader(file)){

            String times = br.readLine();
            String distances = br.readLine();
            String[] timeList = times.split("\\D");
            ArrayList<Integer> timeValues = new ArrayList<>();

            String[] distanceList = distances.split("\\D");
            ArrayList<Integer> distanceValues = new ArrayList<>();

            for (String s : timeList){
                if(!s.isEmpty()){
                    timeValues.add(Integer.valueOf(s));
                }
            }
            for (String s : distanceList){
                if(!s.isEmpty()){
                    distanceValues.add(Integer.valueOf(s));
                }
            }
            int speed  = 0;
            int timeHeld = 0;
            int answer = 1;
            for(int time : timeValues){
                int count = 0;
                int record = distanceValues.get(timeValues.indexOf(time));

                for(int i = 0; i < time; i ++){
                    timeHeld = i;
                    speed = timeHeld;
                    int raceDistance = speed * (time-timeHeld);
                    if(raceDistance > record){
                        count++;
                    }
                }
                answer = answer*count;
            }
            System.out.println("Result: " + answer);








        }catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }
}
