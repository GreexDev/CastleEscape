package Items;

public class Item extends ItemAbstract{
	public enum Effect {ADDHEALTH,SUBHEALTH,ADDPOWER,SUBPOWER,ADDARMOR,SUBARMOR,OPENDOOR,NOTHING};
	private Effect effect;
	private int effectNumber;
	
	public Item(String name, String description, int space, boolean consumable, Effect effect, int effectNumber) {
		super(name, description, space, consumable);
		this.effect = effect;
		this.effectNumber = effectNumber;
	}

	public Effect getEffect() {
		return effect;
	}

	public void setEffect(Effect effect) {
		this.effect = effect;
	}

	public int getEffectNumber() {
		return effectNumber;
	}

	public void setEffectNumber(int effectNumber) {
		this.effectNumber = effectNumber;
	}
	
}
