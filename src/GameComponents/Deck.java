package GameComponents;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {

	ArrayList<Card> listOfCards;

	private final int MAX_JOKERS_PER_PACK = 2;

	public Deck(int numberOfPacks, boolean hasJoker) {

		int jokerCount;
		listOfCards = new ArrayList<>();
		
		if (hasJoker)
			jokerCount = MAX_JOKERS_PER_PACK;
		else
			jokerCount = 0;
		
		for (int i = 0; i < numberOfPacks; ++i) {
			Pack p = new Pack(jokerCount);
			listOfCards.addAll(p.getCardsFromPack());
		}

	}
	
	public Deck(ArrayList<Card> list){
		listOfCards = new ArrayList<>();
		listOfCards.addAll(list);
	}

	public void shuffle() {
		Collections.shuffle(listOfCards);
	}

	public Card removeCardOnTop() {
		Card c = listOfCards.get(0);
		listOfCards.remove(c);
		return c;
	}
}
