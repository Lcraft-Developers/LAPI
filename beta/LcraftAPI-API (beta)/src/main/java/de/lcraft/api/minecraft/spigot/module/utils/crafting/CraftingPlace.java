package de.lcraft.api.minecraft.spigot.module.utils.crafting;

public enum CraftingPlace {

	/**
	 * The place in the crafting table
	 * FIRST   | SECOND | THIRD
	 * FOURTH  | FIFTH  | SIXTH | -->> RESULT
	 * SEVENTH | EIGHT  | NINTH
	 */

	ONE_ONE('A'), ONE_TWO('B'), ONE_THREE('C'),
	TWO_ONE('D'), TWO_TWO('E'), TWO_THREE('F'),
	THREE_ONE('G'), THREE_TWO('H'), THREE_THREE('I');

	private final char key;

	CraftingPlace(char key) {
		this.key = key;
	}
	public char getKey() {
		return key;
	}

}