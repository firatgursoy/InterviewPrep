package blackjack.practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Scanner;

import javax.management.RuntimeErrorException;

enum Suit {
    SPADE,
    CLUB,
    DIAMOND,
    HEART
}

abstract class Card {
    protected Suit suit;
    protected int cardValue;
    protected boolean isAvailable = true;
    
    public Card(Suit suit, int cardValue) {
        this.suit = suit;
        this.cardValue = cardValue;
    }
    
    public abstract int value(); 
    
    public Suit getSuit() {
        return suit;
    }
    
    public int getCardValue() {
        return cardValue;
    }
    
    public boolean isAvailable() {
        return isAvailable;
    }
    
    public void markUnavailable() {
        this.isAvailable = false;
    }
    
    public void print() {
        String[] cards = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        System.out.print(cards[cardValue - 1]);
        switch(this.suit) {
            case SPADE:
                System.out.print("♠");
                break;
            case CLUB:
                System.out.print("♣");
                break;
            case DIAMOND:
                System.out.print("◆");
                break;
            case HEART:
                System.out.print("♥");
                break;
        }
        
        System.out.print(" ");
    }
}

class BlackJackCard extends Card {

    public BlackJackCard(Suit suit, int cardValue) {
        super(suit, cardValue);
    }

    @Override
    public int value() {
        if (isFaceCard()) {
            return 10;
        }
        if (isAce()) {
            return 1;
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
    
    public boolean isFaceCard() {
        return this.cardValue >= 11 && this.cardValue <= 13;
    }
    
    public boolean isAce() {
        return this.cardValue == 1;
    }
}

class Hand<T extends Card> {
    protected List<T> cards = new ArrayList<T>();
    
    public void addToHand(T card) {
        if (card != null)
            cards.add(card);
    }
    
    public int score() {
        return cards.stream().mapToInt(x -> x.getCardValue()).sum();
    }
}

class BlackJackHand extends Hand<BlackJackCard> {
    private String name;
    
    public BlackJackHand(String name) {
        this.name = name;
    }
    
    public int score() {
        List<Integer> possibleScores = getPossibleScores();
        int maxUnder = Integer.MIN_VALUE;
        int minOver = Integer.MAX_VALUE;
        
        for (Integer score : possibleScores) {
            if (score > 21 && score < minOver) {
                minOver = score;
            }
            else if (score <= 21 && score > maxUnder) {
                maxUnder = score;
            }
        }
        
        return maxUnder == Integer.MIN_VALUE ? minOver : maxUnder;
    }
    
    private List<Integer> getPossibleScores() {
        List<Integer> scores = new ArrayList<>();
        if (cards.isEmpty())
            return scores;
        
        for(BlackJackCard card: cards) {
            addToScoreList(card, scores);
        }
        
        return scores;
    }
    
    private void addToScoreList(BlackJackCard card, List<Integer> scores) {
        if (card != null) {
            if (scores.isEmpty())
                scores.add(0);
            
            int length = scores.size();
            for (int i = 0; i < length; i++) {
                int score = scores.get(i);
                
                scores.set(i, score + card.minValue());
                
                if (card.minValue() != card.maxValue()) {
                    scores.add(score + card.maxValue());
                }
            }
        }
    }

    public boolean isBusted() {
        return score() > 21;
    }
    
    public boolean is21() {
        return score() == 21;
    }
    
    public boolean isBlackJack() {
        if (cards.size() != 2) {
            BlackJackCard first = cards.get(0);
            BlackJackCard second = cards.get(1);
            
            if (first.isFaceCard() && second.isAce()) 
                return true;
            if (first.isAce() && second.isFaceCard())
                return true;
        }
        
        return false;
    }
    
    public void print() {
        System.out.print("Player " + this.name + ": ");
        for (BlackJackCard card : cards) {
            card.print();
        }
        System.out.print(" Score: " + score());
    }
    
    public String getName() {
        return name;
    }
}

class Deck<T extends Card> {
    List<T> deck;
    private int deckIndex = 0;
    
    public Deck(List<T> deck) {
        this.deck = deck;
    }
    
    public void shuffle() {
        if (deck != null)
            Collections.shuffle(deck);
    }
    
    public int getRemainCards() {
        return deck.size() - deckIndex;
    }
    
    public T dealCard() {
        if (getRemainCards() == 0) {
            throw new RuntimeErrorException(null, "No more cards available");
        }
        
        T card = null;
        if (deck != null && !deck.isEmpty()) {
            card = deck.get(deckIndex);
            deckIndex++;
            card.markUnavailable();
        }
        
        return card;
    }
}

class BlackJackGame {
    private BlackJackHand[] hands;
    private BlackJackHand dealerHand;
    private Deck<BlackJackCard> deck;
    private Scanner scanner;
    
    public BlackJackGame(int players) {
        hands = new BlackJackHand[players];
        for (int i = 0; i < hands.length; i++) {
            hands[i] = new BlackJackHand(String.valueOf(i));
        }
        dealerHand = new BlackJackHand("Dealer");
        scanner = new Scanner(System.in);
    }

    public void initDeck() {
        List<BlackJackCard> cards = new ArrayList<>();
        for (Suit suit : EnumSet.allOf(Suit.class)) {
            for(int i = 1; i <= 13; i++) {
                cards.add(new BlackJackCard(suit, i));
            }
        }
        deck = new Deck<>(cards);
        deck.shuffle();
    }
    
    public void dealCardToPlayers() {
        for (BlackJackHand hand : hands) {
            hand.addToHand(deck.dealCard());
        }
        
        printHands();
    }
    
    public void dealCardToDealer() {
        dealerHand.addToHand(deck.dealCard());
        
        dealerHand.print();
        System.out.println();
    }
    
    public void playAllHands() {
        for (BlackJackHand hand : hands) {
            if (!hand.is21() || !hand.isBlackJack() || hand.isBusted())
                playHand(hand);
        }
    }
    
    public void playHand(BlackJackHand hand) {
        String input = "";
        
        while(!input.equalsIgnoreCase("S") && !hand.is21() && !hand.isBusted()) {
            System.out.print("Player " + hand.getName() + ": ");
            System.out.print("Hit(H) or Stay(S)");
            
            input = scanner.nextLine();
            
            if (input.equals("H")) {
                hand.addToHand(deck.dealCard());
                hand.print();
            } else {
                hand.print();
                System.out.println();
            }
        }
    }
    
    public void playDealerHand() {
        int score = dealerHand.score();
        
        while(score <= 16) {
            dealerHand.addToHand(deck.dealCard());
            
            score = dealerHand.score();
        }
        
        dealerHand.print();
    }
    
    public List<Integer> getBlackJacks() {
        List<Integer> blackJacks = new ArrayList<>();
        
        for (int i = 0; i < hands.length; i++) {
            BlackJackHand hand = hands[i];
            
            if (hand.isBlackJack())
                blackJacks.add(i);
        }
        
        return blackJacks;
    }
    
    public List<Integer> getWinners() {
        return null;
    }
    
    public void closeGame() {
        scanner.close();
    }
    
    public void printHands() {
        for (BlackJackHand hand : hands) {
            hand.print();
            System.out.println();
        }
    }
}

public class Practice {
    public static void main(String[] args) {
        BlackJackGame game = new BlackJackGame(3);
        game.initDeck();
        
        game.dealCardToPlayers();
        game.dealCardToDealer();
        game.dealCardToPlayers();
        game.dealCardToDealer();
        
        List<Integer> blackJacks = game.getBlackJacks();
        if (!blackJacks.isEmpty())
            System.out.println("black jacks at: " + blackJacks);
        
        //play all hands
        game.playAllHands();
        game.playDealerHand();
        //get winners
        //print winners
        
        //close game
        
    }
}
