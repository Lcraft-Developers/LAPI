package de.lcraft.api.minecraft.spigot.utils.inventory.item;

import de.lcraft.api.minecraft.spigot.SpigotClass;
import de.lcraft.api.minecraft.spigot.manager.utils.entities.LPlayer;
import de.lcraft.api.minecraft.spigot.manager.utils.listeners.ListenerManager;
import de.lcraft.api.minecraft.spigot.utils.items.ItemBuilder;
import de.lcraft.api.minecraft.spigot.utils.items.ItemConsumerBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import java.util.Objects;
import java.util.function.Consumer;

public class InventoryItem implements Listener {

	private InventorySlot slot;
	private ItemBuilder item;
	private boolean titleStartsWith,
	                itemNameStartsWith;
	private String invTitle;
	private boolean isCanceling;
	private boolean isEnabled;

	public InventoryItem(ListenerManager listenerManager, InventorySlot slot, String invTitle, boolean titleStartsWith, boolean isCanceling, boolean itemNameStartsWith, ItemBuilder item) {
		this.titleStartsWith = titleStartsWith;
		this.invTitle = invTitle;
		this.slot = slot;
		this.itemNameStartsWith = itemNameStartsWith;
		this.item = item;
		this.isCanceling = isCanceling;
		this.isEnabled = true;

		listenerManager.registerListener(this);
	}

	public InventoryItem(InventorySlot slot, ItemBuilder itemBuilder) {
		this.slot = slot;
		this.item = itemBuilder;
		this.isEnabled = false;
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if(isEnabled()) {
			if(e.getWhoClicked() instanceof Player) {
				if(SpigotClass.getAPIPluginMain().getLPlayerManager().existsLPlayer(e.getWhoClicked().getUniqueId())) {
					LPlayer l = SpigotClass.getAPIPluginMain().getLPlayerManager().getLPlayerByUUID(e.getWhoClicked().getUniqueId());
					if(Objects.nonNull(e.getClickedInventory())) {
						if(Objects.nonNull(e.getClickedInventory().getType())) {
							if(e.getClickedInventory().getType() == InventoryType.CHEST) {
								if(Objects.nonNull(e.getView().getTitle())) {
									if((titleStartsWith && e.getView().getTitle().startsWith(invTitle)) ||
											(!titleStartsWith && e.getView().getTitle().equals(invTitle))) {
										if(Objects.nonNull(e.getCurrentItem())) {
											if(Objects.nonNull(e.getCurrentItem().getItemMeta())) {
												if(Objects.nonNull(e.getCurrentItem().getType())) {
													if(e.getCurrentItem().getType() == item.getMaterial()) {
														if(Objects.nonNull(e.getCurrentItem().getItemMeta().getDisplayName())) {
															if(Objects.nonNull(getItem())) {
																if(Objects.nonNull(getItem().getDisplayName())) {
																	if((itemNameStartsWith && e.getCurrentItem().getItemMeta().getDisplayName().startsWith(getItem().getDisplayName())) ||
																			(!itemNameStartsWith && e.getCurrentItem().getItemMeta().getDisplayName().equals(getItem().getDisplayName()))) {
																		e.setCancelled(isCanceling());
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
						e.setCancelled(isCanceling());
					}
				}
			}
		}
	}

	public final InventorySlot getSlot() {
		return slot;
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
