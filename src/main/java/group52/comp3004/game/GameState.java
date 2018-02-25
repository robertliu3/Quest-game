package group52.comp3004.game;

import java.util.ArrayList;
import java.util.List;

import group52.comp3004.ResourceManager;
import group52.comp3004.cards.AdventureCard;
import group52.comp3004.cards.Foe;
import group52.comp3004.cards.QuestCard;
import group52.comp3004.cards.StoryCard;
import group52.comp3004.decks.Deck;
import group52.comp3004.players.Player;

public class GameState {
	
	private List<Player> players;
	private int currentTurn;
	private int currentPlayer;
	private int currentSponsor;
	private Phase phase;
	private Deck<AdventureCard> adventureDeck;
	private Deck<StoryCard> storyDeck;
	private GameQuest currentQuest;
	private StoryCard revealedCard;
	private static ResourceManager resman = null;
	/**
	 * @param players
	 */
	
	public GameState(List<Player> players) {
		super();
		if(resman == null) resman = new ResourceManager();
		this.players = players;
		this.currentTurn = 0;
		this.currentPlayer = 0;
		phase = Phase.TurnStart;
		System.out.println("Model loaded (players)");
	}
	
	public GameState() {//<- Issue model loading twice?
		super();
		if(resman == null) resman = new ResourceManager();
		this.players = new ArrayList<>();
		this.currentTurn = 0;
		this.currentPlayer = 0;
		this.currentSponsor = -1;
		phase = Phase.TurnStart;		
		adventureDeck = new Deck<AdventureCard>(Deck.createAdventureDeck(resman));
		storyDeck = new Deck<StoryCard>(Deck.createStoryDeck(resman));
		currentQuest = null;
		revealedCard = null;
		System.out.println("Model loaded (void)");
	}
	
	//PURPOSE: deal initial cards to all players
	public void dealCardsToPlayers() {
		this.players.forEach(player -> {
			for(int i = 0; i < 12; i++) {
				System.out.println("Card "+i+" dealt");
				player.addCardToHand(adventureDeck.drawCard());
			}
		});
	}

	//deal a story card to the middle area
	public StoryCard dealStoryCard() {
		revealedCard = storyDeck.drawCard();
		return revealedCard;
	}

	public void addPlayer(Player player) {
		if(players.size() < 4) players.add(player);
	}


	public void nextTurn() {
		currentTurn = (currentTurn + 1) % players.size();
		currentPlayer = currentTurn;
	}
	
	public void nextPlayer() {
		currentPlayer = (currentPlayer + 1) % players.size();
		
		while(phase == Phase.SetupQuest 
				&& this.currentQuest != null && !this.currentQuest.isPlayer(this.players.get(currentPlayer))) {
			currentPlayer = (currentPlayer + 1) % players.size();
		}
	}
	
	public int getCurrentTurn() {
		return this.currentTurn;
	}
	
	public int getCurrentPlayer() {
		return this.currentPlayer;
	}
	
	public Player getPlayerByIndex(int index) {
		return this.players.get(index);
	}
	
	//Deals a new adventure cards to player
	public void dealToPlayer(int index) {
		AdventureCard card = adventureDeck.drawCard();
		if(card != null)
			this.players.get(index).addCardToHand(card);
	}
	
	public int getSponsorIndex()
	{
		return this.currentSponsor;
	}
	
	public int numPlayers() {
		return this.players.size();
	}
	public void joinQuest() {
		if(currentQuest != null && currentSponsor != currentPlayer) currentQuest.addPlayer(getPlayerByIndex(currentPlayer));
	}
	
	public void setQuest() {
		if(revealedCard != null && revealedCard instanceof QuestCard)
		{
			currentSponsor = currentPlayer;
			currentQuest = new GameQuest((QuestCard) revealedCard, getPlayerByIndex(currentSponsor));
			this.players.get(currentPlayer).setQuest(currentQuest);
			this.revealedCard = null;
			this.phase = Phase.SponsorQuest;
		}
	}
	
	public boolean setUpQuestStage(Foe foe) {
		if(currentQuest != null && currentQuest.canAddStage() && currentSponsor == currentPlayer
				&& phase == Phase.SetupQuest) {
			currentQuest.addStage(new Stage(foe));
			return true;
		}
		
		return false;
			
	}
	
	public boolean playCardToTemp(AdventureCard card) {
		if(currentPlayer == currentSponsor && phase != Phase.SetupQuest) return false;
		if(!currentQuest.isPlayer(this.players.get(currentPlayer))) return false;
		
		Player p = this.players.get(currentPlayer);
		p.playToTemp(card);
		return true;
	}
	
	public void endQuest() {
		currentSponsor = -1;
		currentQuest = null;
		
		// remove all allies and all cards and make people draw (call quest end)
	}
	
	public void playCurrentQuestStage() {
		if(currentQuest != null) currentQuest.playStage();
	}
	
	public Player getCurrentSponsor() {
		if(currentSponsor != -1) return this.players.get(currentSponsor);
		return null;
	}
	
	//GETTERS and SETTERS
	public StoryCard getRevealed() { return this.revealedCard; }
	
	public GameQuest getCurrentQuest() { return this.currentQuest; }
	
	public StoryCard getRevealedCard() { return this.revealedCard; }
	public void setRevealedCard(StoryCard card) { this.revealedCard = card; }

	public void setPhase(Phase phase) { this.phase = phase; }
	public Phase getPhase() { return this.phase; }
	
	public ResourceManager getResourceManager() { return resman; }
	
	public List<Player> getAllPlayers() { return this.players; }
	
	public Deck<AdventureCard> getAdventureDeck() { return adventureDeck; }
}