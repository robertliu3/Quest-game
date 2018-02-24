package group52.comp3004.cards;

import java.util.ArrayList;

import group52.comp3004.ResourceManager;
import group52.comp3004.game.*;
import group52.comp3004.players.Player;

public class Ally extends AdventureCard
{
	private int bids;
	private String prereq;
	private int bonusbp;
	private int bonusbid;
	private boolean merlin = false;
	
	// TODO special abilities
	
	// used for allies without special cases (should not be used for Merlin)
	public Ally(String name, ResourceManager rm, int bp, int bids) {
		super(name);
		this.bp = bp;
		this.bids = bids;
		if(name.equals("Merlin")) this.merlin = true;	
	}
	
	// used for allies with special cases (should not be used for Merlin)
	public Ally(String name, ResourceManager rm, int bp, int bids, String prereq, int bonusbp, int bonusbid) {
		super(name);
		this.bp = bp;
		this.bids = bids;
		this.prereq = prereq;
		this.bonusbp = bonusbp;
		this.bonusbid = bonusbid;
		if(name.equals("Merlin")) this.merlin = true;
	}
	
	// should only be used for Merlin
	public Ally(String name, ResourceManager rm) {
		super(name);
		if(name.equals("Merlin")) this.merlin = true;
	}
	
	// determine if an ally's special ability is satisfied
	public boolean bonusSatisfied(GameState state) {
		if(this.prereq==null) return false;
		if(state.getCurrentQuest()!=null && 
				this.prereq.equals(state.getCurrentQuest().getQuest().getName())) {
			return true;
		}
		ArrayList<Player> players = new ArrayList<Player>(state.getAllPlayers());
		for(int i=0;i<players.size();i++) {
			ArrayList<AdventureCard> field = players.get(i).getField();
			for(int j=0;j<field.size();j++) {
				if(field.get(j).getName().equals(this.prereq)) {
					return true;
				}
			}
		}
		return false;
	}
	
	// spaghetti code to start Merlin's special ability
	public ArrayList<AdventureCard> StartMerlinSpecial(GameState state, int stage) {
		ArrayList<AdventureCard> cards = new ArrayList<AdventureCard>();
		if(!this.merlin) {
			System.out.println("NOT MERLIN\n");
			return null;
		}
		GameQuest q = state.getCurrentQuest();
		if(q==null) return null;
		if(stage>=q.getNumStages()) {
			System.out.println("INVALID STAGE");
			return null;
		}
		Stage s = q.getStage(stage-1);
		if(s.isTestStage()) {
			return cards;
		}else {
			Foe f = s.getFoe();
			cards.add(f);
			cards.addAll(f.getWeapons());
			for(int i=0;i<cards.size();i++) {
				cards.get(i).setFaceUp();
			}
			return cards;
		}
	}
	
	// spaghetti code to end Merlin's special ability
	public boolean EndMerlinSpecial(ArrayList<AdventureCard> cards) {
		if(!this.merlin) return false;
		for(int i=0;i<cards.size();i++) {
			cards.get(i).setFaceDown();
		}
		return true;
	}

	// get an ally's battle power
	public int getBp(GameState state) {
		if (bonusSatisfied(state)) return bp+bonusbp;
		return bp;
	}
	
	// get an ally's bids added
	public int getBids(GameState state) {
		if (bonusSatisfied(state)) return bids+bonusbid;
		return bids;
	}
	

}
