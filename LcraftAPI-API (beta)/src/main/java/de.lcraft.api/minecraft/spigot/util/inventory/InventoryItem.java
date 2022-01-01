package de.lcraft.api.minecraft.spigot.util.inventory;

import de.lcraft.api.minecraft.spigot.listeners.ListenerManager;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Consumer;

public class InventoryItem implements Listener {

	private InventorySlot slot;
	private ItemStack item;
	private Consumer<InventoryClickEvent> rightClickConsumer;
	private Consumer<InventoryClickEvent> leftClickConsumer;
	private boolean areConsumerActivated = false,
	                cancellingEvent,
	                titleStartsWith,
	                itemNameStartsWith;
	private String invTitle,
	               itemDisplayName;

	public InventoryItem(ListenerManager listenerManager, InventorySlot slot, String invTitle, boolean cancelEvent, boolean titleStartsWith, boolean itemNameStartsWith, ItemStack item, Consumer<InventoryClickEvent> rightClickConsumer, Consumer<InventoryClickEvent> leftClickConsumer) {
		this(listenerManager, slot, invTitle, cancelEvent, titleStartsWith, itemNameStartsWith, item);
		this.rightClickConsumer = rightClickConsumer;
		this.leftClickConsumer = leftClickConsumer;
		this.areConsumerActivated = true;
	}
	public InventoryItem(ListenerManager listenerManager, InventorySlot slot, String invTitle, boolean cancelEvent, boolean titleStartsWith, boolean itemNameStartsWith, ItemStack item) {
		this.slot = slot;
		this.item = item;
		this.itemDisplayName = item.getItemMeta().getDisplayName();
		this.cancellingEvent = cancelEvent;
		this.titleStartsWith = titleStartsWith;
		this.invTitle = invTitle;
		this.itemNameStartsWith = itemNameStartsWith;

		listenerManager.registerListener(this);
	}
	public InventoryItem(InventorySlot slot, ItemStack item) {
		this.slot = slot;
		this.item = item;
		this.itemDisplayName = item.getItemMeta().getDisplayName();
	}

	/*@EventHandler
	public void onClick(InventoryClickEvent e) {
		if(e.getWhoClicked() instanceof Player) {
			if(SpigotClass.getAPIPluginMain().existsPlayer(e.getWhoClicked().getUniqueId())) {
				LPlayer l = SpigotClass.getAPIPluginMain().getLPlayerByUUID(e.getWhoClicked().getUniqueId());
				if(e.getClickedInventory() != null) {
					if(e.getClickedInventory().getType() != null) {
						if(e.getClickedInventory().getType() == InventoryType.CHEST) {
							if(e.getView().getTitle() != null) {
								if((titleStartsWith && e.getView().getTitle().startsWith(invTitle)) ||
										(!titleStartsWith && e.getView().getTitle().equals(invTitle))) {
									if(e.getCurrentItem() != null) {
										if(e.getCurrentItem().getItemMeta() != null) {
											if(e.getCurrentItem().getType() != null) {
												if(e.getCurrentItem().getType() == item.getType()) {
													if(e.getCurrentItem().getItemMeta().getDisplayName() != null) {
														if((itemNameStartsWith && e.getCurrentItem().getItemMeta().getDisplayName().startsWith(itemDisplayName)) ||
																(!itemNameStartsWith && e.getCurrentItem().getItemMeta().getDisplayName().equals(itemDisplayName))) {
															e.setCancelled(cancellingEvent);
															if(areConsumerActivated) {
																if(e.getClick().isLeftClick()) {
																	if(leftClickConsumer != null) {
																		leftClickConsumer.accept(e);
																	}
																}
																if(e.getClick().isRightClick()) {
																	if(rightClickConsumer != null) {
																		rightClickConsumer.accept(e);
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
	}*/

	public InventorySlot getSlot() {
		return slot;
	}
	public ItemStack getItem() {
		return item;
	}
	public Consumer<InventoryClickEvent> getRightClickConsumer() {
		return rightClickConsumer;
	}
	public Consumer<InventoryClickEvent> getLeftClickConsumer() {
		return leftClickConsumer;
	}
	public boolean areConsumerActivated() {
		return areConsumerActivated;
	}
	public boolean isCancellingEvent() {
		return cancellingEvent;
	}
	public boolean isTitleStartsWith() {
		return titleStartsWith;
	}
	public boolean isItemNameStartsWith() {
		return itemNameStartsWith;
	}
	public String getInvTitle() {
		return invTitle;
	}
	public String getItemDisplayName() {
		return itemDisplayName;
	}

}
