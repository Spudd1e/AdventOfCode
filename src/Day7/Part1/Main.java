package Day7.Part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


/*  DESCRIPTION
Puts the hands in order of strength in ascending order
Multiplies the bid against each hand by its rank and adds result to total sum
 */
public class Main {
    public static void main(String[] args) {
        Path file = Paths.get("./src/Day7/input.txt");
        ArrayList<Hand> handList = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(file)) {
            String line;
            while ((line = br.readLine()) != null) {
                handList.add(new Hand(line.split(" ")[0], Integer.parseInt(line.split(" ")[1])));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        ArrayList<Hand> fiveOfAKinds = new ArrayList<>();
        ArrayList<Hand> fourOfAKinds = new ArrayList<>();
        ArrayList<Hand> fullHouses = new ArrayList<>();
        ArrayList<Hand> threeOfAKinds = new ArrayList<>();
        ArrayList<Hand> twoPairs = new ArrayList<>();
        ArrayList<Hand> onePairs = new ArrayList<>();
        ArrayList<Hand> highCards = new ArrayList<>();

        for (Hand hand : handList) {
            switch (getType(hand.cards)) {
                case "fiveOfAKind":
                    fiveOfAKinds.add(hand);
                    break;
                case "fourOfAKind":
                    fourOfAKinds.add(hand);
                    break;
                case "fullHouse":
                    fullHouses.add(hand);
                    break;
                case "threeOfAKind":
                    threeOfAKinds.add(hand);
                    break;
                case "twoPair":
                    twoPairs.add(hand);
                    break;
                case "onePair":
                    onePairs.add(hand);
                    break;
                case "highCard":
                    highCards.add(hand);
                case null:
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + getType(hand.cards));
            }
        }
        fiveOfAKinds.sort(new strengthComp());
        fourOfAKinds.sort(new strengthComp());
        fullHouses.sort(new strengthComp());
        threeOfAKinds.sort(new strengthComp());
        twoPairs.sort(new strengthComp());
        onePairs.sort(new strengthComp());
        highCards.sort(new strengthComp());

        ArrayList<Hand> finalList = new ArrayList<>();
        finalList.addAll(highCards);
        finalList.addAll(onePairs);
        finalList.addAll(twoPairs);
        finalList.addAll(threeOfAKinds);
        finalList.addAll(fullHouses);
        finalList.addAll(fourOfAKinds);
        finalList.addAll(fiveOfAKinds);
        long result = 0;
        for (Hand hand : finalList) {
            result += (long) (finalList.indexOf(hand) + 1) * hand.bid;
        }
        System.out.println("Result: " + result);
    }

    public static String getType(String hand) {
        String[] chars = hand.split("");
        ArrayList<Integer> cardCount = getIntegers(chars);
        if (isFiveOfAKind(cardCount)) {
            return "fiveOfAKind";
        }
        if (isFourOfAKind(cardCount)) {
            return "fourOfAKind";
        }
        if (isFullHouse(cardCount)) {
            return "fullHouse";
        }
        if (isThreeOfAKind(cardCount)) {
            return "threeOfAKind";
        }
        if (isTwoPair(cardCount)) {
            return "twoPair";
        }
        if (isOnePair(cardCount)) {
            return "onePair";
        }
        if (isHighCard(cardCount)) {
            return "highCard";
        }
        return null;
    }

    private static ArrayList<Integer> getIntegers(String[] chars) {
        ArrayList<Integer> cardCount = new ArrayList<>();
        ArrayList<String> counted = new ArrayList<>();

        //count cards in hand
        for (int i = 0; i < chars.length; i++) {
            int count = 0;
            if (!counted.contains(chars[i])) {
                counted.add(chars[i]);
                for (int x = i; x < chars.length; x++) {
                    if (chars[x].equals(chars[i])) {
                        count++;
                    }
                }
                cardCount.add(count);
            }
        }

        return cardCount;
    }


    public static boolean isFiveOfAKind(ArrayList<Integer> cardCount) {
        return cardCount.size() == 1;
    }

    public static boolean isFourOfAKind(ArrayList<Integer> cardCount) {
        return cardCount.size() == 2 && Collections.max(cardCount) == 4;
    }

    public static boolean isFullHouse(ArrayList<Integer> cardCount) {
        return cardCount.size() == 2 && Collections.max(cardCount) == 3;
    }

    public static boolean isThreeOfAKind(ArrayList<Integer> cardCount) {
        return cardCount.size() == 3 && Collections.max(cardCount) == 3;
    }

    public static boolean isTwoPair(ArrayList<Integer> cardCount) {
        return cardCount.size() == 3 && Collections.max(cardCount) == 2;
    }

    public static boolean isOnePair(ArrayList<Integer> cardCount) {
        return cardCount.size() == 4 && Collections.max(cardCount) == 2;
    }

    public static boolean isHighCard(ArrayList<Integer> cardCount) {
        return cardCount.size() == 5;
    }

    public static void printList(ArrayList<String[]> cardType, String type) {
        System.out.println(type + " " + cardType.size());
        for (String[] card : cardType) {
            System.out.println(card[0] + ", " + card[1]);
        }

    }

    public static class strengthComp implements Comparator<Hand> {

        @Override
        public int compare(Hand o1, Hand o2) {
            ArrayList<Character> strengths = new ArrayList<>();
            strengths.add('A');
            strengths.add('K');
            strengths.add('Q');
            strengths.add('J');
            strengths.add('T');
            strengths.add('9');
            strengths.add('8');
            strengths.add('7');
            strengths.add('6');
            strengths.add('5');
            strengths.add('4');
            strengths.add('3');
            strengths.add('2');
            for (int i = 0; i < o1.cards.length(); i++) {
                int valueOne = strengths.indexOf(o1.cards.charAt(i));
                int valueTwo = strengths.indexOf(o2.cards.charAt(i));
                if(valueOne == valueTwo){
                    continue;
                }
                if (valueOne < valueTwo) {
                    return 1;
                }
                return -1;


            }
            return 0;
        }

        ;
    }
    public record Hand(String cards, int bid) implements Comparable<Hand>{


        @Override
        public int compareTo(Hand o) {

            return 0;
        }
    }


}



