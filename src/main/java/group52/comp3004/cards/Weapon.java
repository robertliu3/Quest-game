package group52.comp3004.cards;

import group52.comp3004.ResourceManager;

public class Weapon extends AdventureCard{
	
	public Weapon(String name, ResourceManager rm, int bp) {
		super(name, rm);
		this.bp = bp;
	}
}