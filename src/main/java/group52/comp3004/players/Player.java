package group52.comp3004.players;

import java.util.ArrayList;
import java.util.List;

import group52.comp3004.GUI.PlayerArea;
import group52.comp3004.cards.AdventureCard;
import group52.comp3004.cards.Ally;
import group52.comp3004.cards.Weapon;
import group52.comp3004.controllers.GameController;
import group52.comp3004.game.GameQuest;
import group52.comp3004.game.GameState;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class Player {
	
	private Integer id;
	private Integer shields;
	private Rank rank;
	private Integer battlePoints;
	private Integer requiredShields;
	private List<Integer> weapons;
	private int minShields;
	private ArrayList<AdventureCard> hand;
	private ArrayList<AdventureCard> field;
	private ArrayList<AdventureCard> temp;
	private GameState game;
	private GameQuest quest;
	private GameController controller;
	
	//this needs to be fixed - wrecks the testing
	public Player(Integer id, GameController gc, GameState gs) {
		this.id = id;
		shields = 10;
		rank = Rank.Squire;
		battlePoints = 5;
		requiredShields = 15;
		minShields = 10;
		weapons = new ArrayList<Integer>();
		hand = new ArrayList<>();
		field = new ArrayList<>();
		temp = new ArrayList<>();
		quest = null;
		controller = gc;
		game = gs;
	}
	
	public Player(Integer id) {
		this.id = id;
		shields = 10;
		rank = Rank.Squire;
		battlePoints = 5;
		requiredShields = 15;
		minShields = 10;
		weapons = new ArrayList<Integer>();
		hand = new ArrayList<>();
		field = new ArrayList<>();
		temp = new ArrayList<>();
		quest = null;
	}
	
	public Integer getId() {
		return id;
	}
	
	public Integer getShields() {
		return shields;
	}
	
	public Rank getRank() {
		return rank;
	}
	
	public void setGame(GameState game) {
		this.game = game;
	}
	
	public GameState getGame() {
		return this.game;
	}
	
	public GameQuest getQuest() {
		return this.quest;
	}
	
	public void setQuest(GameQuest quest) {
		this.quest = quest;
		//quest.addPlayer(this);
	}
	
	public Integer getBattlePoints() {
		return battlePoints + temp.stream().mapToInt(c -> c.getBp()).sum() + field.stream().mapToInt(c -> c.getBp()).sum();
	}
	
	public void clearWeapons() {
		this.weapons.clear();
	}

	public Integer getRequiredShields() {
		return requiredShields;
	}
	
	public void addWeapon(Integer weapon) {
		this.weapons.add(weapon);
	}
	
	public void addShields(Integer shields) {		
		this.shields += shields;
		if(this.shields < minShields) this.shields = minShields;
		if(this.shields >= this.requiredShields) {
			updateRank();
		}
	}
	
	
	public ArrayList<AdventureCard> getHand() {
		return hand;
	}
	
	public void setHand(ArrayList<AdventureCard> hand) {
		this.hand = hand;
	}
	
	
	public void addCardToHand(AdventureCard card) {
		System.out.println("Adding "+card.getName()+" to hand");
		card.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				card.removeEventHandler(MouseEvent.MOUSE_CLICKED, this);//isnt removing the card
				System.out.println(card.getName()+ " clicked");
				//change so card click behaviour changes based on the phase
				/*
				 if(game.getPhase() == SetupQuest){
				 	//add foes and weapons to quest
				 	hand.remove(card);
				 	controller.updateAll();	
				 }
				 else if(game.getPhase() == RunQuest){
				 	//add weapons and allies
				 	field.add(card);
					hand.remove(card);	
					controller.updateAll();	
				 }
				 else if(game.getPhase() == RunTourney){
				 	//add weapons to tourney
				 	hand.remove(card);	
					controller.updateAll();	
				 }
				 else{
				 	//do nothing
				 }
				 */
				field.add(card);
				hand.remove(card);	
				controller.updateAll();	
			}
		});
		this.hand.add(card);
	}
	
	public boolean canPlayWeapon(Weapon weapon) {
		return !this.temp.contains(weapon);
	}
	
	public boolean hasCardInHand(AdventureCard card) {
		return this.hand.contains(card);
	}
	
	public void playCardToField(Ally card) {//why only Ally
		if(!hasCardInHand(card)) return;
		this.field.add(card);
		this.hand.remove(card);
	}
	
	public void playToTemp(AdventureCard card) {//whats this for
		if(!this.hasCardInHand(card)) return;
		if(card instanceof Weapon && !canPlayWeapon((Weapon) card)) return;
		
		this.temp.add(card);
		this.hand.remove(card);
	}
	
	public void addField(Ally card) {
		this.field.add(card);
	}
	
	public void addTemp(AdventureCard card) {
		this.temp.add(card);
	}
	
	public void clearTemp() {
		
	}

	private void updateRank() {
		minShields = requiredShields;
		if(rank == Rank.Squire) {
			requiredShields = 22;
			rank = Rank.Knight;
			battlePoints = 10;
		}
		else if(rank == Rank.Knight) {
			requiredShields = 32;
			rank = Rank.ChampionKnight;
			battlePoints = 20;
		}
		else if(rank == Rank.ChampionKnight) {
			rank = Rank.KnightOfTheRoundTable;
		}
	}

	public ArrayList<AdventureCard> getField() {
		// TODO Auto-generated method stub
		return this.field;
	}

	public ArrayList<AdventureCard> getTemp() {
		// TODO Auto-generated method stub
		return this.temp;
	}
}
