package Day8.Part1;

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
            sb.append(br.readLine());
            String[] instructions = sb.toString().split("");
            br.readLine();
            ArrayList<String> elements = new ArrayList<>();
            String currentLine = "";
            ArrayList<Node> nodes = new ArrayList<>();
            while((currentLine = br.readLine()) != null){
                String element = currentLine.substring(0,3);
                String left = currentLine.substring(7,10);
                String right = currentLine.substring(12,15);
                nodes.add(new Node(element, left,right));
                elements.add(element);
            }
            int steps = 0;
            String currentElement = "AAA";
            while(!currentElement.equals("ZZZ")){
                for(String instruction : instructions){
                    System.out.println(currentElement);
                    steps ++;
                    if(instruction.equals("L")){
                        currentElement = nodes.get(elements.indexOf(currentElement)).left;
                    }
                    if(instruction.equals("R")){
                        currentElement = nodes.get(elements.indexOf(currentElement)).right;
                    }
                }
                System.out.println(currentElement);
            }
            System.out.println(steps);


        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    public record Node(String element, String left, String right){
    }
}
