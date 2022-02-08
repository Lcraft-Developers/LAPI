package de.lcraft.api.minecraft.spigot.utils.inventory.item.slot;

import java.util.Objects;

public enum InventorySlotSpace {

	ONE_ONE(InventoryX.ONE, InventoryY.ONE),
	ONE_TWO(InventoryX.TWO, InventoryY.ONE),
	ONE_THREE(InventoryX.THREE, InventoryY.ONE),
	ONE_FOUR(InventoryX.FOUR, InventoryY.ONE),
	ONE_FIVE(InventoryX.FIVE, InventoryY.ONE),
	ONE_SIX(InventoryX.SIX, InventoryY.ONE),
	ONE_SEVEN(InventoryX.SEVEN, InventoryY.ONE),
	ONE_EIGHT(InventoryX.EIGHT, InventoryY.ONE),
	ONE_NINE(InventoryX.NINE, InventoryY.ONE),

	TWO_ONE(InventoryX.ONE, InventoryY.TWO),
	TWO_TWO(InventoryX.TWO, InventoryY.TWO),
	TWO_THREE(InventoryX.THREE, InventoryY.TWO),
	TWO_FOUR(InventoryX.FOUR, InventoryY.TWO),
	TWO_FIVE(InventoryX.FIVE, InventoryY.TWO),
	TWO_SIX(InventoryX.SIX, InventoryY.TWO),
	TWO_SEVEN(InventoryX.SEVEN, InventoryY.TWO),
	TWO_EIGHT(InventoryX.EIGHT, InventoryY.TWO),
	TWO_NINE(InventoryX.NINE, InventoryY.TWO),

	THREE_ONE(InventoryX.ONE, InventoryY.THREE),
	THREE_TWO(InventoryX.TWO, InventoryY.THREE),
	THREE_THREE(InventoryX.THREE, InventoryY.THREE),
	THREE_FOUR(InventoryX.FOUR, InventoryY.THREE),
	THREE_FIVE(InventoryX.FIVE, InventoryY.THREE),
	THREE_SIX(InventoryX.SIX, InventoryY.THREE),
	THREE_SEVEN(InventoryX.SEVEN, InventoryY.THREE),
	THREE_EIGHT(InventoryX.EIGHT, InventoryY.THREE),
	THREE_NINE(InventoryX.NINE, InventoryY.THREE),

	FOUR_ONE(InventoryX.ONE, InventoryY.FOUR),
	FOUR_TWO(InventoryX.TWO, InventoryY.FOUR),
	FOUR_THREE(InventoryX.THREE, InventoryY.FOUR),
	FOUR_FOUR(InventoryX.FOUR, InventoryY.FOUR),
	FOUR_FIVE(InventoryX.FIVE, InventoryY.FOUR),
	FOUR_SIX(InventoryX.SIX, InventoryY.FOUR),
	FOUR_SEVEN(InventoryX.SEVEN, InventoryY.FOUR),
	FOUR_EIGHT(InventoryX.EIGHT, InventoryY.FOUR),
	FOUR_NINE(InventoryX.NINE, InventoryY.FOUR),

	FIVE_ONE(InventoryX.ONE, InventoryY.FIVE),
	FIVE_TWO(InventoryX.TWO, InventoryY.FIVE),
	FIVE_THREE(InventoryX.THREE, InventoryY.FIVE),
	FIVE_FOUR(InventoryX.FOUR, InventoryY.FIVE),
	FIVE_FIVE(InventoryX.FIVE, InventoryY.FIVE),
	FIVE_SIX(InventoryX.SIX, InventoryY.FIVE),
	FIVE_SEVEN(InventoryX.SEVEN, InventoryY.FIVE),
	FIVE_EIGHT(InventoryX.EIGHT, InventoryY.FIVE),
	FIVE_NINE(InventoryX.NINE, InventoryY.FIVE),

	SIX_ONE(InventoryX.ONE, InventoryY.SIX),
	SIX_TWO(InventoryX.TWO, InventoryY.SIX),
	SIX_THREE(InventoryX.THREE, InventoryY.SIX),
	SIX_FOUR(InventoryX.FOUR, InventoryY.SIX),
	SIX_FIVE(InventoryX.FIVE, InventoryY.SIX),
	SIX_SIX(InventoryX.SIX, InventoryY.SIX),
	SIX_SEVEN(InventoryX.SEVEN, InventoryY.SIX),
	SIX_EIGHT(InventoryX.EIGHT, InventoryY.SIX),
	SIX_NINE(InventoryX.NINE, InventoryY.SIX),
	;

	private InventoryX x;
	private InventoryY y;

	InventorySlotSpace(InventoryX x, InventoryY y) {
		this.x = x;
		this.y = y;
	}

	public InventoryX getX() {
		return x;
	}
	public InventoryY getY() {
		return y;
	}
	public int getSpace() {
		int space = getY().getSize() * 9;
		space = space + getX().getSize();
		return space;
	}
	public String getID() {
		return getX().toString() + "!.#.!" + getY().toString();
	}

	public static InventorySlotSpace getSlotSpaceByXAndY(InventoryX x, InventoryY y) {
		for(InventorySlotSpace current : InventorySlotSpace.values()) {
			if(Objects.nonNull(current) && current.getX().equals(x) && current.getY().equals(y)) {
				return current;
			}
		}
		return null;
	}
	public static InventorySlotSpace getSlotSpaceBySpace(int space) {
		for(InventorySlotSpace current : InventorySlotSpace.values()) {
			if(Objects.nonNull(current) && current.getSpace() == space) {
				return current;
			}
		}
		return null;
	}

}
