package Entities;

import Items.Bag;

public class Player extends Entity{
	private Bag bag;

	public Player(String name, String description, int lifePoint, Bag bag) {
		super(name, description, lifePoint);
		this.bag = bag;
	}

	public Bag getBag() {
		return bag;
	}

	@Override
	public String toString() {
		return "Player : " + super.toString();
	}
	
}
