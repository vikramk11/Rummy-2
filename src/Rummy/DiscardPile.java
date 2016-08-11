package Rummy;

import java.util.ArrayList;

import GameComponents.*;


public class DiscardPile {
	ArrayList<Card> cardsInBoard;
		
	public void addCard(Card card) {
		cardsInBoard.add(card);
	}
	
	public Card removeCard() {
		Card c = cardsInBoard.get(cardsInBoard.size() - 1);
		return c;
	}
	
	public Deck convertToDeck() {
		Deck deck = new Deck(cardsInBoard);
		deck.shuffle();
		return deck;
	}
}
