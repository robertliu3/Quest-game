package group52.comp3004.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import group52.comp3004.cards.AdventureCard;
import group52.comp3004.cards.Ally;
import group52.comp3004.cards.EventCard;
import group52.comp3004.cards.QuestCard;
import group52.comp3004.cards.StoryCard;
import group52.comp3004.cards.Tourneys;
import group52.comp3004.game.GameState;
import group52.comp3004.game.Phase;
import group52.comp3004.players.Player;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class GameController implements Initializable {
	@FXML
	private GridPane gamepane;
	private DoubleProperty stageSize = new SimpleDoubleProperty();
	
	
	private List<PlayerAreaController> playerControllers;
	private MiddleAreaController middleController;
	
	@FXML
	private Button dealtoplayer1, startGame;
	private GameState model;
	
	//Constructor
	public GameController() {
		model = new GameState();
		Player demo = new Player(1234, this, model);
		//Player demow = new Player(1234);
		//Player demoww = new Player(1234);
		//Player demowww= new Player(1234);
		model.addPlayer(demo);
		//model.addPlayer(demow);
		//model.addPlayer(demoww);
		//model.addPlayer(demowww);
		stageSize.set(0);
		playerControllers = new ArrayList<>();
		middleController = null;
	}

	
	public DoubleProperty stageSizeProperty() {
		return stageSize;
	}

	//PURPOSE: Controller Initialization
	@Override
	public void initialize(URL location, ResourceBundle resources)  {
		System.out.println("Game controller created");
		startGame.setOnAction(e -> this.startGame());
		
			dealtoplayer1.setVisible(false);
	}
	
	/*****************************************************************************************************/
	//GAME STATE EXECUTION METHODS
	/*****************************************************************************************************/
	//PURPOSE: Execute the start game phase
	//		*adds all play areas to the game
	//		*move to phase TurnStart after
	public void startGame() {
		System.out.println("Starting Game");
		
		//remove the startGame button
		startGame.setOnAction(null);
		startGame.setVisible(false);
		
		//call GUI creation methods
		this.createMiddleArea();
		this.createPlayerAreas();
		//this.addAdventureDeck();
		//this.addStoryDeck();
		
		//deal out cards
			dealtoplayer1.setVisible(true);
			Random rand = new Random();
			dealtoplayer1.setOnAction(e -> dealPlayer(rand.nextInt(this.model.numPlayers())));
		this.updateInfo();
		this.model.dealCardsToPlayers();
		
		//update all controllers
		this.updateAll();
		
		//move into normal game loop
		model.setPhase(Phase.TurnStart);
		this.startTurn();
	}
	//PURPOSE: Execute TurnStart Phase
	//		*placeholder if we want to add future behaviour
	public void startTurn() {
		model.setPhase(Phase.RevealStory);
		this.revealStory();
	}
	//PURPOSE: Execute RevealStory Phase
	//		*Model deals a story card from deck
	//		*story card is added to middle area
	//		*next phase depends on type of card dealt
	public void revealStory() {
		middleController.addStory(model.dealStoryCard());
		if(model.getRevealedCard() instanceof EventCard) {
			model.setPhase(Phase.HandleEvent);
			this.handleEvent();
		}
		else if(model.getRevealedCard() instanceof Tourneys) {
			model.setPhase(Phase.SponsorTourney);
			this.sponsorTourney();
		}
		else if(model.getRevealedCard() instanceof QuestCard) {
			model.setPhase(Phase.SponsorQuest);
			this.sponsorQuest();
		}
		else {
			System.out.println("Unknown card type added to story");
			model.setPhase(Phase.Broken);
		}
	}
	//PURPOSE: Execute HandleEvent Phase
	public void handleEvent() {
		model.setPhase(Phase.TurnEnd);
		this.endTurn();
	}
	//PURPOSE: Execute SponsorTourney Phase
	public void sponsorTourney() {
		model.setPhase(Phase.JoinTourney);
		this.joinTourney();
	}
	//PURPOSE: Execute JoinTourney Phase
	public void joinTourney() {
		model.setPhase(Phase.RunTourney);
		this.runTourney();
	}
	//PURPOSE: Execute RunTourney Phase
	public void runTourney() {
		model.setPhase(Phase.TurnEnd);
		this.endTurn();
	}
	//PURPOSE: Execute SponsorQuest Phase
	public void sponsorQuest() {
		model.setPhase(Phase.SetupQuest);
		this.setupQuest();
	}
	//PURPOSE: Execute SetupQuest Phase
	public void setupQuest() {
		model.setPhase(Phase.RunQuest);
		this.runQuest();
	}
	//PURPOSE: Execute RunQuest Phase
	public void runQuest() {
		model.setPhase(Phase.EndQuest);
		this.endQuest();
	}
	//PURPOSE: Execute EndQuest Phase
	public void endQuest() {
		model.setPhase(Phase.TurnEnd);
		this.endTurn();
	}
	//PURPOSE: Execute TurnEnd Phase
	public void endTurn() {
		model.setPhase(Phase.TurnStart);
		this.startTurn();
	}	
	
	/*********************************************************************************************/
	//UTILITY METHODS
	/*********************************************************************************************/

	public void playCard(AdventureCard card) {
		
	}

	//CLICK EVENT: A card in the hand has been clicked
	public void handCardOnClick(AdventureCard card, int index) {
		System.out.println("Clicked card: " + card.getName());
		if(!(card instanceof Ally)) return;
		Player player = this.model.getPlayerByIndex(index);
		player.playCardToField((Ally) card);
		this.playerControllers.get(index).update(player.getHand(),player.getField());
	}
	
	//CLICK EVENT: Card dealt from adventure deck
	public void dealPlayer(int index) {
		System.out.println("Dealt card");
		this.model.dealToPlayer(index);
		Player player = this.model.getPlayerByIndex(index);
		this.playerControllers.get(index).update(player.getHand(),player.getField());
	}
	
	//
	public void updateInfo() {
		for(int i = 0; i < model.numPlayers(); i++) {
			Player player = this.model.getPlayerByIndex(i);
			this.playerControllers.get(i).updateIn(player);
		}
	}

	//PURPOSE: Update cards for all controllers in game
	public void updateAll() {
		//update player hand and field arraylists for each player
		for(int i = 0; i < this.playerControllers.size(); i++) {
			Player player = this.model.getPlayerByIndex(i);
			this.playerControllers.get(i).update(player.getHand(),player.getField());
		}
		
		//update middle area
		middleController.setStoryCard(model.getRevealedCard());
	}	
	
	//Start Game->Initialization Methods
	//PURPOSE: Load in the FXML files for the middle area
	public void createMiddleArea() {
		try {
			FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/fxml/middle_area.fxml"));
			StackPane middle = loader1.load();
			gamepane.add(middle, 2, 2, 2, 2);
			middleController = loader1.getController();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	//PURPOSE: Load in the FXML files for the player areas
	public void createPlayerAreas() {
		for(int i = 0; i < model.numPlayers(); i++) {
			final int index = i;
			try {
				FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/fxml/player_area.fxml"));
				GridPane player = loader2.load();
				PlayerAreaController controller = loader2.getController();
				//controller.setHandClickBehaviour(card -> handCardOnClick((AdventureCard) card, index));
				this.playerControllers.add(controller);
				
				//add the player areas to the 
				if(index == 0) gamepane.add(player, 1, 6, 4, 2);
				else if(index == 1) {
					gamepane.add(player, 0, 1, 1, 4);
				}
				else if(index == 2) {
					gamepane.add(player, 1, 0, 4, 1);
				}
				else if(index == 3) {
					gamepane.add(player, 5, 1, 1, 4);
				}
			}
			catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
	//PURPOSE: add Adventure Deck GUI item
	public void addAdventureDeck() {
		AdventureCard adventureDeck = new AdventureCard("Adventure deck card", model.getResourceManager());
		StackPane deckPane = new StackPane();
		deckPane.getChildren().add(adventureDeck);
		gamepane.add(adventureDeck, 0, 5);
	}
	//PURPOSE: add Story Deck GUI item
	//	-->Incomplete
	public void addStoryDeck() {
		StoryCard storyDeck = new StoryCard("Story deck card", model.getResourceManager());
	}

}
