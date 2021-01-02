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
						this.player.getBag().addItem(item);
						this.actualRoom.deleteItem(item);
					}
				} catch (Exception e) {
					throw new Exception("Wrong entry.");
				}
				break;
			}
		} while(choice != cancelValue);
		
	}

	private void useBagItem() {
		// TODO Auto-generated method stub
		
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
							this.useBagItem();
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
