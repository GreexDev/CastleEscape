package Fight;

import java.util.Scanner;

import Entities.Monster;
import Entities.Player;

public class Fight {
	private Monster monster;
	private Player player;
	private Scanner scanner;
	private ProcCount2Seconds proc;
	
	public Fight(Monster monster, Player player, Scanner scanner) {
		this.monster = monster;
		this.player = player;
		this.scanner = scanner;
	}
	
	// Return true for win and false for lose
	public boolean run() {
		proc = new ProcCount2Seconds();
		proc.start();
		
		// While entities are alive
		while(player.getLifePoint() > 0 && monster.getLifePoint() > 0) {
			this.showMiniGame();
			// Restart Proc if ended
			proc = this.restartProc(proc);
		}
		// Processus is stopped
		
		return (player.getLifePoint() != 0);
	}
	
	private ProcCount2Seconds restartProc(ProcCount2Seconds proc) {
		// If processus has ended and entities are alive
		if (! proc.isAlive() && player.getLifePoint() > 0 && monster.getLifePoint() > 0) {
			// Monster punch player
			int damage = this.calculDamageForPlayer();
			player.setLifePoint(this.calculLifeRemainingForPlayer(damage));
			
			// Start new processus : monster punches player every 2 seconds
			ProcCount2Seconds proc2 = new ProcCount2Seconds();
			proc2.start();
			
			// Return new processus
			return proc2;
		}
		// return old processus
		return proc;
	}
	
	private void showMiniGame() {
		int punch = 0;
		while(punch != 1) {
			// Restart Proc if ended
			proc = this.restartProc(proc);
			// Press punch
			System.out.println("Enter 1 to punch the monster :");
			punch = this.scanner.nextInt();
			// If player is dead
			if (player.getLifePoint() < 0) {
				break;
			}
			// Calcul damage
			int damage = this.calculDamageForMonster();
			// Set lifePoint of monster
			monster.setLifePoint(this.calculLifeRemainingForMonster(damage));
		}
		this.showLifePoints();
	}
	
	private int calculDamageForMonster() {
		int damage = 0;
		// If player has no weapon
		if (player.getWeapon() == null) {
			damage = player.getPower() - monster.getArmor();
		} else { // If he has weapon
			damage = player.getPower() + player.getWeapon().getPower() - monster.getArmor();
		}
		// If damage is negative
		if (damage < 0) {
			return 0;
		} else {
			return damage;
		}
	}
	
	private int calculLifeRemainingForMonster(int damage) {
		int lifePoint = monster.getLifePoint() - damage;
		// If lifePoint is negative
		if (lifePoint < 0) {
			return 0;
		} else {
			return lifePoint;
		}
	}
	
	private void showLifePoints() {
		System.out.println("Life points remaining :");
		System.out.println("Player : "+this.player.getLifePoint());
		System.out.println("Monster : "+this.monster.getLifePoint());
	}
	
	private int calculDamageForPlayer() {
		int damage = monster.getPower() - monster.getArmor();
		if (damage < 0) {
			return 0;
		} else {
			return damage;
		}
	}
	
	private int calculLifeRemainingForPlayer(int damage) {
		int lifePoint = player.getLifePoint() - damage;
		if (lifePoint < 0) {
			return 0;
		} else {
			return lifePoint;
		}
	}
}
