package group52.comp3004;

public class Game {
	//MVC classes
	private Model model;
	private View view;
	private Controller controller;
	
	 // need to create these two arrays for use later
    
	//constructor
	public Game() {
		model = new Model();
		view = new View(model);
		controller = new Controller(model, view);
	}
	
	//create the game loop which is called in main function
    public void loop() {
    	//while(window is open){
    		controller.inputs();
    		model.update();
    		view.render();
    	//}
    }
    
    //main class
    public static void main(String[] args) {
    	//Game game = new Game();
    	
    	//start loop
    	//game.loop();
    	System.out.println("Hello");
    }
}
