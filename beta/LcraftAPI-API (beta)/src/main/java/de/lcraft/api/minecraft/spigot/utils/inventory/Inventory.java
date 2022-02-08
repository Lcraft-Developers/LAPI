package de.lcraft.api.minecraft.spigot.utils.inventory;

import de.lcraft.api.minecraft.spigot.manager.utils.language.LanguagesManager;
import de.lcraft.api.minecraft.spigot.utils.inventory.item.InventoryItem;
import de.lcraft.api.minecraft.spigot.utils.inventory.item.slot.InventorySlot;
import de.lcraft.api.minecraft.spigot.utils.inventory.item.slot.InventorySlotSpace;
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
	private ArrayList<InventoryItem> normalItems;
	private ListenerManager listenerManager;

	public Inventory(ListenerManager listenerManager, LanguagesManager languagesManager, int height) {
		this.languagesManager = languagesManager;
		this.width = 9;
		this.height = height;
		this.listenerManager = listenerManager;
	}

	public org.bukkit.inventory.Inventory getInventory(String title, UUID player) {
		org.bukkit.inventory.Inventory inv = Bukkit.createInventory(null, getSize(), getTitle(title, player));
		for(InventoryItem c : getNormalItems()) {
			inv = c.getSlot().setItem(inv, c.getItem());
		}
		return inv;
	}
	public final Inventory setItem(InventoryItem item) {
		if(existsItemAtSlot(item.getSlot().getSlotSpace())) getNormalItems().remove(getItemFromSlot(item.getSlot().getSlotSpace()));
		getNormalItems().add(item);
		return this;
	}
	public final InventoryItem getItem(InventorySlotSpace slot) {
		if(existsItemAtSlot(slot)) {
			return getNormalItems().get(slot.getSpace());
		} else {
			return new InventoryItem(new InventorySlot(slot.getX(), slot.getY()), new ItemBuilder(listenerManager, Material.AIR).setDisplayName(""));
		}
	}
	public final Inventory addPlaceHolders(ItemBuilder placeHolder) {
		for(int i = 0; i < getSize(); i++) {
			InventoryItem item = getItem(InventorySlotSpace.getSlotSpaceBySpace(i));
			if(Objects.isNull(item)) {
				setItem(new InventoryItem(item.getSlot(), placeHolder));
			} else if(Objects.isNull(item.getItem().getMaterial())) {
				setItem(new InventoryItem(item.getSlot(), placeHolder));
			} else if(item.getItem().getMaterial() == Material.AIR || item instanceof InventoryHolder) {
				setItem(new InventoryItem(item.getSlot(), placeHolder));
			} else if(Objects.nonNull(placeHolder) && item.equals(placeHolder)) {
				setItem(new InventoryItem(item.getSlot(), placeHolder));
			}
			continue;
		}
		return this;
	}
	public final String getTitle(String title, UUID player) {
		return getLanguagesManager().getIDLanguage(getLanguagesManager().getIDFromUUID(player)).translate(title);
	}
	public final boolean existsItemAtSlot(InventorySlotSpace slot) {
		if(Objects.nonNull(getItemFromSlot(slot))) return true;
		else return false;
	}
	public final InventoryItem getItemFromSlot(InventorySlotSpace slot) {
		for(InventoryItem i : getNormalItems()) {
			if(i.getSlot().getSlotSpace().equals(slot)) {
				return i;
			}
			continue;
		}
		return null;
	}
	public final int getNextFreeSpaceInItems() {
		for(int space = 0; space < getSize(); space++) {
			if(!existsItemAtSlot(InventorySlotSpace.getSlotSpaceBySpace(space))) {
				return space;
			}
			continue;
		}
		return -1;
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
	public ArrayList<InventoryItem> getNormalItems() {
		return normalItems;
	}

}
