package group52.comp3004.game;

import java.util.ArrayList;
import java.util.List;

import group52.comp3004.cards.Tourneys;
import group52.comp3004.players.Player;


public class GameTourney {
	private final Tourneys tourney;
	private List<Player> players;
	private List<Player> count,count1;
	//private Player sponsor;
	private int bonus;
	private List<Player> winner;
	private boolean over;
	
	public GameTourney(Tourneys tourney) {
		this.tourney = tourney;
		this.players = new ArrayList<Player>();
		this.count = new ArrayList<Player>();
		this.count1 = new ArrayList<Player>();
		//this.sponsor = sponsor;
		this.winner=new ArrayList<Player>();
		this.bonus=0;
		this.over= false;
	}
	
	public Tourneys getTourney() {
		return tourney;
	}
	
	public List<Player> getPlayers() {
		return this.players;
	}
	
	//Why need to add tourney inside player?
	public void addPlayer(Player player) {

			this.players.add(player);
			player.setTourney(this);
	}

	public boolean isOver() {
		return this.over;
	}
	
	public void dealCards() {
		for(int i=0;i<this.players.size();i++) {
			this.players.get(i).getGame().dealToPlayer(i);
		}
			
	}
	
	public List<Player> winner() {
		List<Player> win= battle(this.players);
		if(this.players.size()==1) {
			this.winner= this.players;
			bonus=2;
		}else {
			int count = win.size();
			if(count>1) {
					this.winner = secondBattle(win);
				}
			else {
				this.winner = win;
			}
		}
		return this.winner;


	}
public List<Player> secondBattle(List<Player> player) {
	    this.count1.clear();
		Player highest= player.get(0);
		this.count1.add(player.get(0));
		for(int i=1;i<player.size();i++) {
			if(player.get(i).getBattlePoints()>highest.getBattlePoints()) {
				highest =player.get(i);
				this.count1.clear();
				this.count1.add(player.get(i));
			}else if(player.get(i).getBattlePoints()==highest.getBattlePoints()) {
				this.count1.add(player.get(i));
			}
		}
		return this.count1;
	}
	public List<Player> battle(List<Player> player) {
		this.count.clear();
		Player highest= player.get(0);

		this.count.add(player.get(0));
		for(int i=1;i<this.players.size();i++) {
			if(player.get(i).getBattlePoints()>highest.getBattlePoints()) {
				highest =player.get(i);
				this.count.clear();
				this.count.add(player.get(i));
			}else if(player.get(i).getBattlePoints()==highest.getBattlePoints()) {
				this.count.add(player.get(i));
			}
		}
		return this.count;
	}
	public void awardShields() {
		 this.winner.forEach(p -> p.addShields(players.size()+tourney.getShields() + bonus));
	}
	
	public void end() {
		for(int i =0;i<this.players.size();i++) {
			this.getPlayers().get(i).clearTemp();
		}
		this.over = true;
		this.count.clear();
		this.count1.clear();
		bonus = 0;
	}

	public boolean isPlayer(Player player) {
		// TODO Auto-generated method stub
		return this.players.contains(player); // || player == sponsor;
	}
}
