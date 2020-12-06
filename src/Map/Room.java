package Map;

import java.util.HashMap;
import java.util.Vector;

import Entities.Monster;
import Items.ItemAbstract;

public class Room {
	private String description;
	private HashMap<String, Room> doorList;
	private Vector<ItemAbstract> itemList;
	private Monster monster;
	private boolean finish;
	
	public Room(String description, Monster monster, boolean finish) {
		super();
		this.description = description;
		this.doorList = new HashMap<String, Room>();
		this.itemList = new Vector<ItemAbstract>();
		this.monster = monster;
		this.finish = finish;
	}
	
	public Room() {
		super();
		this.description = "";
		this.doorList = new HashMap<String, Room>();
		this.itemList = new Vector<ItemAbstract>();
		this.monster = null;
		this.finish = false;
	}
	
	public void addDoor(String direction, Room room) throws Exception {
		// If door doesn't already exist
		if(doorList.get(direction) == null) {
			doorList.put(direction, room);
		} else {
			throw new Exception("Door already exists.");
		}
	}
	
	public Room getRoom(String direction) {
		return doorList.get(direction);
	}
	
	public void addItem(ItemAbstract itmAbs) {
		itemList.add(itmAbs);
	}
	
	public void deleteItem(ItemAbstract itmAbs) throws Exception {
		int idItem = itemList.indexOf(itmAbs);
		if(idItem != -1) {
			itemList.remove(idItem);
		} else {
			throw new Exception("Item doesn't exist.");
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public HashMap<String, Room> getDoorList() {
		return doorList;
	}

	public void setDoorList(HashMap<String, Room> doorList) {
		this.doorList = doorList;
	}
	
	public Monster getMonster() {
		return monster;
	}

	public void setMonster(Monster monster) {
		this.monster = monster;
	}

	public boolean isFinish() {
		return finish;
	}

	public void setFinish(boolean finish) {
		this.finish = finish;
	}

	@Override
	public String toString() {
		if(description != "") {
			return description;
		}
		String monster = "";
		String item = "";
		if(this.monster != null) {
			monster ="a " + this.monster.getName();
		}
		if(!itemList.isEmpty()) {
			for(int i = 0; i < itemList.size(); i++) {
				if(item != "") {
					item = item + ", ";
				}
				item = item + itemList.get(i).getName();
			}
		}
		if(monster != "") {
			if(item != "") {
				return "Room contain "+monster+" and "+item+".";
			} else {
				return "Room contain "+monster+".";
			}
		} else {
			if(item != "") {
				return "Room contain "+item+".";
			} else {
				return "Room is empty.";
			}
		}
	}	
}