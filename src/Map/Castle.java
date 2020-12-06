package Map;

import java.util.Vector;

public class Castle {
	private Vector<Room> roomList;
	private String name;
	private String difficulty;
	
	public Castle(Vector<Room> roomList, String name, String difficulty) {
		super();
		this.roomList = roomList;
		this.name = name;
		this.difficulty = difficulty;
	}

	public Vector<Room> getRoomList() {
		return roomList;
	}

	public void setRoomList(Vector<Room> roomList) {
		this.roomList = roomList;
	}
	
	public int getCountRoom() {
		return roomList.size();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	
	public String toString() {
		return "Castle : "+name+"\n"
				+"Number of rooms : "+getCountRoom()+"\n"
				+"Difficulty : "+difficulty;
	}
}
