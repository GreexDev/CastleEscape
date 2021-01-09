package Map;

import java.util.HashMap;
import java.util.Vector;

import Entities.Monster;
import Items.ItemAbstract;

public class Room {
	private int id;
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
	
	public Room(String description, int id) {
		super();
		this.description = description;
		this.id = id;
		this.doorList = new HashMap<String, Room>();
		this.itemList = new Vector<ItemAbstract>();
		this.monster = null;
		this.finish = false;
	}
	public Room(int id) {
		super();
		this.id = id;
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
	
	public Vector<ItemAbstract> getItemList(){
		return this.itemList;
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
	
	public int getId() {
		return id;
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
				return description+"The room contains "+monster+" and "+item+".";
			} else {
				return description+"The room contains "+monster+".";
			}
		} else {
			if(item != "") {
				return description+"The room contains "+item+".";
			} else {
				return description+"The room is empty.";
			}
		}
	}	
}