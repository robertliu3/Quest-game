package group52.comp3004.GUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class PortraitArea {
	private Pane area;
	
	//Constructor
	public PortraitArea(Pane parent) throws Exception{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/portrait_area.fxml"));
		area = loader.load();

		area.setStyle("-fx-background-color: #0000FF");
		System.out.println("Portrait area created");
	}
	
	public Pane getPane() { return area; }
}