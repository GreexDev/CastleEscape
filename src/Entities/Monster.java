package Entities;

public class Monster extends Entity{
	public Monster(String name, String description, int lifePoint, int armor, int power) {
		super(name, description, lifePoint, armor, power);
	}

	@Override
	public String toString() {
		return "Monster : " + super.toString();
	}
	
}
