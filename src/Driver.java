import GameComponents.*;
import Rummy.*;

public class Driver {

	public static void main(String[] args) {

			Card joker;
			Deck deck = new Deck(1, true);
			deck.shuffle();
			joker=deck.removeCardOnTop();
			RummyHand hand = new RummyHand(joker);

			for(int i=0;i<7;i++){
				hand.addCard(deck.removeCardOnTop());
			}	
//			hand.addCard(new Card("A","SPADE"));
//			hand.addCard(new Card("K","SPADE"));
//			hand.addCard(new Card("Q","SPADE"));
//			hand.addCard(new Card("J","SPADE"));
//			hand.addCard(new Card("T","HEART"));
//			hand.addCard(new Card("T","DIAMOND"));
//			hand.addCard(new Card("5","SPADE"));
//			hand.addCard(new Card("6","CLUB"));
//			hand.addCard(new Card("6","HEART"));
//			hand.addCard(new Card("6","DIAMOND"));
//			hand.addCard(new Card("7","SPADE"));
//			hand.addCard(new Card("7","DIAMOND"));
//			hand.addCard(new Card("Q","SPADE"));	

			System.out.println("Joker Card: "+joker);
			System.out.println("Cards Needed:"+hand.evaluate());
			
			
	}

}
