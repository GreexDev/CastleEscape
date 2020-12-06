package Items;

public class Weapon extends ItemAbstract{
	private int power;

	public Weapon(String name, String description, int space, int power) {
		super(name, description, space);
		this.power = power;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	@Override
	public String toString() {
		return super.toString() + "\n"
				+"Power : "+this.power;
	}
	
}
