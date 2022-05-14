package de.lcraft.api.minecraft.spigot.module.utils.inventory;

import de.lcraft.api.java_utils.language.LanguagesManager;
import de.lcraft.api.minecraft.spigot.utils.listeners.ListenerManager;
import de.lcraft.api.minecraft.spigot.module.utils.inventory.item.InventoryItem;
import de.lcraft.api.minecraft.spigot.module.utils.inventory.item.slot.InventorySlot;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class ListInventory extends Inventory {

	public ListInventory(ListenerManager listenerManager, LanguagesManager languagesManager, int height) {
		super(listenerManager, languagesManager, height);
	}

	public org.bukkit.inventory.Inventory getListPageInventory(String title, UUID player, int itemsAmountPerSite, int page, InventoryItem LAST_PAGE, InventoryItem NEXT_PAGE) {
		org.bukkit.inventory.Inventory inv = Bukkit.createInventory(null, getSize(), getListTitle(title, player, page, getMaxListPages(itemsAmountPerSite)));
		if(page > 1)
			setItem(LAST_PAGE, InventorySlot.getSlotSpaceBySpace(getSize() - 8));
		if(page < getMaxListPages(itemsAmountPerSite))
			setItem(NEXT_PAGE, InventorySlot.getSlotSpaceBySpace(getSize() - 1));
		HashMap<InventorySlot, InventoryItem> allItems = getAllItemsNeeded(itemsAmountPerSite, page);
		for(InventorySlot c : allItems.keySet()) {
			if(Objects.nonNull(c) && existsItemAtSlot(c)) {
				inv.setItem(c.getSpace(), allItems.get(c).getItem().build());
			}
		}
		return inv;
	}
	public final int getMaxListPages(int itemAmountPerSite) {
		int pagethings = 0;
		int pages = 1;
		for(int i = 0; i < getNormalItems().size(); i++) {
			pagethings++;
			if(pagethings > itemAmountPerSite) {
				pagethings = 1;
				pages = pages + 1;
			}
			continue;
		}
		return pages;
	}
	public final String getListTitle(String title, UUID player, int currentPage, int pages) {
		return getLanguagesManager().getIDLanguage(getLanguagesManager().getIDFromUUID(player)).translate(title + " §6%cpage%§7/§6%maxpage%").replace("%cpage%", currentPage + "").replace("%maxpage%", pages + "");
	}
	public final HashMap<InventorySlot, InventoryItem> getAllItemsNeeded(int itemsAmountPerSite, int page) {
		int pagethings = 0;
		int pages = 1;
		HashMap<InventorySlot, InventoryItem> items = new HashMap<>();
		for(InventorySlot slot : getNormalItems().keySet()) {
			InventoryItem item = getItem(slot);
			pagethings++;
			if(pagethings > itemsAmountPerSite) {
				pagethings = 0;
				pages = pages + 1;
			}
			if(pages == page) {
				items.put(slot,item);
			}
			continue;
		}
		return items;
	}

}
