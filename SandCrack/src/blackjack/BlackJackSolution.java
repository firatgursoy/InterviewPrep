package blackjack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * enum Suit

 * abstract Card: 
 *  has a Suite
 *  has a int cardValue
 *  has an availability
 * 
 * BlackJackCard child of Card
 * 
 * Hand: list of cards
 *  has a score
 * 
 * Deck: list of cards
 *  + shuffle
 *  + int getRemainingCards
 *  + Card dealCard
 *  + Card[] dealHand
 * 
 */

enum Suit {
    CLUB(0),
    DIAMOND(1),
    HEART(2),
    SPADE(3);
    
    private int value;
    
    Suit(int value) {
        this.value = value;
    }
    
    int getValue() {
        return value;
    }
    
    public static Suit getSuitFromValue(int value) {
        switch (value) {
        case 0:
            return Suit.CLUB;
        case 1:
            return Suit.DIAMOND;
        case 2:
            return Suit.HEART;
        case 3: 
            return Suit.SPADE;
        default:
                return null;
        }
    }
}

abstract class Card {
    private boolean isAvailable = true;
    
    protected Suit suit;
    protected int cardValue;
    
    public Card(Suit suit, int cardValue) {
        this.suit = suit;
        this.cardValue = cardValue;
    }
    
    public abstract int value();
    
    public boolean isAvailable() {
        return this.isAvailable;
    }
    
    public Suit getSuit() {
        return suit;
    }
    
    public int getCardValue() {
        return this.cardValue;
    }
    
    public void markUnavailable() {
        this.isAvailable = false;
    }
    
    public void markAvailable() {
        this.isAvailable = true;
    }
    
    public void print() {
        String[] faceValues = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        System.out.print(faceValues[cardValue - 1]);
        switch (suit) {
        case CLUB:
            System.out.print("♣");
            break;
        case HEART:
            System.out.print("♥");
            break;
        case DIAMOND:
            System.out.print("♦");
            break;
        case SPADE:
            System.out.print("♠");
            break;          
        }
        System.out.print(" ");
    }
}

class Deck<T extends Card> {
    private List<T> cards;
    private int deckIndex;
    
    public Deck(List<T> cards) {
        this.cards = cards;
        this.deckIndex = 0;
    }
    
    public void shuffle() {
        Collections.shuffle(this.cards);
    }
    
    public int getRemainingCards() {
        return cards.size() - deckIndex;
    }
    
    public T dealCard() {
        if (getRemainingCards() == 0) {
            return null;
        }
        
        T card = (T)cards.get(deckIndex);
        card.markUnavailable();
        this.deckIndex++;
        return card;
    }
}

class Hand<T extends Card> {
    protected List<T> cards = new ArrayList<>();
    
    public int score() {
        return cards.stream().mapToInt(x -> x.getCardValue()).sum();
    }
    
    public void addCard(T card) {
        if (card != null) {
            this.cards.add(card);
        }
    }
}

class BlackJackHand extends Hand<BlackJackCard> {
    private String handName;
    
    public BlackJackHand(String name) {
        this.handName = name;
    }
    
    public int score() {
        ArrayList<Integer> scores = possibleScores();
        int maxUnder = Integer.MIN_VALUE;
        int minOver = Integer.MAX_VALUE;
        for (int score : scores) {
            if (score > 21 && score < minOver) {
                minOver = score;
            } else if (score <= 21 && score > maxUnder) {
                maxUnder = score;
            }
        }
        return maxUnder == Integer.MIN_VALUE ? minOver : maxUnder;
    }
    
    private ArrayList<Integer> possibleScores() {
        ArrayList<Integer> scores = new ArrayList<Integer>();
        if (cards.size() == 0) {
            return scores;
        }
        for (BlackJackCard card : cards) {
            addCardToScoreList(card, scores);
        }
        return scores;
    }
    
    private void addCardToScoreList(BlackJackCard card, ArrayList<Integer> scores) {
        if (scores.size() == 0) {
            scores.add(0);
        } 
        int length = scores.size();
        for (int i = 0; i < length; i++) {
            int score = scores.get(i);
            scores.set(i, score + card.minValue());
            if (card.minValue() != card.maxValue()) {
                scores.add(score + card.maxValue());
            }
        }
    }
    
    public boolean is21() {
        return score() == 21;
    }
    
    public boolean isBlackJack() {
        int cards = this.cards.size();
        
        if (cards != 2) {
            return false;
        }
        
        BlackJackCard first = this.cards.get(0);
        BlackJackCard second = this.cards.get(1);
        
        if (first.isAce() && second.isFaceCard()) {
            return true;
        } else if (first.isFaceCard() && second.isAce()) {
            return true;
        }
        return false;
    }
    
    public boolean isBusted() {
        return score() > 21;
    }
    
    public void print() {
        System.out.print("Hand " + this.getName() + ": ");
        for (Card card : cards) {
            card.print();
        }
        System.out.print(" score:" + score());
        System.out.println();
    }
    
    public String getName() {
        return this.handName;
    }
}

class BlackJackCard extends Card {

    public BlackJackCard(int cardValue, Suit suit) {
        super(suit, cardValue);
    }

    @Override
    public int value() {
        if (isAce()) {
            return 1;
        }
        if (isFaceCard()) {
            return 10;
        }
        return this.cardValue;
    }

    public int minValue() {
        if (isAce()) {
            return 1;
        }
        return value();
    }
    
    public int maxValue() {
        if (isAce()) {
            return 11;
        }
        return value();
    }
    
    public boolean isAce() {
        return this.cardValue == 1;
    }
    
    // cards: 2, 3, 4, 5, 6, 7, 8, 10, J-11, Q-12, K-13
    public boolean isFaceCard() {
        return this.cardValue >= 11 && this.cardValue <= 13;
    }
}

class BlackJackGame {
    private Deck<BlackJackCard> deck;
    private BlackJackHand[] hands;
    private BlackJackHand dealerHand;
    private static final int HIT_UNTIL = 16;
    private Scanner scanner;
    
    public BlackJackGame(int numPlayers) {
        hands = new BlackJackHand[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            hands[i] = new BlackJackHand(String.valueOf(i));
        }
        dealerHand = new BlackJackHand("Dealer");
        scanner = new Scanner(System.in);
    }
    
    public boolean dealInitialToPlayers() {
        for (BlackJackHand hand : hands) {
            BlackJackCard card1 = deck.dealCard();
            if (card1 == null) {
                return false;
            }
            hand.addCard(card1);
        }
        return true;
    }

    public boolean dealInitialToDealer() {
        BlackJackCard card = deck.dealCard();
        if (card == null) {
            return false;
        }
        dealerHand.addCard(card);
        return true;
    }
    
    public ArrayList<Integer> getBlackJacks() {
        ArrayList<Integer> winners = new ArrayList<Integer>();
        for (int i = 0; i < hands.length; i++) {
            if (hands[i].isBlackJack()) {
                winners.add(i);
            }
        }
        return winners;
    }
    
    public boolean dealCard(int i) {
        BlackJackHand hand = hands[i];
        BlackJackCard card = deck.dealCard();
        if (card == null) {
            return false;
        }
        hand.addCard(card);
        return true;
    }
    
    public boolean playHand(int i) {
        BlackJackHand hand = hands[i];
        return playHand(hand);
    }
    
    public boolean playHand(BlackJackHand hand) {
        String input = "";
        
        while( !input.equals("S") && !hand.is21() && !hand.isBusted()) {
            System.out.println("Hand " + hand.getName() + ": Hit(H) or Stay(S)");

            input = scanner.nextLine();
            
            if (input.equalsIgnoreCase("H")) {
                BlackJackCard card = deck.dealCard();
                if (card == null) {
                    scanner.close();
                    return false;
                }
                hand.addCard(card);
                hand.print();
            } else {
                hand.print();
            }
        }

        return true;
    }   
    
    public boolean playAllHands() {
        for (BlackJackHand hand : hands) {
            if (!hand.is21() && !hand.isBusted()) {
                if (!playHand(hand)) {
                    return false;
                }
            }
        }
        
        
        return true;
    }
    
    public boolean playDealerHand() {
        while (dealerHand.score() < HIT_UNTIL) {
            BlackJackCard card = deck.dealCard();
            if (card == null) {
                return false;
            }
            dealerHand.addCard(card);
            dealerHand.print();
        }
        return true;
    }
    
    public void closeScanner() {
        scanner.close();
    }
    
    public List<String> getWinners() {
        List<String> winners = new ArrayList<>();
        if (dealerHand.isBusted()) {
            winners = Arrays.stream(hands)
                            .filter(x -> !x.isBusted())
                            .map(BlackJackHand::getName)
                            .collect(Collectors.toList());
        } else {
            int winningScore = dealerHand.score();
            winners = Arrays.stream(hands)
                            .filter(x -> !x.isBusted())
                            .filter(x -> x.score() >= winningScore)
                            .map(BlackJackHand::getName)
                            .collect(Collectors.toList());
        }
        return winners;
    }
    
    public void initializeDeck() {
        ArrayList<BlackJackCard> cards = new ArrayList<BlackJackCard>();
        for (int i = 1; i <= 13; i++) {
            for(Suit suit: EnumSet.allOf(Suit.class)) {
                BlackJackCard card = new BlackJackCard(i, suit);
                cards.add(card);
            }
        }
        
        deck = new Deck<BlackJackCard>(cards);
        deck.shuffle(); 
    }
    
    public void printHandsAndScore() {
        for(BlackJackHand hand: hands) {
            hand.print();
            System.out.println("");
        }
        dealerHand.print();
    }
}

public class BlackJackSolution {

    public static void main(String[] args) {
        int[] numHands = {0, 1, 2};
        
        BlackJackGame game = new BlackJackGame(numHands.length);
        game.initializeDeck();
        
        game.dealInitialToPlayers();
        game.dealInitialToDealer();
        game.dealInitialToPlayers();
        game.dealInitialToDealer();
        
        game.printHandsAndScore();
        
        ArrayList<Integer> blackjacks = game.getBlackJacks();
        if (!blackjacks.isEmpty()) {
            System.out.println();
            System.out.print("Blackjack at ");
            for (int i : blackjacks) {
                System.out.print(i + ", ");
            }
            System.out.println("");
        } 

        System.out.println();
        game.playAllHands();
        game.playDealerHand();
        
        List<String> winners = game.getWinners();
        if(!winners.isEmpty()) {
            System.out.println();
            System.out.print("Winner(s) at " + String.join(",", winners));
            System.out.println("");
        }
        
        game.closeScanner();
    }
}
