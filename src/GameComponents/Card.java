package GameComponents;

import java.lang.Comparable;

public class Card implements Comparable {
	int value;
	private String displayValue;
	String suit;
	String SUIT_NAMES = "SPADE HEART DIAMOND CLUB";
	String FACE_NAMES = "AKQJT98765432";
	public Card(int value, String displayValue, String suit) {
		this.value = value;
		this.displayValue = displayValue;
		this.suit = suit;
	}

	public Card(int value, String suit) {
		this.value = value;
		this.suit = suit;
		this.displayValue = convertToDisplayValue(value);
	}

	public Card(String displayValue, String suit) {
		this.displayValue = displayValue;
		this.suit = suit;
		this.value = convertToValue(displayValue);
	}

	public int convertToValue(String displayValue) {
		switch (displayValue) {
		case "A":
			return 1;
		case "T":
			return 10;
		case "J":
			return 11;
		case "Q":
			return 12;
		case "K":
			return 13;
		case "0":
			return 0;
		default:
			return Integer.parseInt(displayValue);
		}
	}

	public String convertToDisplayValue(int value) {
		switch (value) {
		case 1:
			return "A";
		case 10:
			return "T";
		case 11:
			return "J";
		case 12:
			return "Q";
		case 13:
			return "K";
		default:
			return String.valueOf(value);
		}
	}

	public int getValue() {
		return this.value;
	}

	public String getSuit() {
		return this.suit;
	}

	public String getDisplayValue() {
		return this.displayValue;
	}

	public boolean isSameSuit(Card c2) {
		return c2.suit.equals(this.suit);
	}

	public boolean isSameValue(Card c2) {
		return c2.value == this.value;
	}

	public boolean isNextInSeq(Card c2) {
		if (isSameSuit(c2)) {
			if (this.value == 13) {
				return c2.value == 1;
			} else {
				return c2.value == this.value + 1;
			}
		}

		return false;
	}

	public boolean isPrevInSeq(Card c2) {
		if (isSameSuit(c2)) {
			if (this.value == 1) {
				return c2.value == 13;
			} else {
				return c2.value == this.value - 1;
			}
		}

		return false;
	}

	public int compare(Card c2) {
		return c2.value - this.value;
	}

	public String toString() {
		return this.suit + " " + this.displayValue;
	}

	public boolean equals(Card c){
		if(this.isSameSuit(c)&&this.isSameValue(c))
			return true;
		return false;
	}
	
	@Override
	public int hashCode(){
		int card = (SUIT_NAMES.indexOf(this.suit) * FACE_NAMES.length()) + FACE_NAMES.indexOf(this.displayValue);
		return card;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		Card c = (Card) o;
		if (!isSameSuit(c)) {
			return this.suit.compareTo(c.suit);
		} else {
			return compare(c);
		}
	}
}
