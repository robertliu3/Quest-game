package group52.comp3004.game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import group52.comp3004.cards.AdventureCard;
import group52.comp3004.cards.Amour;
import group52.comp3004.cards.QuestCard;
import group52.comp3004.players.Player;

/**
 * Handles set up, playing, and ending a quest state.
 * <p>QuestCard is the card itself. GameQuest handles the quest game play.</p>
 * @author Sandy
 *
 */
public class GameQuest {
	
	private final QuestCard quest;
	private boolean withTest;
	private List<Stage> stages;
	private List<Player> players;
	private int currentStage;
	private Player sponsor;
	private boolean over;

	static final private Logger logger = Logger.getLogger(GameQuest.class);
	
	/**
	 * Constructor for a new quest. GameQuest functionally starts only after a player has decided to sponsor a quest.
	 * @param quest The quest card that was drawn from the story deck.
	 * @param sponsor The player that will set up the quest. 
	 */
	public GameQuest(QuestCard quest, Player sponsor) {
		this.quest = quest;
		this.withTest = false;
		this.stages = new ArrayList<Stage>();
		this.players = new ArrayList<Player>();
		this.currentStage = 0;
		this.sponsor = sponsor;
		this.over = false;
	}
	
	/**
	 * Returns true if one of the stages of the quest is a test.
	 */
	public boolean isWithTest() {
		return withTest;
	}

	/**
	 * Get the quest property.
	 * @return
	 */
	public QuestCard getQuest() {
		return quest;
	}
	
	/**
	 * Get the ith stage of the quest
	 * @param i An index of the stage list that exists
	 */
	public Stage getStage(int i) {
		return stages.get(i);
	}
	
	/**
	 * Get a list of stages of a quest
	 */
	public List<Stage> getStages() {
		return stages;
	}
	
	/**
	 * Used for the cheat function. Finds the number of cards in each stage including the foe/test card
	 * @return an array list of the number of cards in each stage.
	 */
	public ArrayList<Integer> getStageCardNum(){
		ArrayList<Integer> cNum = new ArrayList<Integer>();
		for(int i=0;i>stages.size();i++) {
			if(stages.get(i).isTestStage()) cNum.add(1);
			else cNum.add(1+stages.get(i).getFoe().getWeapons().size());
		}
		return cNum;
	}
	
	/**
	 * Can another stage be added to the quest.
	 * @return True if there at least one more spot available in the quest for a new stage.
	 */
	public boolean canAddStage() {
		return this.stages.size() < quest.getStages();
	}
	
	/**
	 * Remove all stages from quest.
	 */
	public void clearAllStages() {
		withTest = false;
		this.stages.clear();
	}
	
	/**
	 * Add a new stage to the quest. Can only add one test stage to a quest
	 * @param stage The stage that is being added
	 * @return True Stage added incorrectly
	 *     <p>False either trying to add a second test or ?what does second if statement do? 
	 */
	public boolean addStage(Stage stage) {
		
		if(stage.isTestStage() && withTest) return false;
		
		if(stage.isTestStage() && !withTest) {
			withTest = true;
			stages.add(stage);
			return true;
		}
		
		Stage highestStage = stages.stream().max( (s1, s2) -> Integer.compare(s1.getTotalPower(), s2.getTotalPower())).orElse(null);
		
		if((highestStage == null || stage.getTotalPower() >= highestStage.getTotalPower()) && stages.size() < quest.getStages()) {
			stages.add(stage);
			return true;
		}
		
		return false;
	}
	
	/**
	 * Get complete stage object of the current stage through the list of stages
	 */
	public Stage currentStage() {
		return stages.get(currentStage);
	}
	
	/**
	 * Get complete stage object of the current stage through the currentStage property
	 */
	public int getCurrentStage() {
		return currentStage;
	}
	
	/**
	 * ?
	 */
	public void advanceStage() {
		if(currentStage < (quest.getStages() - 1)) currentStage += 1;
	}
	
	/**
	 * Add a new participating player to the quest
	 * @param player
	 */
	public void addPlayer(Player player) {
		if(!this.players.contains(player) && player.getQuest() == null) {
			this.players.add(player);
			player.setQuest(this);
		}
	}
	
	/**
	 * ?
	 */
	public void playStage() {
		if(over || this.players.size() == 0) return;
		
		if(stages.get(currentStage).isTestStage()) {
			logger.info("Playing in a test stage...");
			Player remaining = players.stream().max((p1, p2) -> {
				if(p1.getOfferedBids() > p2.getOfferedBids()) return -1;
				if(p1.getOfferedBids() == p2.getOfferedBids()) return 0;
				return 1;
			}).get();
			
			logger.info("Remaining player is " + remaining.getId());
			
			if(remaining.getOfferedBids() == 0) {
				logger.info("Everyone dropped of the test!");
				this.over = true;
				this.players.clear();
			}
			else {
				this.players.clear();
				this.players.add(remaining);
			}
			
			advanceStage();
			
			return;
		}
		
		logger.info("Playing a foe stage!");
		
		List<Player> remaining = players.stream().filter(p -> p.getBattlePoints() >= stages.get(currentStage).getTotalPower()).collect(Collectors.toList());
		this.players.forEach(p -> p.clearTemp());
		logger.info("Done playing the stage...");
		logger.info(remaining.size() + " players are now in the quest.");
		this.players = remaining;
		if(currentStage == (quest.getStages() - 1) || this.players.size() < 1)  {
			logger.info("No players or we played all stages! Quest is over.");
			this.over = true;
		}
		advanceStage();	
	}
	
	/**
	 * ?
	 * @param state The current conditions of the game.
	 */
	public void playStage(GameState state) {
		if(over || this.players.size() == 0) return;
		
		if(stages.get(currentStage).isTestStage()) {
			System.out.println("Playing in a test stage...");
			Player remaining = players.stream().max((p1, p2) -> {
				if(p1.getOfferedBids() > p2.getOfferedBids()) return -1;
				if(p1.getOfferedBids() == p2.getOfferedBids()) return 0;
				return 1;
			}).get();
			
			System.out.println("Remaining player is " + remaining.getId());
			
			if(remaining.getOfferedBids() == 0) {
				System.out.println("Everyone dropped of the test!");
				this.over = true;
				this.players.clear();
			}
			else {
				this.players.clear();
				this.players.add(remaining);
			}
			
			advanceStage();
			
			return;
		}
		
		System.out.println("Playing a foe stage!");
		
		List<Player> remaining = players.stream().filter(p -> p.getBattlePoints(state) >= stages.get(currentStage).getTotalPower(state)).collect(Collectors.toList());
		ArrayList<AdventureCard> discard = new ArrayList<AdventureCard>();
		for(int i=0;i<players.size();i++) discard.addAll(players.get(i).clearTemp());
		state.getAdventureDeck().discard(discard);
		System.out.println("Done playing the stage...");
		System.out.println(remaining.size() + " players are now in the quest.");
		this.players = remaining;
		if(currentStage == (quest.getStages() - 1) || this.players.size() < 1)  {
			System.out.println("No players or we played all stages! Quest is over.");
			this.over = true;
		}
		advanceStage();	
	}
	
	/**
	 * Get the number of stages in the quest
	 */
	public int getNumStages() {
		return this.quest.getStages();
	}
	
	/**
	 * Gives shields to the players that complete the quest
	 */
	public void awardShields() {
		if(over) players.forEach(p -> p.addShields(quest.getStages()));
	}
	
	/**
	 * Gives shields to the players that complete the quest ?
//	 * @param bonus ?
	 */
	public void awardShields(int bonus) {
		if(over) players.forEach(p -> p.addShields(quest.getStages() + bonus));
	}
	
	/**
	 * Get the list of participating players
	 */
	public List<Player> getPlayers() {
		return this.players;
	}
	
	/**
	 * Returns true if the quest is finished
	 */
	public boolean isOver() {
		return this.over;
	}

	/**
	 * Returns true if the player is participating in the quest.
	 * @param player The player to test for
	 */
	public boolean isPlayer(Player player) {
		return this.players.contains(player); // || player == sponsor;
	}
	
	/**
	 * Get the player that sponsored the quest
	 */	
	public Player getSponsor() {
		return this.sponsor;
	}
	
	/**
	 * Change which player sponsored the quest
	 * @param player
	 */	
	public void setSponsor(Player player) {
		this.sponsor = player;
	}
	
	/**
	 * ?
	 * @return
	 */
	public int getNumCardsPlayedBySponsor() {
		return this.getStages().stream().mapToInt(s -> s.totalCardsPlayed()).sum() + stages.size();
	}
	
	/**
	 * ?
	 */
	public void dealCardsToSponsor() {
		for(int i = 0; i< getNumCardsPlayedBySponsor(); i++)
			sponsor.getGame().dealToPlayer(sponsor.getGame().getSponsorIndex());
	}
	
	/**
	 * Handles the a quest ending. All cards played into the quest are added to the discard pile and awards 
	 * @param state The current conditions of the game.
	 * @param bonus ?
	 */
	public void end(GameState state, int bonus) {
		this.over = true;
		dealCardsToSponsor();
		for(int i=0;i<state.getAllPlayers().size();i++) {
			Player p = state.getPlayerByIndex(i);
			for(int j=0;j<p.getTemp().size();j++) {
				if(p.getTemp().get(j) instanceof Amour) {
					state.getAdventureDeck().discard(p.getTemp().get(j));
					p.getTemp().remove(j);
					break;
				}
			}
		}
		for(int i=0;i<this.getNumStages();i++) {
			state.getAdventureDeck().discard(this.stages.get(i).getCards());
		}
		awardShields(bonus);
	}
	
	/**
	 * ?
	 */
	public void end() { 
		this.over = true;
		
	}
	
	/**
	 * ?
	 * @param bonus ?
	 */
	public void end(int bonus) {
		this.over = true;
		dealCardsToSponsor();
		awardShields(bonus);
	}
}