package group52.comp3004.game.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import group52.comp3004.Deck.Deck;
import group52.comp3004.Deck.DeckType;
import group52.comp3004.cards.Card;
import group52.comp3004.cards.Weapon;

class DeckTester {
	
	Card excalibur = new Weapon("Excalibur", 30);
	Card horse = new Weapon("Horse", 10);
	Card sword = new Weapon("Sword", 10);
	Card dagger = new Weapon("Dagger", 5);
	Card lance = new Weapon("Lance", 20);
	Card battleax = new Weapon("Battle-ax", 15);

	@Test
	public void createDeck() {
		Deck<Card> aDeck = new Deck<Card>(DeckType.Adventure);
		aDeck.add(excalibur);
		aDeck.add(horse);
		aDeck.add(sword);
		aDeck.add(dagger);
		aDeck.add(lance);
		aDeck.add(battleax);
	}
	
	@Test
	public void drawCard() {
		Deck<Card> aDeck = new Deck<Card>(DeckType.Adventure);
		aDeck.add(excalibur);
		aDeck.add(horse);
		aDeck.add(sword);
		aDeck.add(dagger);
		aDeck.add(lance);
		aDeck.add(battleax);
		ArrayList<Card> fdraw = aDeck.draw(1);
		ArrayList<Card> sdraw = aDeck.draw(3);
		System.out.println("Draw one card: \n");
		for(int i=0;i<fdraw.size();i++){
			System.out.println(fdraw.get(i).getName() + "\n");
		}
		System.out.println("Draw three cards: \n");
		for(int i=0;i<sdraw.size();i++) {
			System.out.println(sdraw.get(i).getName() + "\n");
		}
	}
	
	@Test
	public void discardCard() {
		Deck<Card> aDeck = new Deck<Card>(DeckType.Adventure);
		aDeck.add(excalibur);
		aDeck.add(horse);
		aDeck.add(sword);
		aDeck.add(dagger);
		aDeck.add(lance);
		aDeck.add(battleax);
		Card discarded = aDeck.discard(excalibur);
		assertEquals("Excalibur", (String) discarded.getName());
	}

}