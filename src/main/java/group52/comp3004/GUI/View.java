package group52.comp3004.GUI;

import group52.comp3004.cards.Card;
import group52.comp3004.controllers.GameController;
import group52.comp3004.game.GameState;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
//tutorial imports 
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class View{
	//Variables
	Stage stage;
	Scene scene;
	//window size
	DoubleProperty w_size = new SimpleDoubleProperty();
	private String css;
	GridPane root;
	
	//Constructor
	public View(Stage myStage) throws Exception {
		stage = myStage;
		w_size.set(600);
		css = getClass().getResource("/css/view.css").toExternalForm();
		
		//Load top level fxml document
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/game_field.fxml"));
		root = loader.load();
		GameController gameController = loader.getController();
		gameController.stageSizeProperty().bind(w_size);
		//load player area
		//PlayerArea player1 = new PlayerArea(root);

		initView();
	}
	
	public void initView() {
		scene = new Scene(root, 600, 600);
		scene.getStylesheets().addAll(css);
		stage.setTitle("Quest of the Round Table");
		stage.setScene(scene);
        
        stage.show();
	}
	
	public Scene getScene() {
		return scene;
	}
}