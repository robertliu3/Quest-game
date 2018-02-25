package group52.comp3004.cards;

import group52.comp3004.ResourceManager;
import group52.comp3004.game.GameState;

public class AdventureCard extends Card{

	private String description;
	protected int bp;
	
	public AdventureCard(String name, ResourceManager rm ) {
		super(name, rm);
		this.description = null;
		
		//get face resources
		front = resman.getFront(name, 'a');
		back = resman.getAdventureBack();
		this.setFill(front);
	}
	
	public void setDes(String description) {
		this.description= description;
	}
	
	public String getDes() {
		return this.description;
	}
		
	public int getBp(GameState state) {
		return this.bp;
	}
	
	public int getBp() {
		return this.bp;
	}

}