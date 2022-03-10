package de.lcraft.api.minecraft.spigot.module.utils.inventory;

import de.lcraft.api.minecraft.spigot.module.manager.utils.listeners.ListenerManager;
import de.lcraft.api.minecraft.spigot.module.utils.inventory.item.InventoryItem;
import de.lcraft.api.minecraft.spigot.module.utils.inventory.item.slot.InventorySlot;
import de.lcraft.api.minecraft.spigot.module.utils.items.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import java.util.ArrayList;
import java.util.Objects;

public class InventoryHelper {

	public final boolean isAnyFreeSpaceLeft(Inventory inv) {
		return !Objects.isNull(getNextFreeSlot(inv));
	}

	public final ArrayList<InventorySlot> getAllFreeSlots(Inventory inv) {
		ArrayList<InventorySlot> slotSpaces = new ArrayList<>();
		BukkitInventoryValidator validator = new BukkitInventoryValidator();

		for(int i = 0; i < inv.getSize(); i++) {
			ItemStack item = inv.getItem(i);
			if(!validator.existsItemWithItemMetaAndDisplayName(item)) {
				slotSpaces.add(InventorySlot.getSlotSpaceBySpace(i));
			}
		}

		return slotSpaces;
	}
	public final InventorySlot getNextFreeSlot(Inventory inv) {
		InventorySlot minSpace = InventorySlot.SIX_NINE;
		for(InventorySlot space : getAllFreeSlots(inv)) {
			if(space.getSpace() < minSpace.getSpace()) {
				minSpace = space;
			}
		}
		return minSpace;
	}

	public final ArrayList<InventorySlot> getAllFreeSlots(de.lcraft.api.minecraft.spigot.module.utils.inventory.Inventory inv) {
		ArrayList<InventorySlot> slotSpaces = new ArrayList<>();
		BukkitInventoryValidator validator = new BukkitInventoryValidator();

		for(int i = 0; i < inv.getSize(); i++) {
			InventorySlot slot = InventorySlot.getSlotSpaceBySpace(i);
			if(inv.existsItemAtSlot(slot)) {
				InventoryItem item = inv.getItem(InventorySlot.getSlotSpaceBySpace(i));
				if(Objects.nonNull(item) && Objects.nonNull(item.getItem()) && Objects.nonNull(item.getItem().build())) {
					if(!validator.existsItemWithItemMetaAndDisplayName(item.getItem().build())) {
						slotSpaces.add(slot);
					}
				} else {
					slotSpaces.add(slot);
				}
			} else {
				slotSpaces.add(slot);
			}
		}

		return slotSpaces;
	}
	public final InventorySlot getNextFreeSlot(de.lcraft.api.minecraft.spigot.module.utils.inventory.Inventory inv) {
		InventorySlot minSpace = InventorySlot.SIX_NINE;
		for(InventorySlot space : getAllFreeSlots(inv)) {
			if(space.getSpace() < minSpace.getSpace()) {
				minSpace = space;
			}
		}
		return minSpace;
	}

	public final Inventory makePlaceholders(Inventory inv, ItemStack placeHolder) {
		for(InventorySlot space : getAllFreeSlots(inv)) {
			inv.setItem(space.getSpace(), placeHolder);
		}
		return inv;
	}
	public final Inventory makePlaceholders(Inventory inv, ListenerManager listenerManager, String invTitle) {
		return makePlaceholders(inv, new InventoryItem(listenerManager, invTitle, true, true, true, new ItemBuilder(listenerManager,Material.BLACK_STAINED_GLASS_PANE).setDisplayName("§7 ")).getItem().build());
	}


}
