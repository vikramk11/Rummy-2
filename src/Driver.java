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
			
//			hand.addCard(new Card("2","CLUB"));
//			hand.addCard(new Card("3","CLUB"));
//			hand.addCard(new Card("4","DIAMOND"));
//			hand.addCard(new Card("5","DIAMOND"));
//			hand.addCard(new Card("0","*"));
//			hand.addCard(new Card("4","HEARTS"));
//			hand.addCard(new Card("5","HEARTS"));


			System.out.println("Joker Card: "+joker);
			System.out.println("Cards Needed:"+hand.evaluate());
			
			
	}

}
