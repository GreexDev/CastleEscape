package Items;

import java.util.Vector;

public class Bag extends ItemAbstract{
	private Vector<ItemAbstract> itemAbstractList;
	private int maxPlace;

	public Bag(int maxPlace) {
		super("Sac", "Sac de l'explorateur", maxPlace);
		this.itemAbstractList = new Vector<ItemAbstract>();
		this.maxPlace = maxPlace;
	}

	public int getMaxPlace() {
		return maxPlace;
	}

	public void setMaxPlace(int maxPlace) {
		this.maxPlace = maxPlace;
	}
	
	public Vector<ItemAbstract> getBagList(){
		return itemAbstractList;
	}
	
	public void addItem(ItemAbstract item) throws Exception {
		int placeOccupied = this.getPlaceOccupied();
		// If not enough place
		if(maxPlace < placeOccupied+item.getSpace()) {
			throw new Exception("Not enough place in bag.");
		}
		itemAbstractList.add(item);
	}
	
	public void deleteItem(ItemAbstract item) throws Exception {
		// If item isn't in bag
		if(itemAbstractList.contains(item)) {
			throw new Exception("Bag does not contain this item.");
		}
		itemAbstractList.remove(item);
	}
	
	public int getPlaceOccupied() {
		int placeOccupied = 0;
		for(int i = 0; i < itemAbstractList.size(); i++) {
			placeOccupied += itemAbstractList.elementAt(i).getSpace();
		}
		return placeOccupied;
	}
}
