/*eli james
 * project 4
 * cis 200 section 1b
 * date completed: 9/18/23
 * Description: Declare as a constant YOUR last name, another constant with YOUR 9-digit WID# and another constant
with the password CIS200$Fall23. (If your last name is < 4 letters, pad at the end with ‘z’)
- Username will consists of the first 4 letters contained in the constant NAME + last 4 digits of constant
WID. (Assume last name is at least 4 letters).Require the user to enter a username and password (only the password is case sensitive) before they are
allowed to play the game that follows. If the username AND/OR the password is/are invalid, display an
error message and ask the user to re-enter (OK to make your error specific to the error or generic such as
“User and/or Password is/are invalid”).
If invalid 3 times, display another error message (“Invalid Username and/or Password entered 3 times –
Exiting”) and lock out the user from playing the game by exiting the program. Once the username and password have been validated, have the program “deal” a single hand containing
five random playing cards that are unique. Each card will have a suit (Spades, Clubs, Hearts, or Diamonds)
and a value (2-10, Jack, Queen, King, or Ace) display the BEST (highest) classification for your poker hand (1-9). If none of the hands 1-9 are drawn,
then simply display your highest card. (From low to high: 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K, A).
After displaying the results of your hand, ask the user if they wish to ‘Play Again?’ If so, repeat and ‘deal’
another hand of 5 cards, and display the result. Assume the cards are reshuffled and dealt from a new deck.
In other words, duplicates from the previous hand could be dealt.
Continue until the user replies they no longer want to play. (Program should accept ‘Y’ or ‘y’ or ‘N’ or ‘n’
ONLY – so validate and error check user response to “Play Again?”)

 * 
 */

import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
import java.util.HashMap;

public class Proj4 {
    public static void main(String[] args) {
        final String FIRST_LAST_NAME = "James";
        final String WID = "871739708";
        final String PASSWORD = "CIS200$Fall23";


        String LAST_NAME = FIRST_LAST_NAME;

        // Padding
        if (LAST_NAME.length() < 4) {
            LAST_NAME = String.format("%-4s", LAST_NAME).replace(' ', 'z');
        }


        String firstNamePart = LAST_NAME.substring(0, Math.min(LAST_NAME.length(), 4));

        String lastFourDigits = WID.substring(Math.max(0, WID.length() - 4));

        StringBuilder sb = new StringBuilder();
        sb.append(firstNamePart);
        sb.append(lastFourDigits);
        String userName = sb.toString();

        Scanner scanner = new Scanner(System.in);

        int attempts = 0;

        System.out.println("Username generated: " + userName);
        System.out.println();
        System.out.print("Enter username: ");
        String enteredUsername = scanner.nextLine();
        System.out.print("Enter password: ");
        String enteredPassword = scanner.nextLine();
        while (attempts < 2) {
            if (userName.toLowerCase().equals(enteredUsername.toLowerCase()) && PASSWORD.equals(enteredPassword)) {
                System.out.println();
                System.out.println("** Welcome to the 2023 Las Vegas Poker Festival! **");
                System.out.println("\t"+"(Application developed by <Eli James>)");
                break;
            } else {
                System.out.println("User and/or Password is/are invalid");
                System.out.println();
                System.out.print("Please re-enter your username: ");
                enteredUsername = scanner.nextLine();
                System.out.print("Please re-enter your password: ");
                enteredPassword = scanner.nextLine();
                attempts++;
            }
        }

        if (attempts >= 2) {
            System.out.println("Invalid Username and/or Password entered 3 times...EXITING");
            scanner.close();
            System.exit(0);
        }

        boolean playGame = true;

        while (playGame) {
            String[] hand = dealHand();
            System.out.println();
            System.out.println("Shuffling cards...");
            System.out.println("Dealing the cards...");
            System.out.println("Here are your five cards...");
            for (String card : hand) {
                System.out.println("\t" + "\t" + card);
            }
            String handClassification = determineHandClassification(hand);

            if (handClassification.equals("High Card")) {
                String highCard = getHighCard(hand);
                System.out.println();
                System.out.println("High card is a(n) " + highCard);
            } else {
                System.out.println();
                System.out.println("You were dealt a " + handClassification + ".");
            }

            while (true) {
                System.out.print("Play Again? (Y/N): ");
                String playAgain = scanner.nextLine();

                if (playAgain.equalsIgnoreCase("Y")) {
                    break;
                } else if (playAgain.equalsIgnoreCase("N")) {
                    playGame = false;
                    break;
                } else {
                    System.out.println("Please enter 'Y' or 'N' only.");
                }
            }
        }

    }
    // Function to deal a hand of 5 unique cards
    private static String[] dealHand() {
        String[] hand = new String[5];
        Random random = new Random();
        String[] suits = {"Spades", "Clubs", "Hearts", "Diamonds"};
        String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        int cardsDealt = 0;
        boolean[] dealt = new boolean[52];

        while (cardsDealt < 5) {
            int cardIndex = random.nextInt(52);

            if (!dealt[cardIndex]) {
                int suitIndex = cardIndex / 13;
                int valueIndex = cardIndex % 13;

                String suit = suits[suitIndex];
                String value = values[valueIndex];

                hand[cardsDealt] = value + " of " + suit;
                dealt[cardIndex] = true;
                cardsDealt++;
            }
        }

        return hand;
    }

    // Function to determine the hand classification
    private static String determineHandClassification(String[] hand) {
        if (isRoyalFlush(hand)) {
            return "Royal Flush";
        } else if (isStraightFlush(hand)) {
            return "Straight Flush";
        } else if (isFourOfAKind(hand)) {
            return "Four of a Kind";
        } else if (isFullHouse(hand)) {
            return "Full House";
        } else if (isFlush(hand)) {
            return "Flush";
        } else if (isStraight(hand)) {
            return "Straight";
        } else if (isThreeOfAKind(hand)) {
            return "Three of a Kind";
        } else if (isTwoPairs(hand)) {
            return "Two Pairs";
        } else if (isOnePair(hand)) {
            return "One Pair";
        } else {
            return "High Card";
        }
    }

    private static String getHighCard(String[] hand) {
        Arrays.sort(hand, (card1, card2) -> {
            String card1Value = card1.split(" of ")[0];
            String card2Value = card2.split(" of ")[0];
            int card1Rank = getValueRank(card1Value);
            int card2Rank = getValueRank(card2Value);
            return Integer.compare(card1Rank, card2Rank);
        }
        );
        String highCard = hand[hand.length - 1].split(" of ")[0];
        return highCard;
    }

    private static boolean isRoyalFlush(String[] hand) {
        String firstCard = hand[0];
        String firstSuit = firstCard.split(" of ")[1];

        for (String card : hand) {
            String suit = card.split(" of ")[1];
            if (!suit.equals(firstSuit)) {
                return false;
            }

            String value = card.split(" of ")[0];
            if (!(value.equals("10") || value.equals("Jack") || value.equals("Queen") || value.equals("King") || value.equals("Ace"))) {
                return false;
            }
        }

        return true;
    }

    private static boolean isStraightFlush(String[] hand) {
        String firstCard = hand[0];
        String firstSuit = firstCard.split(" of ")[1];

        for (String card : hand) {
            String suit = card.split(" of ")[1];
            if (!suit.equals(firstSuit)) {
                return false;
            }
        }

        // Sort the cards by their values (Ace low)
        Arrays.sort(hand, (card1, card2) -> {
            String card1Value = card1.split(" of ")[0];
            String card2Value = card2.split(" of ")[0];
            int card1Rank = getValueRank(card1Value);
            int card2Rank = getValueRank(card2Value);
            return Integer.compare(card1Rank, card2Rank);
        });

        int prevRank = -1;
        for (String card : hand) {
            String cardValue = card.split(" of ")[0];
            int cardRank = getValueRank(cardValue);

            if (prevRank != -1 && cardRank != prevRank + 1) {
                return false;
            }

            prevRank = cardRank;
        }

        return true;
    }

    private static boolean isFourOfAKind(String[] hand) {
        // Creating a hashmap to count the occurrence of each card value
        HashMap<String, Integer> cardCount = new HashMap<>();

        for (String card : hand) {
            String cardValue = card.split(" of ")[0];
            cardCount.put(cardValue, cardCount.getOrDefault(cardValue, 0) + 1);
            if (cardCount.get(cardValue) == 4) {
                return true;
            }
        }

        return false;
    }

    private static boolean isFullHouse(String[] hand) {
        // Create a hashmap to count the occurrence of each card value
        HashMap<String, Integer> cardCount = new HashMap<>();

        for (String card : hand) {
            String cardValue = card.split(" of ")[0];
            cardCount.put(cardValue, cardCount.getOrDefault(cardValue, 0) + 1);
        }

        boolean hasThreeOfAKind = false;
        boolean hasPair = false;

        for (int count : cardCount.values()) {
            if (count == 3) {
                hasThreeOfAKind = true;
            } else if (count == 2) {
                hasPair = true;
            }
        }

        return hasThreeOfAKind && hasPair;
    }

    private static boolean isFlush(String[] hand) {
        String firstCard = hand[0];
        String firstSuit = firstCard.split(" of ")[1];
        for (String card : hand) {
            String suit = card.split(" of ")[1];
            if (!suit.equals(firstSuit)) {
                return false;
            }
        }

        return true;
    }
    private static int getValueRank(String cardValue) {
        switch (cardValue) {
            case "2":
                return 2;
            case "3":
                return 3;
            case "4":
                return 4;
            case "5":
                return 5;
            case "6":
                return 6;
            case "7":
                return 7;
            case "8":
                return 8;
            case "9":
                return 9;
            case "10":
                return 10;
            case "Jack":
                return 11;
            case "Queen":
                return 12;
            case "King":
                return 13;
            case "Ace":
                return 14;
            default:
                return -1;
        }
    }

    private static boolean isStraight(String[] hand) {
        Arrays.sort(hand, (card1, card2) -> {
            String card1Value = card1.split(" of ")[0];
            String card2Value = card2.split(" of ")[0];
            int card1Rank = getValueRank(card1Value);
            int card2Rank = getValueRank(card2Value);
            return Integer.compare(card1Rank, card2Rank);
        });

        int prevRank = -1;
        for (String card : hand) {
            String cardValue = card.split(" of ")[0];
            int cardRank = getValueRank(cardValue);

            if (prevRank != -1 && cardRank != prevRank + 1) {
                return false;
            }

            prevRank = cardRank;
        }

        return true;
    }

    private static boolean isThreeOfAKind(String[] hand) {
        // Creating a hashmap to count the occurrence of each card value
        HashMap<String, Integer> cardCount = new HashMap<>();

        for (String card : hand) {
            String cardValue = card.split(" of ")[0];
            cardCount.put(cardValue, cardCount.getOrDefault(cardValue, 0) + 1);
            if (cardCount.get(cardValue) == 3) {
                return true;
            }
        }

        return false;
    }

    private static boolean isTwoPairs(String[] hand) {
        // Creating a hashmap to count the occurrence of each card value
        HashMap<String, Integer> cardCount = new HashMap<>();

        for (String card : hand) {
            String cardValue = card.split(" of ")[0];

            cardCount.put(cardValue, cardCount.getOrDefault(cardValue, 0) + 1);
        }

        int pairCount = 0;

        for (int count : cardCount.values()) {
            if (count == 2) {
                pairCount++;
            }
        }

        return pairCount == 2;
    }

    private static boolean isOnePair(String[] hand) {
        // Creating a hashmap to count the occurrence of each card value
        HashMap<String, Integer> cardCount = new HashMap<>();

        for (String card : hand) {
            String cardValue = card.split(" of ")[0];
            cardCount.put(cardValue, cardCount.getOrDefault(cardValue, 0) + 1);

            if (cardCount.get(cardValue) == 2) {
                return true;
            }
        }

        return false;
    }


}



