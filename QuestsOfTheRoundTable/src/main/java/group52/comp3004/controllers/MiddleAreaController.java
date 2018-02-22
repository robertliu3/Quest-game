package group52.comp3004.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import group52.comp3004.ResourceManager;
import group52.comp3004.cards.AdventureCard;
import group52.comp3004.cards.StoryCard;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class MiddleAreaController implements Initializable{
	
	@FXML
	private StackPane middlePane;
	private HBox questContainer;
	private StoryCard storyCard;
	
	//temp
	private ResourceManager rm;
	
	public MiddleAreaController() {
		super();
		//temp
		rm = new ResourceManager();
		storyCard = new StoryCard("York", rm);
		questContainer = new HBox();
		questContainer.setAlignment(Pos.CENTER);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//middlePane.getChildren().add(storyCard);
		middlePane.getChildren().add(questContainer);
		questContainer.getChildren().add(storyCard);
		System.out.println("Middle area crated");
	}
	
	public void updateCards(ArrayList<AdventureCard> cards) {
		questContainer.getChildren().clear();
		questContainer.getChildren().addAll(cards);
	}
}