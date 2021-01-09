package Game;

import Map.*;

import java.util.Map;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import Entities.*;
import Fight.Fight;
import Items.*;

public class Game implements IGame{
	private Castle castle;
	private Player player;
	private Room actualRoom;
	private Scanner scanner;

	@Override
	public void initialize() {
		Initialize init = new Initialize();
		try {
			// Create vars
			this.castle = init.createCastle();
			this.player = init.createPlayer("Lonk", "Glubu slayer", 20, 0, 1, null, null);
			// Set 1st room
			this.actualRoom = this.castle.getRoomList().firstElement();
			// To get entries
			this.scanner = new Scanner(System.in);
		} catch(Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void start() {
		System.out.println("Welcome to the "+this.castle.getName()+" !");
		// Start Loop
		while(! this.actualRoom.isFinish()) {
			System.out.println("--------------------------------------");
			System.out.println(this.actualRoom);
			this.makeChoice();
		}
		
	}

	@Override
	public void end() {
		System.out.println("Thanks for playing ! See you next time !");
		
	}

	private void roomListItem() throws Exception {
		int choice = 0;
		int cancelValue = 0;
		do {
			// Get items
			Vector<ItemAbstract> listItem = this.actualRoom.getItemList();
			cancelValue = listItem.size() + 1;
			
			// Show items
			System.out.println("Choose an item : ");
			for(int i = 0; i < listItem.size(); i++) {
				System.out.println((i+1)+" - "+listItem.get(i).getName());
			}
			System.out.println(cancelValue+" - Cancel");
			
			// Select items
			System.out.println("Enter a choice :");
			choice = this.scanner.nextInt();
			
			// If choice is not cancel
			if(choice != cancelValue) {
				try {
					// Get Item
					ItemAbstract item = listItem.get(choice - 1);
					// If item is a bag
					if (item.getClass() == Bag.class) {
						// Replace bag
						Bag oldBag = this.player.replaceBag((Bag)item);
						this.actualRoom.deleteItem(item);
						// If oldBag is not null
						if (oldBag != null) {
							this.actualRoom.addItem(oldBag);
						}
					} else { // If simple items
						// If player has a bag
						if(this.player.getBag() != null) {
							// Take item
							this.player.getBag().addItem(item);
							this.actualRoom.deleteItem(item);
							// Item affect player
							this.player.addEffect(item, false);
						} else {
							throw new Exception("No bag to take this item.");
						}
					}
				} catch (Exception e) {
					// Exceptionnal error
					if (e.getMessage() == "No bag to take this item.") {
						throw new Exception(e.getMessage());
					} else { // Usual error
						throw new Exception("Wrong entry.");
					}
				}
				break;
			}
		} while(choice != cancelValue);
		
	}
	
	

	private void selectBagItem() throws Exception {
		int choice = 0;
		int cancelValue = 0;
		do {
			// Get items
			Vector<ItemAbstract> listBagItem = this.player.getBag().getItemList();
			cancelValue = listBagItem.size() + 1;
			
			// Show items
			System.out.println("Choose an item : ");
			for(int i = 0; i < listBagItem.size(); i++) {
				System.out.println((i+1)+" - "+listBagItem.get(i).getName());
			}
			System.out.println(cancelValue+" - Cancel");
			
			// Select items
			System.out.println("Enter a choice :");
			choice = this.scanner.nextInt();
			
			// If choice is not cancel
			if(choice != cancelValue) {
				ItemAbstract item = null;
				try {
					// Get Item
					item = listBagItem.get(choice - 1);
				} catch (Exception e) {
					throw new Exception("Wrong entry.");
				}
				if (item != null) {
					// use Bag item
					this.useBagItem(item);
				}
				break;
			}
		} while(choice != cancelValue);
	}
	
	private void useBagItem(ItemAbstract item) throws Exception {
		// If item is an Item and consumable
		if (item.getClass() == Item.class && item.isConsumable()) {
			// Consume, put down, description, cancel
			// Show Choices
			System.out.println("What do you want to do with "+item.getName()+" ?");
			System.out.print("1 - Consume this item\n"
			+"2 - Put down this item\n"
			+"3 - Get the description of this item\n"
			+"4 - Cancel\n");
			// Select Choice
			System.out.println("Enter a choice :");
			int choice = this.scanner.nextInt();
			switch(choice) {
				case 1:
					// Add effect
					this.player.addEffect(item, true);
					// Delete item
					this.player.getBag().deleteItem(item);
					break;
				case 2:
					// Put down item
					this.player.getBag().deleteItem(item);
					this.actualRoom.addItem(item);
					break;
				case 3:
					System.out.println("Description of "+item.getName());
					System.out.println(item.getDescription());
					break;
				case 4:
					// Do nothing
					break;
				default:
					System.out.println("Wrong choice, try again.");
			}
		} else if (item.getClass() == Weapon.class && ((Weapon) item) != this.player.getWeapon()) { // If item is a Weapon and is not the one that player use
			// Change Weapon, put down, description, cancel
			// Show Choices
			System.out.println("What do you want to do with "+item.getName()+" ?");
			System.out.print("1 - Use this item as weapon\n"
			+"2 - Put down this item\n"
			+"3 - Get the description of this item\n"
			+"4 - Cancel\n");
			// Select Choice
			System.out.println("Enter a choice :");
			int choice = this.scanner.nextInt();
			switch(choice) {
				case 1:
					// Change weapon
					this.player.changeWeapon((Weapon) item);
					break;
				case 2:
					// Put down item
					System.out.println("debug");
					this.player.getBag().deleteItem(item);
					this.actualRoom.addItem(item);
					break;
				case 3:
					System.out.println("Description of "+item.getName());
					System.out.println(item.getDescription());
					break;
				case 4:
					// Do nothing
					break;
				default:
					System.out.println("Wrong choice, try again.");
			}
		} else {
			// Put down, description, cancel
			// Show Choices
			System.out.println("What do you want to do with "+item.getName()+" ?");
			System.out.print("1 - Put down this item\n"
			+"2 - Get the description of this item\n"
			+"3 - Cancel\n");
			// Select Choice
			System.out.println("Enter a choice :");
			int choice = this.scanner.nextInt();
			switch(choice) {
				case 1:
					// Delete effect on player
					this.player.deleteEffect(item);
					// Put down item
					this.player.getBag().deleteItem(item);
					this.actualRoom.addItem(item);
					break;
				case 2:
					System.out.println("Description of "+item.getName());
					System.out.println(item.getDescription());
					break;
				case 3:
					// Do nothing
					break;
				default:
					System.out.println("Wrong choice, try again.");
			}
		}
	}

	private void changeRoom() throws Exception {
		int choice = 0;
		int cancelValue = 0;
		String[] directionList = {"south", "west", "north", "east"};
		do {
			// Get doorList
			Map<String, Room> doorList = this.actualRoom.getDoorList();
			cancelValue = doorList.size() + 1;
			
			Vector<String> possibleDirections = new Vector<String>();
			int j = 0;
			
			// Get possible directions
			System.out.println("Choose a door : ");
			for(int i = 0; i < directionList.length; i++) {
				// If direction is possible -> add to possibleDirection and increment j
				if(doorList.containsKey(directionList[i])){
					possibleDirections.add(directionList[i]);
					j++;
					// Show direction
					System.out.println(j+" - Door to the "+directionList[i]);
				}
			}
			System.out.println(cancelValue+" - Cancel");
			
			// Select door
			System.out.println("Enter a choice :");
			choice = this.scanner.nextInt();
			
			// If choice is not cancel
			if(choice != cancelValue) {
				try {
					// If choice is not in array
					if ((!(choice-1 < possibleDirections.size())) || (!(choice-1 >= 0))) {
						throw new Exception("Out of array."); // Will show "Wrong entry."
					}
					// Get door
					Room room = doorList.get(possibleDirections.get(choice-1));
					// If finish -> need key
					if (room.isFinish()) {
						// If player has key
						if (this.player.getBag().hasKey()) {
							// Show player that he won the game
							System.out.println("Congrats ! You have exit the castle !");
							System.out.println("Game is ended.");
							System.out.println("Game made by Eric Rodriguez.");
							// Change room
							this.actualRoom = room;
						} else {
							System.out.println("Impossible action : You need a key to open this door.");
						}
					} else {
						// Change room
						this.actualRoom = room;
					}
				} catch (Exception e) {
					throw new Exception("Wrong entry.");
				}
				break;
			}
		} while(choice != cancelValue);
		
	}

	private void fightMonster() {
		// Show the monster that the player is going to fight
		System.out.println("You are going to fight the "+this.actualRoom.getMonster());
		System.out.println("Stats of "+this.player);
		// Warn player that he is going to play a minigame
		try {
			for(int i = 0; i < 5; i++) {
			System.out.println("Warning ! A minigame will start in "+(5-i)+" seconds !");
			TimeUnit.SECONDS.sleep(1);
			}
		} catch (InterruptedException e) {
			// Do nothing
		}
		// Excecute fight
		Fight fight = new Fight(this.actualRoom.getMonster(), this.player, this.scanner);
		boolean win = fight.run();
		// If player win the fight
		if (win) {
			// Monster died
			this.actualRoom.setMonster(null);
			System.out.println("You won the fight ! You can leave the room !");
		} else {
			// Actual room is set finished to end the game
			this.actualRoom.setFinish(true);
			System.out.println("You lost the fight ! Too bad, maybe next time !");
		}
		
	}

	private void makeChoice() {		
		boolean wrongChoice = true;
		while(wrongChoice) {
			try {
				// Show Choices
				System.out.println("Choose an action :");
				System.out.print("1 - Take an item\n"
				+"2 - Use an item in your bag\n"
				+"3 - Attack the monster\n"
				+"4 - Change room\n");
				
				// Select Choice
				System.out.println("Enter a choice :");
				int choice = this.scanner.nextInt();
				switch(choice) {
					case 1:
						// If no items
						if (this.actualRoom.getItemList().size() == 0) {
							throw new Exception("There is no item in this room.");
						} else { // Select an item in the room
							wrongChoice = false;
							this.roomListItem();
						}
						break;
					case 2:
						// If no bag
						if (this.player.getBag() == null) {
							throw new Exception("You don't have a bag.");
						} else if (this.player.getBag().getItemList().size() == 0) { // If no items in bag
							throw new Exception("Your bag is empty.");
						} else { // Use an item in the bag
							wrongChoice = false;
							this.selectBagItem();
						}
						break;
					case 3:
						// If no monster in room
						if (this.actualRoom.getMonster() == null) {
							throw new Exception("There is no monster in the room.");
						} else { // Fight the monster
							this.fightMonster();
							wrongChoice = false;
						}
						break;
					case 4:
						// If monster in the room
						if(this.actualRoom.getMonster() != null) {
							throw new Exception("A monster is preventing you from going out of the room.");
						} else {
							wrongChoice = false;
							this.changeRoom();
						}
						break;
					default:
						System.out.println("Wrong choice, try again.");
				}
			} catch(Exception e) {
				System.out.println("Impossible action : "+e.getMessage());
			}
		}
	}
}
