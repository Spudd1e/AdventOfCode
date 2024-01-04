package Day8.Part2;

import java.io.BufferedReader;
import java.io.IOException;
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
            System.out.println(trackedElements);
            int steps = 0;
            boolean endingInZ = false;

            while (!endingInZ) {
                for (String instruction : instructions) {
                    steps++;
                    for (int i = 0; i < trackedElements.size(); i++) {
                        if (instruction.equals("L"))
                            trackedElements.set(i, nodes.get(elements.indexOf(trackedElements.get(i))).left);
                        if (instruction.equals("R")) {
                            trackedElements.set(i, nodes.get(elements.indexOf(trackedElements.get(i))).right);
                        }
                    }
                }
                int zCount = 0;
                if (trackedElements.get(0).charAt(2) == 'Z')
                    for (String e : trackedElements) {
                        if (e.charAt(2) == 'Z') {
                            zCount++;
                            System.out.println("Containing z: " + zCount);
                        }
                    }
                if (zCount == trackedElements.size()) {
                    endingInZ = true;
                }

            }
            System.out.println(steps);


        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public record Node(String element, String left, String right) {
    }

}
