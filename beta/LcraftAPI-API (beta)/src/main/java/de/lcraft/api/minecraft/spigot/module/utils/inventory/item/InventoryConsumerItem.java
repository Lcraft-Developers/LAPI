package de.lcraft.api.minecraft.spigot.module.utils.inventory.item;

import de.lcraft.api.minecraft.spigot.module.manager.utils.listeners.ListenerManager;
import de.lcraft.api.minecraft.spigot.module.utils.inventory.BukkitInventoryValidator;
import de.lcraft.api.minecraft.spigot.module.utils.items.ItemConsumerBuilder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import java.util.function.Consumer;

public class InventoryConsumerItem extends InventoryItem implements Listener {

	private boolean areConsumerActivated = false,
	                itemNameStartsWith;
	private ItemConsumerBuilder item;

	public InventoryConsumerItem(ListenerManager listenerManager,  String invTitle, boolean titleStartsWith, boolean isCanceling, boolean itemNameStartsWith, ItemConsumerBuilder item) {
		this(listenerManager, invTitle, titleStartsWith, isCanceling, true, itemNameStartsWith, item);
	}
	public InventoryConsumerItem(ListenerManager listenerManager, String invTitle, boolean isCanceling, boolean titleStartsWith, boolean areConsumerActivated, boolean itemNameStartsWith, ItemConsumerBuilder item) {
		super(listenerManager, invTitle, titleStartsWith, isCanceling, itemNameStartsWith, item);
		this.itemNameStartsWith = itemNameStartsWith;
		this.areConsumerActivated = areConsumerActivated;
		this.item = item;
		this.item.setAreConsumersActivated(false);
	}

	@Override
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
									if(areConsumerActivated()) {
										if(inventoryValidator.isRightClick(e)) {
											getRightClickConsumer().accept(e);
										} else if(inventoryValidator.isLeftClick(e)) {
											getLeftClickConsumer().accept(e);
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

	@Override
	public final boolean isCanceling() {
		return super.isCanceling();
	}
	@Override
	public ItemConsumerBuilder getItem() {
		return item;
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

}
