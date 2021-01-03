package Game;

import Map.*;

import java.util.Scanner;
import java.util.Vector;

import Entities.*;
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
					} else { // If simple item
						// Take item
						this.player.getBag().addItem(item);
						this.actualRoom.deleteItem(item);
						// Item affect player
						this.player.addEffect(item, false);
					}
				} catch (Exception e) {
					throw new Exception("Wrong entry.");
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
				try {
					// Get Item
					ItemAbstract item = listBagItem.get(choice - 1);
					// use Bag item
					this.useBagItem(item);
				} catch (Exception e) {
					throw new Exception("Wrong entry.");
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
					this.actualRoom.addItem(item);
					this.player.getBag().deleteItem(item);
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
					this.actualRoom.addItem(item);
					this.player.getBag().deleteItem(item);
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
					this.actualRoom.addItem(item);
					this.player.getBag().deleteItem(item);
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

	private void changeRoom() {
		// TODO Auto-generated method stub
		
	}

	private void fightMonster() {
		// TODO Auto-generated method stub
		
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
