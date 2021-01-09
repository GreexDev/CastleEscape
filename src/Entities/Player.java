package Entities;

import java.util.Vector;

import Items.*;

public class Player extends Entity{
	private Bag bag;
	private Weapon weapon;

	public Player(String name, String description, int lifePoint, int armor, int power,  Bag bag, Weapon weapon) {
		super(name, description, lifePoint, armor, power);
		this.weapon = weapon;
		this.bag = bag;
	}

	public Bag getBag() {
		return bag;
	}
	public Bag replaceBag(Bag bag) throws Exception {
		if(this.bag == null) {
			this.bag = bag;
			return null;
		} else {
			// Set Items to new bag
			bag.setItemList(this.bag.getItemList());
			// Empty the old bag
			this.bag.setItemList(new Vector<ItemAbstract>());
			Bag oldBag = this.bag;
			// Replace old bag by new bag
			this.bag = bag;
			return oldBag;
		}
	}
	
	public void changeWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
	
	public Weapon getWeapon() {
		return this.weapon;
	}

	@Override
	public String toString() {
		String string = "Player : " + super.toString();
		// If player has weapon
		if (weapon != null) {
			string = string+"Weapon power : "+weapon.getPower()+"\n";
		}
		return string;
	}
	
	// When a consumable item will affect player so consume is set to true
	public void addEffect(ItemAbstract item, boolean consume) throws Exception {
		// If item is an Item and is not consumable -> affect player now
		if(item.getClass() == Item.class && (!item.isConsumable() || consume)) {
			// Affect player
			switch(((Item) item).getEffect()) {
				case ADDHEALTH:
					this.lifePoint = this.lifePoint + ((Item) item).getEffectNumber();
					break;
				case SUBHEALTH:
					this.lifePoint = this.lifePoint - ((Item) item).getEffectNumber();
					break;
				case ADDPOWER:
					this.power = this.power + ((Item) item).getEffectNumber();
					break;
				case SUBPOWER:
					this.power = this.power - ((Item) item).getEffectNumber();
					break;
				case ADDARMOR:
					this.armor = this.armor + ((Item) item).getEffectNumber();
					break;
				case SUBARMOR:
					this.armor = this.armor - ((Item) item).getEffectNumber();
					break;
				case OPENDOOR:
				case NOTHING:
					// do Nothing
					break;
				default:
					throw new Exception("Wrong Effect.");
			}
		}
	}
	
	public void deleteEffect(ItemAbstract item) throws Exception {
		// If item is an Item and is not consumable
		if(item.getClass() == Item.class  && !item.isConsumable()) {
			switch(((Item) item).getEffect()) {
				case ADDHEALTH:
					this.lifePoint = this.lifePoint - ((Item) item).getEffectNumber();
					break;
				case SUBHEALTH:
					this.lifePoint = this.lifePoint + ((Item) item).getEffectNumber();
					break;
				case ADDPOWER:
					this.power = this.power - ((Item) item).getEffectNumber();
					break;
				case SUBPOWER:
					this.power = this.power + ((Item) item).getEffectNumber();
					break;
				case ADDARMOR:
					this.armor = this.armor - ((Item) item).getEffectNumber();
					break;
				case SUBARMOR:
					this.armor = this.armor + ((Item) item).getEffectNumber();
					break;
				case OPENDOOR:
				case NOTHING:
					// do Nothing
					break;
				default:
					throw new Exception("Wrong Effect.");
			}
		}
	}
	
}
