package Day4.Part2;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static Day4.Part1.Main.getWinners;


/*DESCRIPTION - UNFINISHED
Finds how many times a card wins, and then finds the winners of the cards following on, for each index after the original card up to
the count of wins that card has, until there are no more cards
 */
public class Main {
    public static void main(String[] args) {
        Path input = Paths.get("./src/Day4/input.txt");
        try (BufferedReader br = Files.newBufferedReader(input)) {
            String currentLine = "";
            int sum = 0;
            ArrayList<String> originalCards = new ArrayList<>();

            while((currentLine = br.readLine()) != null) {
                currentLine = currentLine.substring(10);
                originalCards.add(currentLine);
            }

            System.out.println("_+_++_+_+_+__+_+_");
            for(String line : originalCards){
                if(originalCards.indexOf(line) < 10)
                    System.out.println(line);
            }



        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    public static int getNumberOfCards(String card, ArrayList<String> origin) {
        //for each win, get the cards from the list
        int winners = getWinners(card);
        ArrayList<String> cards = new ArrayList<>();
        for(int i = 1; i <= winners; i ++){
            cards.add(origin.get(origin.indexOf(card) + i));
        }

        for(String c : cards){
            getNumberOfCards(card, origin);
        }
    return 0;
    }
}
