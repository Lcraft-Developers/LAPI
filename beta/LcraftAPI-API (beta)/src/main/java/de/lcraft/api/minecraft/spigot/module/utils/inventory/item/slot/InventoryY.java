package de.lcraft.api.minecraft.spigot.module.utils.inventory.item.slot;

public enum InventoryY {

	ONE(0), TWO(1), THREE(2), FOUR(3), FIVE(4), SIX(5);

	private final int size;

	InventoryY(int size) {
		this.size = size;
	}

	public int getSize() {
		return size;
	}

}
