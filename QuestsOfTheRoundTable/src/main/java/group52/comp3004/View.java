package group52.comp3004;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.Group;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.fxml.*;

//tutorial imports 
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.text.*;
import javafx.scene.control.*;

public class View{
	//Variables
	Stage stage;
	Model model;
	//window size
	double w_size = 600;
	private String css;
	Parent root;
	
	//Constructor
	public View(Model myModel, Stage myStage) {
		model = myModel;
		stage = myStage;
		css = getClass().getResource("/view.css").toExternalForm();
		try {
			root = FXMLLoader.load(getClass().getResource("/PlayerArea.fxml")); //needs the fx:controller attribute filled in
		} catch(IOException ie) {
			ie.getMessage();
			System.out.println("Couldn't load fxml file. \n");
		}	
		
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
		Scene scene = new Scene(root, w_size, w_size);
		stage.setTitle("Quest of the Round Table");
		stage.setScene(scene);
		
		/*Group root = new Group();
        Scene scene = new Scene(root, w_size, w_size);
        scene.getStylesheets().clear();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        
        Group playArea1 = new Group();
        Rectangle area1 = new Rectangle(w_size/3, w_size/6, w_size/3, w_size/6);
        //area1.setFill(Color.black);
        //area1.setArcHeight(30);
        //area1.setArcWidth(30);
        
        Rectangle player1 = new Rectangle(4*w_size/6, 0, w_size/6, w_size/6);
        Rectangle hand1 = new Rectangle(w_size/3, 0, w_size/3, w_size/6);
        Rectangle extraHand1 = new Rectangle(w_size/6, 0, w_size/6, w_size/6);
        
        root.getChildren().add(area1);
        root.getChildren().add(player1);
        root.getChildren().add(hand1);
        root.getChildren().add(extraHand1);*/
        
        stage.show();
	}
}