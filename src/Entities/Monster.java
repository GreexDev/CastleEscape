package Entities;

public class Monster extends Entity{
	public Monster(String name, String description, int lifePoint) {
		super(name, description, lifePoint);
	}

	@Override
	public String toString() {
		return "Monster : " + super.toString();
	}
	
}
