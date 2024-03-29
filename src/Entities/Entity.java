package Entities;

public abstract class Entity implements IEntity{
	protected String name;
	protected String description;
	protected int lifePoint;
	protected int armor;
	protected int power;
	
	public Entity(String name, String description, int lifePoint, int armor, int power) {
		super();
		this.name = name;
		this.description = description;
		this.lifePoint = lifePoint;
		this.armor = armor;
		this.power = power;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getLifePoint() {
		return lifePoint;
	}
	public void setLifePoint(int lifePoint) {
		this.lifePoint = lifePoint;
	}
	public int getArmor() {
		return armor;
	}
	public void setArmor(int armor) {
		this.armor = armor;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}

	@Override
	public String toString() {
		return name + "\n"
	+"Description : " + description + "\n"
	+"Life point : " + lifePoint + "\n"
	+"Resistance : " + armor + "\n"
	+"Power : " + power + "\n";
	}
}
