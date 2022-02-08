package de.lcraft.api.minecraft.spigot.utils.inventory.item;

import de.lcraft.api.minecraft.spigot.utils.inventory.item.slot.InventorySlotSpace;
import de.lcraft.api.minecraft.spigot.utils.inventory.item.slot.InventoryX;
import de.lcraft.api.minecraft.spigot.utils.inventory.item.slot.InventoryY;
import de.lcraft.api.minecraft.spigot.utils.items.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class InventorySlot {

	private InventorySlotSpace slotSpace;

	public InventorySlot(InventoryX x, InventoryY y) {
		this.slotSpace = InventorySlotSpace.getSlotSpaceByXAndY(x,y);
	}

	public final Inventory setItem(Inventory inv, ItemBuilder item) {
		inv.setItem(convertToSlot(), item.build());
		return inv;
	}

	public final boolean isAnyFreeSpaceLeft(Inventory inv) {
		if(Objects.isNull(getNextFreeSlot(inv))) return false;
		return true;
	}
	public final InventorySlotSpace getNextFreeSlotAndReplacePlaceholder(Inventory inv, Material placeholder) {
		InventorySlotSpace space = null;
		for(int i = 0; i < inv.getSize(); i++) {
			ItemStack item = inv.getItem(i);
			if(Objects.isNull(item)) {
				space = InventorySlotSpace.getSlotSpaceBySpace(i);
			} else if(Objects.isNull(item.getType())) {
				space = InventorySlotSpace.getSlotSpaceBySpace(i);
			} else if(item.getType() == Material.AIR || item instanceof InventoryHolder) {
				space = InventorySlotSpace.getSlotSpaceBySpace(i);
			} else if(Objects.nonNull(placeholder) && item.equals(placeholder)) {
				space = InventorySlotSpace.getSlotSpaceBySpace(i);
			}
			continue;
		}
		return space;
	}
	public final InventorySlotSpace getNextFreeSlot(Inventory inv) {
		return getNextFreeSlotAndReplacePlaceholder(inv, null);
	}

	public final int convertToSlot() {
		return getSlotSpace().getSpace();
	}
	public InventoryX getX() {
		return getSlotSpace().getX();
	}
	public InventoryY getY() {
		return getSlotSpace().getY();
	}
	public InventorySlotSpace getSlotSpace() {
		return slotSpace;
	}

}
