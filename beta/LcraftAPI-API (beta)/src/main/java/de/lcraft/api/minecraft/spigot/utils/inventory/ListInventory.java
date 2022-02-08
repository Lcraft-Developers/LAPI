package de.lcraft.api.minecraft.spigot.utils.inventory;

import de.lcraft.api.minecraft.spigot.manager.utils.language.LanguagesManager;
import de.lcraft.api.minecraft.spigot.manager.utils.listeners.ListenerManager;
import de.lcraft.api.minecraft.spigot.utils.inventory.item.InventoryItem;
import org.bukkit.Bukkit;
import java.util.ArrayList;
import java.util.UUID;

public class ListInventory extends Inventory {

	public ListInventory(ListenerManager listenerManager, LanguagesManager languagesManager, int height) {
		super(listenerManager, languagesManager, height);
	}

	public org.bukkit.inventory.Inventory getListPageInventory(String title, UUID player, int itemsAmountPerSite, int page, InventoryItem LAST_PAGE, InventoryItem NEXT_PAGE) {
		org.bukkit.inventory.Inventory inv = Bukkit.createInventory(null, getSize(), getListTitle(title, player, page, getMaxListPages(itemsAmountPerSite)));
		for(InventoryItem c : getAllItemsNeeded(itemsAmountPerSite, page)) {
			inv = c.getSlot().setItem(inv, c.getItem());
		}
		if(page > 1)
			inv = LAST_PAGE.getSlot().setItem(inv, LAST_PAGE.getItem());
		if(page < getMaxListPages(itemsAmountPerSite))
			inv = NEXT_PAGE.getSlot().setItem(inv, NEXT_PAGE.getItem());
		return inv;
	}
	public final int getMaxListPages(int itemAmountPerSite) {
		int pagethings = 0;
		int pages = 0;
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
	public final ArrayList<InventoryItem> getAllItemsNeeded(int itemsAmountPerSite, int page) {
		int pagethings = 0;
		int pages = 1;
		ArrayList<InventoryItem> item = new ArrayList<>();
		for(int i = 0; i < getNormalItems().size(); i++) {
			pagethings++;
			if(pagethings > itemsAmountPerSite) {
				pagethings = 0;
				pages = pages + 1;
			}
			if(pages == page) {
				item.add(getNormalItems().get(i));
			}
			continue;
		}
		return item;
	}

}
