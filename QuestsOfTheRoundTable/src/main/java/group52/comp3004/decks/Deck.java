package group52.comp3004.decks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import group52.comp3004.ResourceManager;
import group52.comp3004.cards.AdventureCard;
import group52.comp3004.cards.Ally;
import group52.comp3004.cards.EventCard;
import group52.comp3004.cards.QuestCard;
import group52.comp3004.cards.StoryCard;
import group52.comp3004.cards.Tourneys;
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
	
	public static ArrayList<AdventureCard> createAdventureDeck(ResourceManager resman) {
			
		ArrayList<AdventureCard> cards = new ArrayList<>(Arrays.asList(
				new Ally("Sir_Galahad", resman, 15, 0),
				new Ally("Sir_Lancelot", resman, 15, 0),
				new Ally("King_Arthur", resman, 10, 0),
				new Ally("Sir_Tristan", resman, 10, 0),
				new Ally("King_Pellinore", resman, 10, 0),
				new Ally("Sir_Gawain", resman, 10, 0),
				new Ally("Sir_Percival", resman, 5, 0),
				new Ally("Queen_Guinevere", resman, 0, 0),
				new Ally("Queen_Iseult", resman, 0, 0),
				new Ally("Merlin", resman, 0, 0)));
		
		for(int i = 0; i < 2; i++) cards.add(new Weapon("Excalibur", resman, 30));
		for(int i = 0; i < 6; i++) cards.add(new Weapon("Lance", resman, 30));
		for(int i = 0; i < 8; i++) cards.add(new Weapon("Battle_Ax", resman, 30));
		for(int i = 0; i < 16; i++) cards.add(new Weapon("Sword", resman, 10));
		for(int i = 0; i < 11; i++) cards.add(new Weapon("Horse", resman, 10));
		for(int i = 0; i < 6; i++) cards.add(new Weapon("Dagger", resman, 5));
		
		return cards;
	}
	
	public static ArrayList<StoryCard> createStoryDeck(ResourceManager resman){
		ArrayList<StoryCard> cards= new ArrayList<>(Arrays.asList(
				//missing event behaviours
				/*new EventCard("Pox", resman, ),
				new EventCard("Plague", resman, ),
				new EventCard("Chivalrous_Deed", resman, ),
				new EventCard("Prosperity", resman, ),
				new EventCard("Call_to_Arms", resman, ),*/
				new QuestCard("Holy_Grail", resman, 5),
				new QuestCard("Green_Knight", resman, 4),
				new QuestCard("Questing_Beast", resman, 4),
				new QuestCard("Queens_Honor", resman, 4),
				new QuestCard("Rescue_Maiden", resman, 3),
				new QuestCard("Enchanted_Forest", resman, 3),
				new QuestCard("Slay_the_Dragon", resman, 3),
				new Tourneys("Camelot", resman, 3), 
				new Tourneys("Orkney", resman, 2),
				new Tourneys("Tintagel", resman, 1),
				new Tourneys("York", resman, 0)));
				
		for(int i = 0; i < 2; i++) cards.add(new QuestCard("Arthurs_Enemy", resman, 3));
		for(int i = 0; i < 2; i++) cards.add(new QuestCard("Boar_Hunt", resman, 2));
		for(int i = 0; i < 2; i++) cards.add(new QuestCard("Repel_Saxon_Raiders", resman, 2));
		//missing event behaviours
		/*for(int i = 0; i < 2; i++) cards.add(new EventCard("King's_Recognition", resman, ));
		for(int i = 0; i < 2; i++) cards.add(new EventCard("Queen's_Favor", resman, ));
		for(int i = 0; i < 2; i++) cards.add(new EventCard("Called_to_Camelot", resman, ));*/
		
		return cards;
	}
}