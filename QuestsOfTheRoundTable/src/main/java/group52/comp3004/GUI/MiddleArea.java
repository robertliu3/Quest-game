package group52.comp3004.GUI;

import group52.comp3004.cards.Card;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;


public class MiddleArea{
	private Pane area;
	
	//Constructor
	public MiddleArea() throws Exception{
		
		System.out.println("Middle area created");
	}
	
	public Pane getPane() { return area; }
}