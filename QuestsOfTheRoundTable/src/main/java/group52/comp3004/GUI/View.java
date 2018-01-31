package group52.comp3004.GUI;

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
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class View{
	//Variables
	Stage stage;
	GameState model;
	//window size
	DoubleProperty w_size = new SimpleDoubleProperty();
	private String css;
	GridPane root;
	
	//Constructor
	public View(GameState myModel, Stage myStage) throws Exception {
		model = myModel;
		stage = myStage;
		w_size.set(600);
		css = getClass().getResource("/css/view.css").toExternalForm();
		
		//Load top level fxml document
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/game_field.fxml"));
		root = loader.load();
		GameController gameController = loader.getController();
		gameController.stageSizeProperty().bind(w_size);
		//load player area
		PlayerArea player1 = new PlayerArea(root);
		
	    
		//tutorialPart1();
		//tutorialPart2();
		initView();
	}
	public void tutorialPart1() {
		//JavaFX tutorials
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Text scenetitle = new Text("Welcome");
       // scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        scenetitle.setId("welcome-text");
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);
        //grid.setGridLinesVisible(true);
        
        Button btn = new Button("sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn,  1,  5);
        
        final Text actionTarget = new Text();
        grid.add(actionTarget, 1, 6);
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		//actionTarget.setFill(Color.RED);
        		actionTarget.setId("actiontarget");
        		actionTarget.setText("Sign in button pressed");
        	}
        });
        
        Scene t1Scene = new Scene(grid, 300, 275);
        t1Scene.getStylesheets().add(css);
        stage.setScene(t1Scene);
        stage.show();
	}

	public void tutorialPart2() {
		Scene t2Scene = new Scene(root, 300, 275);
		
		stage.setTitle("FXML Welcome");
		stage.setScene(t2Scene);
		stage.show();
	}
	
	public void initView() {
		Scene scene = new Scene(root, 600, 600);
		stage.setTitle("Quest of the Round Table");
		stage.setScene(scene);
		
		
        
        stage.show();
	}
}