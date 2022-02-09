package de.lcraft.api.minecraft.spigot.utils.inventory;

import de.lcraft.api.minecraft.spigot.manager.utils.language.LanguagesManager;
import de.lcraft.api.minecraft.spigot.utils.inventory.item.InventoryItem;
import de.lcraft.api.minecraft.spigot.utils.inventory.item.slot.InventorySlot;
import de.lcraft.api.minecraft.spigot.manager.utils.listeners.ListenerManager;
import org.bukkit.Bukkit;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class Inventory {

	private LanguagesManager languagesManager;
	private int width,
	            height;
	private HashMap<InventorySlot, InventoryItem> normalItems;
	private ListenerManager listenerManager;

	public Inventory(ListenerManager listenerManager, LanguagesManager languagesManager, int height) {
		this.languagesManager = languagesManager;
		this.width = 9;
		this.height = height;
		this.listenerManager = listenerManager;
		this.normalItems = new HashMap<>();
	}

	public final Inventory setItem(InventoryItem item, InventorySlot slot) {
		if(existsItemAtSlot(slot)) getNormalItems().remove(slot);
		getNormalItems().put(slot,item);
		return this;
	}
	public final InventoryItem getItem(InventorySlot slot) {
		if(Objects.nonNull(slot) && existsItemAtSlot(slot)) {
			return getNormalItems().get(slot);
		} else if(Objects.isNull(slot) || (Objects.isNull(slot.getX()) || Objects.isNull(slot.getY()))) {
			return null;
		}
		return null;
	}

	public org.bukkit.inventory.Inventory getInventory(String title, UUID player) {
		org.bukkit.inventory.Inventory inv = Bukkit.createInventory(null, getSize(), getTitle(title, player));
		HashMap<InventorySlot, InventoryItem> allItems = getNormalItems();
		for(InventorySlot c : allItems.keySet()) {
			if(Objects.nonNull(c) && Objects.nonNull(c) && Objects.nonNull(c.getSpace())) {
				if(Objects.nonNull(allItems.get(c)) && Objects.nonNull(allItems.get(c).getItem())) {
					inv.setItem(c.getSpace(), allItems.get(c).getItem().build());
				}
			}
		}
		return inv;
	}
	public final String getTitle(String title, UUID player) {
		return getLanguagesManager().getIDLanguage(getLanguagesManager().getIDFromUUID(player)).translate(title);
	}
	public final boolean existsItemAtSlot(InventorySlot slot) {
		if(Objects.nonNull(getNormalItems().containsKey(slot))) return true;
		return false;
	}

	public final int getWidth() {
		return width;
	}
	public final int getHeight() {
		return height;
	}
	public final int getSize() {
		return width * height;
	}
	public final LanguagesManager getLanguagesManager() {
		return languagesManager;
	}
	public ListenerManager getListenerManager() {
		return listenerManager;
	}
	public HashMap<InventorySlot, InventoryItem> getNormalItems() {
		return normalItems;
	}

}
