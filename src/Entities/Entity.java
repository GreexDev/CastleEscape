package Entities;

public abstract class Entity implements IEntity{
	protected String name;
	protected String description;
	protected int lifePoint;
	
	public Entity(String name, String description, int lifePoint) {
		super();
		this.name = name;
		this.description = description;
		this.lifePoint = lifePoint;
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

	@Override
	public String toString() {
		return name + "\n"
	+"Description : " + description + "\n"
	+"Life point : " + lifePoint;
	}
}
