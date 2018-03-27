package group52.comp3004.cards;

import group52.comp3004.game.GameState;

public class AdventureCard extends Card{

	protected int bp;
	protected int bids;
	
	public AdventureCard(String name) {
		super(name);
	}
		
	public int getBp(GameState state) {
		return this.bp;
	}
	
	public int getBp() {
		return this.bp;
	}
	
	public int getBids(GameState state) {
		return 0;
	}
	
	public int getBids() {
		return 0;
	}
	
	public boolean equals(AdventureCard c) {
		return c.getName().equals(this.getName());
	}


}