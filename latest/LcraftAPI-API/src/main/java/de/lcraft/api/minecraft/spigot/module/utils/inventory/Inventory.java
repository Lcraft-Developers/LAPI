package de.lcraft.api.minecraft.spigot.module.utils.inventory;

import de.lcraft.api.minecraft.spigot.module.manager.utils.LanguagesManager;
import de.lcraft.api.minecraft.spigot.module.utils.items.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class Inventory {

	private LanguagesManager languagesManager;
	private int width,
	            height;
	private ArrayList<InventoryItem> items;

	public Inventory(LanguagesManager languagesManager, int height) {
		this.languagesManager = languagesManager;
		this.width = 9;
		this.height = height;
		this.items = new ArrayList<>();
	}

	public org.bukkit.inventory.Inventory getInventory(String title, UUID player) throws IOException {
		org.bukkit.inventory.Inventory inv = Bukkit.createInventory(null, getSize(), getTitle(title, player));
		for(InventoryItem c : items) {
			inv = c.getSlot().setItem(inv, c.getItem());
		}
		return inv;
	}
	public org.bukkit.inventory.Inventory getListPageInventory(String title, UUID player, int itemsAmountPerSite, int page, InventoryItem LAST_PAGE, InventoryItem NEXT_PAGE) throws IOException {
		org.bukkit.inventory.Inventory inv = Bukkit.createInventory(null, getSize(), getListTitle(title, player, page, getMaxListPages(itemsAmountPerSite)));
		for(InventoryItem c : getAllItemsNeeded(itemsAmountPerSite, page)) {
			inv = c.getSlot().setItem(inv, c.getItem());
		}
		if(page > 1) inv = LAST_PAGE.getSlot().setItem(inv, LAST_PAGE.getItem());
		if(page < getMaxListPages(itemsAmountPerSite)) inv = NEXT_PAGE.getSlot().setItem(inv, NEXT_PAGE.getItem());
		return inv;
	}

	public Inventory addItem(InventoryItem item) {
		return setItem(item);
	}
	public Inventory setItem(InventoryItem item) {
		if(existsItemAtSlot(item.getSlot().getSlot())) items.remove(getItemFromSlot(item.getSlot().getSlot()));
		items.add(item);
		return this;
	}
	public InventoryItem getItem(int slot) {
		if(existsItemAtSlot(slot)) {
			return items.get(slot);
		} else {
			return new InventoryItem(new InventorySlot(slot), new ItemBuilder(Material.AIR).setDisplayName("").build());
		}
	}
	public Inventory addPlaceHolders(ItemStack placeHolder) {
		for(int i = 0; i < getSize(); i++) {
			if(getItem(i) == null || (getItem(i) != null && (getItem(i).getItem().getType() == Material.AIR || getItem(i) instanceof InventoryHolder))) {
				setItem(new InventoryItem(new InventorySlot(i), placeHolder));
			}
		}
		return this;
	}
	public boolean existsItemAtSlot(int slot) {
		if(getItemFromSlot(slot) != null) return true;
		else return false;
	}
	public InventoryItem getItemFromSlot(int slot) {
		for(InventoryItem i : items) {
			if(i.getSlot().getSlot() == slot) {
				return i;
			}
		}
		return null;
	}

	public ArrayList<InventoryItem> getItems() {
		return items;
	}
	public int getNextFreeSpaceInItems() {
		for(int i = 0; i < getSize(); i++) {
			if(!existsItemAtSlot(i)) {
				return i;
			}
		}
		return -1;
	}

	public LanguagesManager getLanguagesManager() {
		return languagesManager;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public int getSize() {
		return (width * height);
	}
	public int getMaxListPages(int itemAmountPerSite) {
		int pagethings = 0;
		int pages = 1;
		for(int i = 0; i < items.size(); i++) {
			pagethings++;
			if(pagethings > itemAmountPerSite) {
				pagethings = 1;
				pages = pages + 1;
			}
		}
		return pages;
	}
	public String getListTitle(String title, UUID player, int currentPage, int pages) throws IOException {
		return getLanguagesManager().getIDLanguage(getLanguagesManager().getIDFromUUID(player)).translate(title + " §6%cpage%§7/§6%maxpage%").replace("%cpage%", currentPage + "").replace("%maxpage%", pages + "");
	}
	public String getTitle(String title, UUID player) throws IOException {
		return getLanguagesManager().getIDLanguage(getLanguagesManager().getIDFromUUID(player)).translate(title);
	}
	public ArrayList<InventoryItem> getAllItemsNeeded(int itemsAmountPerSite, int page) {
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
		}
		return item;
	}

}
