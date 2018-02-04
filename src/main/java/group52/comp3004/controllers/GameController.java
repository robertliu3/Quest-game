package group52.comp3004.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import group52.comp3004.cards.AdventureCard;
import group52.comp3004.cards.Ally;
import group52.comp3004.game.GameState;
import group52.comp3004.players.Player;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class GameController implements Initializable {
	@FXML
	private GridPane gamepane;
	private DoubleProperty stageSize = new SimpleDoubleProperty();
	
	
	private List<PlayerAreaController> playerControllers;
	
	@FXML
	private Button dealtoplayer1;
	GameState model;
	
	//Constructor
	public GameController() {
		model = new GameState();
		Player demo = new Player(1234);
		Player demow = new Player(1234);
		Player demoww = new Player(1234);
		Player demowww= new Player(1234);
		model.addPlayer(demo);
		model.addPlayer(demow);
		model.addPlayer(demoww);
		model.addPlayer(demowww);
		stageSize.set(0);
		playerControllers = new ArrayList<>();
	}

	
	public DoubleProperty stageSizeProperty() {
		return stageSize;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)  {
		System.out.println("Game controller created");
		
		for(int i = 0; i < model.numPlayers(); i++) {
			final int index = i;
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/player_area.fxml"));
				AnchorPane player = loader.load();
				PlayerAreaController controller = loader.getController();
				controller.setHandClickBehaviour(card -> handCardOnClick(card, index));
				this.playerControllers.add(controller);
				if(index == 0) gamepane.add(player, 1, 5, 4, 1);
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
		
		Random rand = new Random();
		
		this.model.dealCardsToPlayers();
		this.updateAll();
		
		dealtoplayer1.setOnAction(e -> dealPlayer(rand.nextInt(this.model.numPlayers())));
	}
	
	public void playCard(AdventureCard card) {
		
	}
	
	public void handCardOnClick(AdventureCard card, int index) {
		if(!(card instanceof Ally)) return;
		Player player = this.model.getPlayerByIndex(index);
		player.playCardToField((Ally) card);
		this.playerControllers.get(index).update(player.getHand(),player.getField());
	}
	
	public void dealPlayer(int index) {
		this.model.dealToPlayer(index);
		Player player = this.model.getPlayerByIndex(index);
		this.playerControllers.get(index).update(player.getHand(),player.getField());
	}
	
	public void updateAll() {
		for(int i = 0; i < this.playerControllers.size(); i++) {
			Player player = this.model.getPlayerByIndex(i);
			this.playerControllers.get(i).update(player.getHand(),player.getField());
		}
	}
	
	
	
}
