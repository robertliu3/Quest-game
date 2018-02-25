package group52.comp3004.GUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class FieldArea{
	private Pane area;
	
	//Constructor
	public FieldArea(Pane parent) throws Exception{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/field_area.fxml"));
		area = loader.load();
		
		area.setStyle("-fx-background-color: #FF0000");
		System.out.println("Field area created");
	}
	
	public Pane getPane() { return area; }
}