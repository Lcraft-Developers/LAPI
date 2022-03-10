package de.lcraft.api.minecraft.spigot.module.utils.inventory.item.slot;

public enum InventoryX {

	ONE(0), TWO(1), THREE(2), FOUR(3), FIVE(4), SIX(5), SEVEN(6), EIGHT(7), NINE(8);

	private final int size;

	InventoryX(int size) {
		this.size = size;
	}

	public int getSize() {
		return size;
	}

}
