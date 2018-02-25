package group52.comp3004.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import group52.comp3004.cards.AdventureCard;
import group52.comp3004.players.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class PlayerAreaController implements Initializable{
	
	
	@FXML
	private FieldAreaController fieldAreaController;
	
	@FXML
	private HandAreaController handAreaController;
	
	@FXML
	private PortraitAreaController portraitAreaController;
	
	@FXML
	private Button addCardBtn;
	
	private SimpleStringProperty prop;
	
	//Constructor
	public PlayerAreaController() {
		prop = new SimpleStringProperty("lol");
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("player area loaded");
	}

	//update card that are in the field
	public void setField(ArrayList<AdventureCard> cards) {
		this.fieldAreaController.updateCards(cards);
		
	}
	
	//update cards that are in the hand
	public void setHand(ArrayList<AdventureCard> cards) {
		this.handAreaController.updateCards(cards);
		
	}
	
	public void setPortraitArea(Player player) {
			this.portraitAreaController.playerInfo(player);
	}
	
	public void updateIn(Player player) {
		setPortraitArea(player);
	}


	public void update(ArrayList<AdventureCard> hand, ArrayList<AdventureCard> field) {
		setField(field);
		setHand(hand);
	}
}