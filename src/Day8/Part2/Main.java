package Day8.Part2;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

//UNFINISHED
public class Main {
    public static void main(String[] args) {


        Path textFile = Paths.get("./src/Day8/input.txt");
        try (BufferedReader br = Files.newBufferedReader(textFile)) {
            StringBuilder sb = new StringBuilder();
            sb.append(br.readLine());
            br.readLine();
            String[] instructions = sb.toString().split("");
            ArrayList<String> elements = new ArrayList<>();
            String currentLine = "";
            ArrayList<Node> nodes = new ArrayList<>();
            ArrayList<String> trackedElements = new ArrayList<>();
            while ((currentLine = br.readLine()) != null) {
                String element = currentLine.substring(0, 3);
                elements.add(element);
                if (element.charAt(2) == 'A') {
                    trackedElements.add(element);
                }
                String left = currentLine.substring(7, 10);
                String right = currentLine.substring(12, 15);
                nodes.add(new Node(element, left, right));
            }
            ArrayList<Long> stepCounts = new ArrayList<>();
            for (String element : trackedElements) {
                long steps = 0;
                String currentElement = element;
                boolean stop = false;
                while (!stop) {
                    for (String instruction : instructions) {
                        assert currentElement != null;
                        if (currentElement.charAt(2) == 'Z') {
                            System.out.println(currentElement);
                            stop = true;
                        }
                        if (instruction.equals("L")) {
                            currentElement = nodes.get(elements.indexOf(currentElement)).left;
                        }
                        if (instruction.equals("R")) {
                            currentElement = nodes.get(elements.indexOf(currentElement)).right;
                        }

                        steps++;
                    }
                }
                stepCounts.add(steps);
            }
            long max = stepCounts.get(0);
            System.out.println(max);
            for(long stepCount: stepCounts){
                if(stepCount > max){
                    max = stepCount;
                }
            }
            long lcm = max;
            while(!lowestCommonMultiple(stepCounts, lcm)) {
                lcm += max;
            }
            System.out.println(stepCounts);
            System.out.println(lcm);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public record Node(String element, String left, String right) {
    }
    public static boolean lowestCommonMultiple(ArrayList<Long> stepList, long lcm){
        boolean isLcm = true;
        for(long step : stepList){
            if (lcm % step != 0) {
                isLcm = false;
                break;
            }
        }
    return isLcm;
    }

}
