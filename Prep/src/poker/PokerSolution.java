package poker;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

enum Suit {
    SPADE,
    CLUB,
    DIAMOND,
    HEART
}

class Card {
    private int cardValue;
    private Suit suit;
    
    public Card(Suit suit, int cardValue) {
        this.suit = suit;
        this.cardValue = cardValue;
    }

    public Integer getCardValue() {
        return cardValue;
    }

    public Suit getSuit() {
        return suit;
    }
    
    @Override
    public String toString() {
        return "(" + suit.toString() + "," + cardValue + ")";
    }
}

class PokerHand {
    private Set<Card> cards = new HashSet<>();
    
    public void addToHand(Card card) {
        if (card != null) {
            this.cards.add(card);
        }
    }
    
    public boolean isFourOfKind() {
        Map<Integer, Long> groupByCardValue = cards.stream()
                                                    .collect(Collectors.groupingBy(Card::getCardValue, 
                                                                    Collectors.counting()));
        
        return groupByCardValue.entrySet().stream().anyMatch(x -> x.getValue() == 4);
    }
    
    public boolean isFlush() {
        Map<Suit, Long> groupBySuit = cards.stream()
                                            .collect(Collectors.groupingBy(Card::getSuit, 
                                                            Collectors.counting()));
        
        return groupBySuit.entrySet().stream().anyMatch(x -> x.getValue() == 5);
    }
    
    /**
     * Just get all cards, sort and see if each card are each one value apart.
     * 
     */
    public boolean isStraight() {
        List<Card> sortedItems = cards.stream()
                                        .sorted((c1, c2) -> Integer.compare(c1.getCardValue(), c2.getCardValue()))
                                        .collect(Collectors.toList());
        
        System.out.println(sortedItems);
        
        return false;
    }
     
    public boolean isStraightFlush() {
        return isStraight() && isFlush();
    }
}

public class PokerSolution {
    public static void main(String[] args) {
        PokerHand hand = new PokerHand();
        hand.addToHand(new Card(Suit.HEART, 10));
        hand.addToHand(new Card(Suit.HEART, 2));
        hand.addToHand(new Card(Suit.HEART, 8));
        hand.addToHand(new Card(Suit.HEART, 7));
        hand.addToHand(new Card(Suit.HEART, 6));
        
        //System.out.println(hand.isFourOfKind());
        //System.out.println(hand.isStraight());
        
        hand.isStraight();
    }
}
