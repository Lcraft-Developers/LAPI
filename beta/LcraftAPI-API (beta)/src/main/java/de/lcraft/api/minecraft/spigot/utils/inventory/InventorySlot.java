package de.lcraft.api.minecraft.spigot.utils.inventory;

import de.lcraft.api.minecraft.spigot.utils.items.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class InventorySlot {

	@Getter
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

	public final Inventory addItem(Inventory inv, ItemBuilder item) {
		if(isAnyFreeSpaceLeft(inv)) {
			inv.setItem(getNextFreeSlot(inv), item.build());
		}
		return inv;
	}
	public final Inventory setItem(Inventory inv, ItemBuilder item) {
		inv.setItem(getSlot(), item.build());
		return inv;
	}

	public final boolean isAnyFreeSpaceLeft(Inventory inv) {
		if(getNextFreeSlot(inv) == -1) return false;
		return true;
	}
	public final int getNextFreeSlot(Inventory inv) {
		for(int i = 0; i < inv.getSize(); i++) {
			if(inv.getItem(i) == null || (inv.getItem(i).getType() == Material.AIR || inv.getItem(i) instanceof InventoryHolder)) {
				return i;
			}
			continue;
		}
		return -1;
	}

	public final int convertToX(int slot) {
		return ((slot - 1) - (convertToY(slot) * 9));
	}
	public final int convertToY(int slot) {
		int y = 1;
		for(int c = 1; c < slot; c+=9) {
			y++;
		}
		return y;
	}
	public final int convertToSlot(int x, int y) {
		return (x + (y * 9)) - 1;
	}

	public final int getSlot() {
		return convertToSlot(x,y);
	}

}
