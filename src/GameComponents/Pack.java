package GameComponents;
import java.util.*;

public class Pack {
	
	ArrayList<Card> cardsInPack;
	private final String[] suits  = {"SPADE","DIAMOND","CLUB","HEART"};
	private final String[] values = {"A","2","3","4","5","6","7","8","9","T","J","Q","K"};
	
	public Pack(int jokerCount){
		
		cardsInPack = new ArrayList<>();
		
		for(String s:suits){
			for(String v:values){
				cardsInPack.add(new Card(v,s));
			}
		}	
		
		for(int i=0; i < jokerCount; ++i){
			cardsInPack.add(new Card("0","*"));
		}
	}

	public ArrayList<Card> getCardsFromPack(){
		return cardsInPack;
	}

	
}
