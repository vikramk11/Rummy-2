package GameComponents;
import java.util.ArrayList;

public abstract class Hand {
	protected ArrayList<Card> cardsInHand;

	public void addCard(Card c) {
		cardsInHand.add(c);
	}

	public Card removeCard(Card c) {
		cardsInHand.remove(c);
		return c;
	}
	
	public String toString() {
		return cardsInHand.toString();
	}
}
