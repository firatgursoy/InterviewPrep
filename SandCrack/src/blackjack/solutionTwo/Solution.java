package blackjack.solutionTwo;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Suit: club, spade, heart, diamon
 * abstract Card: suit, cardValue, isAvailable
 *      + value
 * class Hand: list of cards
 *      + int score ?
 * Deck: list of cards
 *      + shuffle
 *      + deaHhand
 *      + dealCard
 * 
 * BlackJackCard
 *      + isFaceCard()
 *      + isAce()
 * 
 * BlackJackHand
 *      + int score()
 *      + isBusted()
 *      + is21()
 *      + isBlackJack()
 */

enum Suit {
    CLUB(0),
    SPADE(1),
    HEART(2),
    DIAMOND(3);
    
    private int value;
    
    Suit(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
}

abstract class Card {
    private boolean isAvailable;
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
    
    public void markUnavailable() {
        this.isAvailable = false;
    }
    
    public void markAvailable() {
        this.isAvailable = true;
    }
}

class Deck<T extends Card> {
    private int dealtIndex;
    private List<T> cards;
    
    public Deck(List<T> cards) {
        this.cards = cards;
        this.dealtIndex = 0;
    }
    
    public int availableCards() {
        return this.cards.size() - this.dealtIndex;
    }
    
    public void shuffle() {
        Collections.shuffle(this.cards);
    }
    
    public T dealCard() {
        if (this.availableCards() == 0) {
            return null;
        }
        
        T card = this.cards.get(this.dealtIndex);
        card.markUnavailable();
        this.dealtIndex++;
        return card;
    }
    
    public T[] dealHard(int number) {
        if (number > availableCards()) {
            return null;
        }
        
        T[] hand = (T[])new Card[number];
        int count = 0;
        IntStream.range(0, number - 1).forEach(x -> {
            T card = dealCard();
            if (card != null) {
                hand[x] = card;
            }
        });;
        
        return hand;
    }
}


public class Solution {

}
