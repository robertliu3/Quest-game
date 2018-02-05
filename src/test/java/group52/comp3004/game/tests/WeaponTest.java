package group52.comp3004.cards.test;

import static org.junit.jupiter.api.Assertions.*;

import group52.comp3004.players.Player;
import group52.comp3004.cards.Weapon;
import group52.comp3004.cards.Foe;

import org.junit.jupiter.api.Test;

class WeaponTest {
	
	Player p1 = new Player(1);
	Weapon excalibur = new Weapon("Excalibur", 30);
	Weapon horse = new Weapon("Horse", 10);
	Weapon sword = new Weapon("Sword", 10);
	Weapon dagger = new Weapon("Dagger", 5);
	Weapon lance = new Weapon("Lance", 20);
	Weapon battleax = new Weapon("Battle-ax", 15);
	Foe giant = new Foe("Giant", 40, 40);

	@Test
	void playerAddWeapon() {
		p1.addWeapon(excalibur);
		assertEquals(35, (int) p1.getBattlePoints());
	}
	
	@Test
	void playerAddSameWeapon() {
		boolean result = p1.addWeapon(excalibur);
		assertEquals(false, (boolean) result);
		assertEquals(35, (int) p1.getBattlePoints());
	}
	
	@Test
	void playerAddMultipleWeapons() {
		p1.addWeapon(horse);
		p1.addWeapon(dagger);
		assertEquals(40, (int) p1.getBattlePoints());
	}
	
	@Test
	void playerClearWeapon() {
		p1.clearWeapons();
		assertEquals(5, (int) p1.getBattlePoints());
	}
	
	@Test
	void foeAddWeapon() {
		giant.addWeapon(dagger);
		assertEquals(45, (int) giant.getBP());
	}
	
	@Test
	void foeAddSameWeapon() {
		boolean result = giant.addWeapon(dagger);
		assertEquals(false, (boolean) result);
		assertEquals(45, (int) giant.getBP());
	}
	
	@Test
	void foeAddMultipleWeapon() {
		giant.addWeapon(lance);
		giant.addWeapon(battleax);
		assertEquals(80, (int) giant.getBP());
	}

	@Test
	void foeClearWeapon() {
		giant.clearWeapons();
		assertEquals(40, (int) giant.getBP());
	}
}
