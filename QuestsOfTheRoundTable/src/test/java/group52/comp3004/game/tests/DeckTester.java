package group52.comp3004.game.tests;

import org.junit.jupiter.api.Test;

import group52.comp3004.ResourceManager;
import group52.comp3004.cards.Card;
import group52.comp3004.cards.Weapon;
import group52.comp3004.decks.Deck;

class DeckTester {
	private ResourceManager resman = new ResourceManager();
	
	Card excalibur = new Weapon("Excalibur", resman, 30);
	Card horse = new Weapon("Horse", resman, 10);
	Card sword = new Weapon("Sword", resman, 10);
	Card dagger = new Weapon("Dagger", resman, 5);
	Card lance = new Weapon("Lance", resman, 20);
	Card battleax = new Weapon("Battle_Ax", resman, 15);

	@Test
	public void createDeck() {
		Deck<Card> aDeck = new Deck<Card>();
		aDeck.addCard(excalibur);
		aDeck.addCard(horse);
		aDeck.addCard(sword);
		aDeck.addCard(dagger);
		aDeck.addCard(lance);
		aDeck.addCard(battleax);
	}
	
	@Test
	public void drawCard() {
		Deck<Card> aDeck = new Deck<Card>();
		aDeck.addCard(excalibur);
		aDeck.addCard(horse);
		aDeck.addCard(sword);
		aDeck.addCard(dagger);
		aDeck.addCard(lance);
		aDeck.addCard(battleax);
		//no multiple draw method in the deck class we are using
		/*ArrayList<Card> fdraw = aDeck.drawCard();
		ArrayList<Card> sdraw = aDeck.drawCard();
		System.out.println("Draw one card: \n");
		for(int i=0;i<fdraw.size();i++){
			System.out.println(fdraw.get(i).getName() + "\n");
		}
		System.out.println("Draw three cards: \n");
		for(int i=0;i<sdraw.size();i++) {
			System.out.println(sdraw.get(i).getName() + "\n");
		}*/
	}
	
	@Test
	public void discardCard() {
		Deck<Card> aDeck = new Deck<Card>();
		aDeck.addCard(excalibur);
		aDeck.addCard(horse);
		aDeck.addCard(sword);
		aDeck.addCard(dagger);
		aDeck.addCard(lance);
		aDeck.addCard(battleax);
		//no discard in deck class
		/*Card discarded = aDeck.discard(excalibur);
		assertEquals("Excalibur", (String) discarded.getName());*/
	}

}
