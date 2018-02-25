package group52.comp3004.cards;

import group52.comp3004.ResourceManager;

public class QuestCard extends StoryCard {
	
	private final int stages;
	// private final List<Foe> foes;
	
	public QuestCard(String name, ResourceManager rm, int stages) {
		super(name, rm);
		this.stages = stages;
	}
	
	public int getStages() { return this.stages; }

}