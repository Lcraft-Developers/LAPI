package de.lcraft.api.minecraft.spigot.utils.inventory.item;

import de.lcraft.api.minecraft.spigot.SpigotClass;
import de.lcraft.api.minecraft.spigot.manager.utils.entities.LPlayer;
import de.lcraft.api.minecraft.spigot.utils.items.ItemBuilder;
import de.lcraft.api.minecraft.spigot.manager.utils.listeners.ListenerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.util.Consumer;

import java.util.Objects;

public class InventoryItem implements Listener {

	private InventorySlot slot;
	private ItemBuilder item;
	private boolean areConsumerActivated = false,
	                cancellingEvent,
	                titleStartsWith,
	                itemNameStartsWith;
	private String invTitle;
	private Consumer<InventoryClickEvent> rightClickConsumer,
	                                       leftClickConsumer;

	public InventoryItem(ListenerManager listenerManager, InventorySlot slot, String invTitle, boolean cancelEvent, boolean titleStartsWith, boolean itemNameStartsWith, ItemBuilder item, boolean areConsumerActivated, Consumer<InventoryClickEvent> rightClickConsumer, Consumer<InventoryClickEvent> leftClickConsumer) {
		this(listenerManager, slot, invTitle, cancelEvent, titleStartsWith, itemNameStartsWith, item);
		this.areConsumerActivated = areConsumerActivated;
		this.rightClickConsumer = rightClickConsumer;
		this.leftClickConsumer = leftClickConsumer;
	}
	public InventoryItem(ListenerManager listenerManager, InventorySlot slot, String invTitle, boolean cancelEvent, boolean titleStartsWith, boolean itemNameStartsWith, ItemBuilder item) {
		this.slot = slot;
		this.item = item;
		this.cancellingEvent = cancelEvent;
		this.titleStartsWith = titleStartsWith;
		this.invTitle = invTitle;
		this.itemNameStartsWith = itemNameStartsWith;

		listenerManager.addListener(this);
		listenerManager.flushRegistrationAllListeners();
	}
	public InventoryItem(InventorySlot slot, ItemBuilder item) {
		this.slot = slot;
		this.item = item;
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
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
														if((itemNameStartsWith && e.getCurrentItem().getItemMeta().getDisplayName().startsWith(getItem().getDisplayName())) ||
																(!itemNameStartsWith && e.getCurrentItem().getItemMeta().getDisplayName().equals(getItem().getDisplayName()))) {
															e.setCancelled(isCancellingEvent());
															if(isConsumerActivated()) {
																if(e.getClick().isLeftClick()) {
																	if(Objects.nonNull(getLeftClickConsumer())) {
																		getLeftClickConsumer().accept(e);
																	}
																}
																if(e.getClick().isRightClick()) {
																	if(Objects.nonNull(getRightClickConsumer())) {
																		getRightClickConsumer().accept(e);
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
					}
				}
			}
		}
	}

	public final InventorySlot getSlot() {
		return slot;
	}
	public final ItemBuilder getItem() {
		return item;
	}
	public boolean isConsumerActivated() {
		return areConsumerActivated;
	}
	public final Consumer<InventoryClickEvent> getRightClickConsumer() {
		return getItem().getRightClickConsumer();
	}
	public final Consumer<InventoryClickEvent> getLeftClickConsumer() {
		return getItem().getLeftClickConsumer();
	}
	public final boolean areConsumerActivated() {
		return areConsumerActivated;
	}
	public final boolean isCancellingEvent() {
		return cancellingEvent;
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
