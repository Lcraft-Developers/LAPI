package de.lcraft.api.minecraft.spigot.util.inventory;

import de.lcraft.api.minecraft.spigot.manager.listeners.ListenerManager;
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

		listenerManager.addListener(this);
		listenerManager.flushRegistrationAllListeners();
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
				if(Objects.nonNull(e.getClickedInventory())) {
					if(Objects.nonNull(e.getClickedInventory().getType())) {
						if(e.getClickedInventory().getType() == InventoryType.CHEST) {
							if(Objects.nonNull(e.getView().getTitle())) {
								if((titleStartsWith && e.getView().getTitle().startsWith(invTitle)) ||
										(!titleStartsWith && e.getView().getTitle().equals(invTitle))) {
									if(Objects.nonNull(e.getCurrentItem())) {
										if(Objects.nonNull(e.getCurrentItem().getItemMeta())) {
											if(Objects.nonNull(e.getCurrentItem().getType())) {
												if(e.getCurrentItem().getType() == item.getType()) {
													if(Objects.nonNull(e.getCurrentItem().getItemMeta().getDisplayName())) {
														if((itemNameStartsWith && e.getCurrentItem().getItemMeta().getDisplayName().startsWith(itemDisplayName)) ||
																(!itemNameStartsWith && e.getCurrentItem().getItemMeta().getDisplayName().equals(itemDisplayName))) {
															e.setCancelled(cancellingEvent);
															if(areConsumerActivated) {
																if(e.getClick().isLeftClick()) {
																	if(Objects.nonNull(leftClickConsumer)) {
																		leftClickConsumer.accept(e);
																	}
																}
																if(e.getClick().isRightClick()) {
																	if(Objects.nonNull(rightClickConsumer)) {
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
