package de.lcraft.api.minecraft.spigot.util.inventory;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class InventorySlot {

	private int x,
	            y;

	public InventorySlot(int slot) {
		x = convertToX(slot);
		y = convertToY(slot);
	}
	public InventorySlot(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public InventorySlot(Inventory inv) {
		this.x = convertToX(getNextFreeSlot(inv));
		this.y = convertToY(getNextFreeSlot(inv));
	}

	public Inventory addItem(Inventory inv, ItemStack item) {
		if(isAnyFreeSpaceLeft(inv)) {
			inv.setItem(getNextFreeSlot(inv), item);
		}
		return inv;
	}
	public Inventory setItem(Inventory inv, ItemStack item) {
		inv.setItem(getSlot(), item);
		return inv;
	}

	public boolean isAnyFreeSpaceLeft(Inventory inv) {
		if(getNextFreeSlot(inv) == -1) return false;
		return true;
	}
	public int getNextFreeSlot(Inventory inv) {
		for(int i = 0; i < inv.getSize(); i++) {
			if(inv.getItem(i) == null || (inv.getItem(i).getType() == Material.AIR || inv.getItem(i) instanceof InventoryHolder)) {
				return i;
			}
		}
		return -1;
	}

	public int convertToX(int slot) {
		return ((slot - 1) - (convertToY(slot) * 9));
	}
	public int convertToY(int slot) {
		int y = 1;
		for(int c = 1; c < slot; c+=9) {
			y++;
		}
		return y;
	}
	public int convertToSlot(int x, int y) {
		return (x + (y * 9)) - 1;
	}

	public int getSlot() {
		return convertToSlot(x,y);
	}
	public int getX() {
		return convertToX(getSlot());
	}
	public int getY() {
		return convertToY(getSlot());
	}

}
