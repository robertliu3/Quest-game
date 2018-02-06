package group52.comp3004.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import group52.comp3004.GUI.cards.GameCard;
import group52.comp3004.cards.AdventureCard;
import group52.comp3004.cards.Card;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

public class HandAreaController implements Initializable{
	
	@FXML
	private HBox handContainer;

	
	private CardClickBehaviour clickBehaviour;
	/**
	 * 
	 */
	public HandAreaController() {
		super();
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Field area crated");
	}
	
	
	public void updateCards(ArrayList<AdventureCard> cards2) {
		System.out.println("hello");
		handContainer.getChildren().clear();
		handContainer.getChildren().addAll(cards2.stream().map(c -> makeGameCard(c)).collect(Collectors.toList()));
	}


	private GameCard makeGameCard(AdventureCard c) {
		GameCard gameCard = new GameCard(c);
		gameCard.setOnMouseClicked(e -> clickBehaviour.handClick((AdventureCard) gameCard.getCard()));
		
		return gameCard;
	}


	public void setClickBehaviour(CardClickBehaviour clickBehaviour) {
		this.clickBehaviour = clickBehaviour;
	}
}
