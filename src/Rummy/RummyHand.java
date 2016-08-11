package Rummy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import GameComponents.*;

public class RummyHand extends Hand {

	String[] possibleWinCombinations = { "4333", "553", "544" };
	String possibleSequence = "AKQJT98765432A";
	String sequenceWithHole = "111*1 -  11*11 - 1*111 - 11*1 - 1*11 - 1*1";

	final String sequenceOf2 = "SEQ2";
	final String sequenceOf3 = "SEQ3";
	final String sequenceOf4 = "SEQ4";
	final String sequenceOf5 = "SEQ5";

	final String sequenceOf3WithHole = "SEQH3";
	final String sequenceOf4WithHole = "SEQH4";
	final String sequenceOf5WithHole = "SEQH5";

	final String setOf2 = "SET2";
	final String setOf3 = "SET3";
	final String setOf4 = "SET4";

	final String keysForFour = "SEQ4 SET4 SEQH4";
	final String keysForThree = "SEQ3 SET3 SEQ2 SEQ3H SET2";
	
	final String toAddOne = "SEQH4 SEQ2 SEQH3 SET2";

	Card joker;
	int availableJokers = 0;
	int MIN_SEQUENCES_TO_WIN = 2;
	int MIN_TRUE_SEQUENCES = 1;

	HashMap<String, String> sequenceInSuit;
	HashMap<Character, Integer> setMap;
	TreeMap<String, Integer> setAndSeq;

	public RummyHand() {
		cardsInHand = new ArrayList<>();
		sequenceInSuit = new HashMap<>();
		setMap = new HashMap<>();
		setAndSeq = new TreeMap<>();
	}

	public RummyHand(Card j) {
		cardsInHand = new ArrayList<>();
		sequenceInSuit = new HashMap<>();
		setMap = new HashMap<>();
		setAndSeq = new TreeMap<>();
		joker = j;
	}

	public void sortCards() {
		Collections.sort(cardsInHand);
	}

	public void convertToSequenceMap() {
		String sequence;
		String initial = "**************";
		for (Card c : cardsInHand) {
			String suit = c.getSuit();
			String value = c.getDisplayValue();
			if (suit == "*" || c.equals(joker))
				this.availableJokers++;
			else if (!sequenceInSuit.containsKey(suit)) {
				sequence = addToSequence(initial, value);
				sequenceInSuit.put(suit, sequence);
			} else {
				sequence = sequenceInSuit.get(suit);
				sequence = addToSequence(sequence, value);
				sequenceInSuit.put(suit, sequence);
			}
		}
	}

	public void convertToSetMap() {
		for (String key : sequenceInSuit.keySet()) {
			
			String value = sequenceInSuit.get(key);
			value = value.substring(0, value.length()-2);

			char[] cards = value.toCharArray();

			for (char c : cards) {
				if (c != '*') {
					if (setMap.containsKey(c)) {
						int count = setMap.get(c);
						setMap.put(c, count + 1);
					} else {
						setMap.put(c, 1);
					}
				}
			}

		}
	}

	public String addToSequence(String s, String v) {

		if (v.equals("A")) {
			s = v + s.substring(1, s.length() - 1) + v;
			return s;
		} else {
			int index = possibleSequence.indexOf(v);
			StringBuilder pattern = new StringBuilder(s);
			pattern.setCharAt(index, v.toCharArray()[0]);
			return pattern.toString();
		}

	}

	public Set<String> subString(String s, String t) {
		int[][] table = new int[s.length()][t.length()];
		int longest = 0;
		Set<String> result = new HashSet<>();

		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < t.length(); j++) {
				if (s.charAt(i) != t.charAt(j)) {
					continue;
				}

				table[i][j] = (i == 0 || j == 0) ? 1 : 1 + table[i - 1][j - 1];
				if (table[i][j] > longest) {
					longest = table[i][j];
					result.clear();
				}
				if (table[i][j] == longest) {
					result.add(s.substring(i - longest + 1, i + 1));
				}
			}
		}
		return result;
	}

	public void getPossibleSeqLengthWithoutHoles() {
		for (String suit : sequenceInSuit.keySet()) {
			String cardsInSuit = sequenceInSuit.get(suit);
			Set<String> allLCS = subString(possibleSequence, cardsInSuit);
			for (String longestSequence : allLCS) {
				System.out.println(longestSequence);
				int length = longestSequence.length();
				String key = "extras";
				if (length == 2)
					key = sequenceOf2;
				if (length == 3)
					key = sequenceOf3;
				if (length == 4)
					key = sequenceOf4;
				if (length == 5)
					key = sequenceOf5;
				if (setAndSeq.containsKey(key)) {
					int count = setAndSeq.get(key);
					setAndSeq.put(key, count + 1);
				} else {
					setAndSeq.put(key, 1);
				}
			}
		}
	}

	public void getPossibleSeqLengthWithHoles() {
		for (String suit : sequenceInSuit.keySet()) {
			String cardsInSuit = occurencePattern(sequenceInSuit.get(suit));
			Set<String> allLCS = subString(this.sequenceWithHole, cardsInSuit);
			for (String longestSequence : allLCS) {
				System.out.println(suit + " " + longestSequence);
				int length = longestSequence.length();
				String key = "extras";
				if (length == 3 && longestSequence.equals("1*1"))
					key = sequenceOf3WithHole;
				if (length == 4)
					key = sequenceOf4WithHole;
				if (length == 5)
					key = sequenceOf5WithHole;
				if (setAndSeq.containsKey(key)) {
					int count = setAndSeq.get(key);
					setAndSeq.put(key, count + 1);
				} else {
					setAndSeq.put(key, 1);
				}
			}
		}

	}

	public String occurencePattern(String s) {
		String result = "";
		for (char c : s.toCharArray()) {
			if (c != '*')
				result += '1';
			else
				result += '*';
		}
		return result;
	}

	public void getPossibleSetLength() {
		for (Character suit : setMap.keySet()) {
			int count = setMap.get(suit);
			String key = "Singles";
			if (count == 2)
				key = setOf2;
			if (count == 3)
				key = setOf3;
			if (count == 4)
				key = setOf4;
			if (setAndSeq.containsKey(key)) {
				int value = setAndSeq.get(key);
				setAndSeq.put(key, value + 1);
			} else {
				setAndSeq.put(key, 1);
			}
		}
	}

	public void checkForOverLaps() {
		for (String key : sequenceInSuit.keySet()) {
			String cardList = sequenceInSuit.get(key);
			for (char k : setMap.keySet()) {
				int count = setMap.get(k);
				if (count > 1) {
					int index = cardList.indexOf(k);
					if (index != -1)
						if (!isSoloCard(index, cardList))
							setMap.put(k, count - 1);
				}
			}
		}
	}

	public boolean isSoloCard(int index, String cards) {
		int lastIndex = cards.length() - 1;
		if (index == 0)
			return cards.charAt(1) == '*';
		else if (index == lastIndex)
			return cards.charAt(lastIndex - 1) == '*';
		else
			return cards.charAt(index - 1) == '*' && cards.charAt(index + 1) == '*';
	}

	public int evaluateFor43() {
		int CARD_GROUPS = 7;
		int cardsNeeded = 0;
		int value;

		for (String s : keysForFour.split(" ")) {
			if(setAndSeq.containsKey(s)){
				value = setAndSeq.get(s);
				if(value>0 && CARD_GROUPS>3){
					CARD_GROUPS-=4;
					if(toAddOne.contains(s)){
						cardsNeeded++;
						System.out.println(s);
					}
				}
			}
		}
		
		
		for (String s : keysForThree.split(" ")) {
			if(setAndSeq.containsKey(s)){
				value = setAndSeq.get(s);
				if(value>0 && CARD_GROUPS>0){
					CARD_GROUPS-=3*value;
					if(toAddOne.contains(s)){
						System.out.println(s+" "+cardsNeeded);
						cardsNeeded+=1*value;
					}
				}
			}
		}
		
		if(CARD_GROUPS==4||CARD_GROUPS==3)
			return cardsNeeded+CARD_GROUPS-1;
		
		return cardsNeeded+CARD_GROUPS;
	}

	public int evaluate() {
		this.sortCards();
		System.out.println("All:" + cardsInHand);

		convertToSequenceMap();
		System.out.println(sequenceInSuit);

		convertToSetMap();
		System.out.println("before overlap:" + setMap);

		checkForOverLaps();
		System.out.println("After overlap:" + setMap);

		getPossibleSeqLengthWithoutHoles();
		getPossibleSeqLengthWithHoles();
		getPossibleSetLength();

		System.out.println(setAndSeq);
		System.out.println("Joker Count: " + availableJokers);

		return evaluateFor43()-availableJokers;
	}
}
