package group52.comp3004.decks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import group52.comp3004.cards.AdventureCard;
import group52.comp3004.cards.Ally;
import group52.comp3004.cards.Weapon;

public class Deck<T> {
	
	List<T> cards;
	int size;
	
	public Deck() {
		this.cards = null;
		size = 0;
	}
	
	public Deck(List<T> cards) {
		setCards(cards);
	}
	
	public void setCards(List<T> cards) {
		this.cards = cards;
		size = cards.size();
	}
	
	public int getSize() {
		return size;
	}
	
	public void addCard(T card) {
		this.cards.add(card);
	}
	
	
	public T drawCard() {
		T card = null;
		Random rand = new Random();
		while(card == null && size > 0) {
			int index = rand.nextInt(cards.size());
			card = cards.get(index);
			cards.set(index, null);
		}
		
		size -= 1;
		
		return card;
	}
	
	public static ArrayList<AdventureCard> createAdventureDeck() {
			
		ArrayList<AdventureCard> cards = new ArrayList<>(Arrays.asList(
				new Ally("Sir Galahad", 15, 0),
				new Ally("Sir Lancelot", 15, 0),
				new Ally("King Arthut", 10, 0),
				new Ally("Sir Tristan", 10, 0),
				new Ally("Sir Pellinore", 10, 0),
				new Ally("Sir Gawain",10, 0),
				new Ally("Sir Percival", 5, 0),
				new Ally("Queen Guinever", 0, 0),
				new Ally("Queen Iseult",0, 0),
				new Ally("Merlin",0, 0)));
		
		for(int i = 0; i < 2; i++) cards.add(new Weapon("Excalibur", 30));
		for(int i = 0; i < 6; i++) cards.add(new Weapon("Lance", 30));
		for(int i = 0; i < 8; i++) cards.add(new Weapon("Battle Ax", 30));
		for(int i = 0; i < 16; i++) cards.add(new Weapon("Sword", 10));
		for(int i = 0; i < 11; i++) cards.add(new Weapon("Horse", 10));
		for(int i = 0; i < 6; i++) cards.add(new Weapon("Dagger", 5));
		
		return cards;
	}
}