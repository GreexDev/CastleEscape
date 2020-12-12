package Items;

public abstract class ItemAbstract implements IItemAbstract{
	protected String name;
	protected String description;
	protected int space;
	protected boolean consumable;
	
	public ItemAbstract(String name, String description, int space, boolean consumable) {
		super();
		this.name = name;
		this.description = description;
		this.space = space;
		this.consumable = consumable;
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

	public int getSpace() {
		return space;
	}

	public void setSpace(int space) {
		this.space = space;
	}
	
	public boolean isConsumable() {
		return consumable;
	}

	public void setConsumable(boolean consumable) {
		this.consumable = consumable;
	}

	public String toString() {
		return "Item : "+this.name+"\n"
				+"Description : "+this.description+"\n"
				+"Necessary space : "+this.space;
	}	
}
