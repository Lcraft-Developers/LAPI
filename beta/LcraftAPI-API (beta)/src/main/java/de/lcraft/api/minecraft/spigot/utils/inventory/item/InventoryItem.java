package de.lcraft.api.minecraft.spigot.utils.inventory.item;

import de.lcraft.api.minecraft.spigot.manager.utils.listeners.ListenerManager;
import de.lcraft.api.minecraft.spigot.utils.inventory.BukkitInventoryValidator;
import de.lcraft.api.minecraft.spigot.utils.items.ItemBuilder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class InventoryItem implements Listener {

	private ItemBuilder item;
	private boolean titleStartsWith,
	                itemNameStartsWith;
	private String invTitle;
	private boolean isCanceling;
	private boolean isEnabled;

	public InventoryItem(ListenerManager listenerManager, String invTitle, boolean titleStartsWith, boolean isCanceling, boolean itemNameStartsWith, ItemBuilder item) {
		this.titleStartsWith = titleStartsWith;
		this.invTitle = invTitle;
		this.itemNameStartsWith = itemNameStartsWith;
		this.item = item;
		this.isCanceling = isCanceling;
		this.isEnabled = true;

		listenerManager.registerListener(this);
	}
	public InventoryItem(ItemBuilder itemBuilder) {
		this.item = itemBuilder;
		this.isEnabled = false;
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		BukkitInventoryValidator inventoryValidator = new BukkitInventoryValidator();
		if(isEnabled()) {
			if(inventoryValidator.isLPlayer(e)) {
				if(inventoryValidator.existsInventoryAndHasInventoryTitle(e)) {
					if(inventoryValidator.isInventoryType(e,InventoryType.CHEST)) {
						if(inventoryValidator.isInventoryTitle(e,getInvTitle(),true,true)) {
							if(inventoryValidator.existsItemWithItemMetaAndDisplayName(e.getCurrentItem())) {
								if(inventoryValidator.isItemName(e.getCurrentItem(),getItem().getDisplayName(), true, true)) {
									e.setCancelled(isCanceling());
								}
							}
						}
					}
				}
			}
		}
	}

	public ItemBuilder getItem() {
		return item;
	}
	public boolean isCanceling() {
		return isCanceling;
	}
	public boolean isEnabled() {
		return isEnabled;
	}
	public final boolean isTitleStartsWith() {
		return titleStartsWith;
	}
	public final boolean isItemNameStartsWith() {
		return itemNameStartsWith;
	}
	public final String getInvTitle() {
		return invTitle;
	}

}
