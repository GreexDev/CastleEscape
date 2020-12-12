package Entities;

import java.util.Vector;

import Items.Bag;
import Items.ItemAbstract;

public class Player extends Entity{
	private Bag bag;

	public Player(String name, String description, int lifePoint, int armor, int power,  Bag bag) {
		super(name, description, lifePoint, armor, power);
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

	@Override
	public String toString() {
		return "Player : " + super.toString();
	}
	
}
