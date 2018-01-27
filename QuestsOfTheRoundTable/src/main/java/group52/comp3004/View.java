package group52.comp3004;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class View{
	//Variables
	Stage stage;
	Model model;
	
	//Constructor
	public View(Model myModel, Stage myStage) {
		model = myModel;
		stage = myStage;
		initView();
	}
	
	public void initView() {
		stage.setTitle("Hello World!");
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        stage.setScene(new Scene(root, 500, 500));
        stage.show();
	}
}