package Game;

import Map.*;
import Items.*;
import static Items.Item.Effect.*;

import java.util.Vector;

import Entities.*;

public class Initialize {
	public Castle createCastle() throws Exception {
		Vector<Room> roomList = this.createRoomList();
		return new Castle(roomList, "Slimy Castle", "Easy");
	}
	
	private Vector<Room> createRoomList() throws Exception{
		// Create Rooms
		Room r1 = new Room("Entrance of the castle.\n");
		Room r2 = new Room();
		Room r3 = new Room();
		Room r4 = new Room();
		Room r5 = new Room();
		Room r6 = new Room();
		Room r7 = new Room();
		Room r8 = new Room();
		Room r9 = new Room();
		Room r10 = new Room();
		Room r11 = new Room();
		Room r12 = new Room();
		Room r13 = new Room();
		Room r14 = new Room("Boss room and exit.");
		Room r15 = new Room("EXIT");
		r15.setFinish(true);
		
		// Link Rooms
		r1.addDoor("east", r2);
		r1.addDoor("south", r4);
		r2.addDoor("west", r1);
		r2.addDoor("south", r5);
		r3.addDoor("south", r7);
		r4.addDoor("north", r1);
		r4.addDoor("south", r8);
		r5.addDoor("north", r2);
		r5.addDoor("east", r6);
		r6.addDoor("west", r5);
		r6.addDoor("south", r10);
		r7.addDoor("north", r3);
		r7.addDoor("south", r11);
		r8.addDoor("north", r4);
		r8.addDoor("east", r9);
		r8.addDoor("south", r12);
		r9.addDoor("west", r8);
		r10.addDoor("north", r6);
		r10.addDoor("east", r11);
		r10.addDoor("south", r13);
		r11.addDoor("north", r7);
		r11.addDoor("west", r10);
		r11.addDoor("south", r14);
		r12.addDoor("north", r8);
		r13.addDoor("north", r10);
		r14.addDoor("north", r11);
		r14.addDoor("east", r15);
		
		// Add items
		Bag bag = new Bag(15);
		r1.addItem(bag);
		Weapon knife = new Weapon("Knife", "Looks perfect for a toast.",2,3);
		r4.addItem(knife);
		Weapon sword = new Weapon("Sword", "Seems perfect for slitting the throats of your enemies.",5,9);
		r5.addItem(sword);
		Item potion1 = new Item("Potion of life", "Increase life of 7.",1,true,ADDHEALTH,7);
		r9.addItem(potion1);
		Item potion2 = new Item("Potion of resistance", "Increase armor of 4.",1,true,ADDARMOR,4);
		r13.addItem(potion2);
		Item shield = new Item("Shield", "Increase armor of 8.",5,false,ADDARMOR,8);
		r12.addItem(shield);
		Item key = new Item("Key", "Open the exit of the castle.", 1,false,OPENDOOR,0);
		r3.addItem(key);
		
		// Add monster
		Monster monster = new Monster("Glubu", "A slimy monster.",12,0,2);
		r5.setMonster(monster);
		Monster boss = new Monster("Glubu King", "King of slimy monsters, he is fierce and cruel.",25,5,6);
		r14.setMonster(boss);
		
		// Add Rooms to list
		Vector<Room> roomList = new Vector<Room>();
		roomList.add(r1);
		roomList.add(r2);
		roomList.add(r3);
		roomList.add(r4);
		roomList.add(r5);
		roomList.add(r6);
		roomList.add(r7);
		roomList.add(r8);
		roomList.add(r9);
		roomList.add(r10);
		roomList.add(r11);
		roomList.add(r12);
		roomList.add(r13);
		roomList.add(r14);
		roomList.add(r15);
		
		return roomList;
	}
	
	public Player createPlayer(String name, String description, int lifePoint, int armor, int power,  Bag bag, Weapon weapon) {
		return new Player(name, description, lifePoint, armor, power, bag, weapon);
	}
}
