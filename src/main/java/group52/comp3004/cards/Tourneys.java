package group52.comp3004.cards;

import group52.comp3004.ResourceManager;

public class Tourneys extends StoryCard{


	private final int bonusShields;
	public Tourneys(String name, ResourceManager rm, int bonusShields) {
		super(name, rm);
		this.bonusShields = bonusShields;
		// TODO Auto-generated constructor stub
	}
	
	public int getShields() {
		return this.bonusShields;
	}
}
