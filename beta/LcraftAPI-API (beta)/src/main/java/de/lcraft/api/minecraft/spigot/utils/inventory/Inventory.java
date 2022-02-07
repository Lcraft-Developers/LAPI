package de.lcraft.api.minecraft.spigot.utils.inventory;

import de.lcraft.api.minecraft.spigot.manager.utils.language.LanguagesManager;
import de.lcraft.api.minecraft.spigot.utils.items.ItemBuilder;
import de.lcraft.api.minecraft.spigot.manager.utils.listeners.ListenerManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.InventoryHolder;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class Inventory {

	private LanguagesManager languagesManager;
	private int width,
	            height;
	private ArrayList<InventoryItem> items;
	private ListenerManager listenerManager;

	public Inventory(ListenerManager listenerManager, LanguagesManager languagesManager, int height) {
		this.languagesManager = languagesManager;
		this.width = 9;
		this.height = height;
		this.items = new ArrayList<>();
		this.listenerManager = listenerManager;
	}

	public org.bukkit.inventory.Inventory getInventory(String title, UUID player) {
		org.bukkit.inventory.Inventory inv = Bukkit.createInventory(null, getSize(), getTitle(title, player));
		for(InventoryItem c : items) {
			inv = c.getSlot().setItem(inv, c.getItem());
		}
		return inv;
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

	public final Inventory addItem(InventoryItem item) {
		return setItem(item);
	}
	public final Inventory setItem(InventoryItem item) {
		if(existsItemAtSlot(item.getSlot().getSlot())) items.remove(getItemFromSlot(item.getSlot().getSlot()));
		items.add(item);
		return this;
	}
	public final InventoryItem getItem(int slot) {
		if(existsItemAtSlot(slot)) {
			return items.get(slot);
		} else {
			return new InventoryItem(new InventorySlot(slot), new ItemBuilder(listenerManager, Material.AIR).setDisplayName(""));
		}
	}
	public final Inventory addPlaceHolders(ItemBuilder placeHolder) {
		for(int i = 0; i < getSize(); i++) {
			if(getItem(i) == null || (Objects.nonNull(getItem(i)) && (getItem(i).getItem().getMaterial() == Material.AIR || getItem(i) instanceof InventoryHolder))) {
				setItem(new InventoryItem(new InventorySlot(i), placeHolder));
			}
			continue;
		}
		return this;
	}
	public final boolean existsItemAtSlot(int slot) {
		if(Objects.nonNull(getItemFromSlot(slot))) return true;
		else return false;
	}
	public final InventoryItem getItemFromSlot(int slot) {
		for(InventoryItem i : items) {
			if(i.getSlot().getSlot() == slot) {
				return i;
			}
			continue;
		}
		return null;
	}
	public final ArrayList<InventoryItem> getItems() {
		return items;
	}
	public final int getNextFreeSpaceInItems() {
		for(int i = 0; i < getSize(); i++) {
			if(!existsItemAtSlot(i)) {
				return i;
			}
			continue;
		}
		return -1;
	}
	public final int getMaxListPages(int itemAmountPerSite) {
		int pagethings = 0;
		int pages = 1;
		for(int i = 0; i < items.size(); i++) {
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
	public final String getTitle(String title, UUID player) {
		return getLanguagesManager().getIDLanguage(getLanguagesManager().getIDFromUUID(player)).translate(title);
	}
	public final ArrayList<InventoryItem> getAllItemsNeeded(int itemsAmountPerSite, int page) {
		int pagethings = 0;
		int pages = 1;
		ArrayList<InventoryItem> item = new ArrayList<>();
		for(int i = 0; i < items.size(); i++) {
			pagethings++;
			if(pagethings > itemsAmountPerSite) {
				pagethings = 0;
				pages = pages + 1;
			}
			if(pages == page) {
				item.add(items.get(i));
			}
			continue;
		}
		return item;
	}

	public final int getWidth() {
		return width;
	}
	public final int getHeight() {
		return height;
	}
	public final int getSize() {
		return (width * height);
	}
	public final LanguagesManager getLanguagesManager() {
		return languagesManager;
	}

	public ListenerManager getListenerManager() {
		return listenerManager;
	}

}
