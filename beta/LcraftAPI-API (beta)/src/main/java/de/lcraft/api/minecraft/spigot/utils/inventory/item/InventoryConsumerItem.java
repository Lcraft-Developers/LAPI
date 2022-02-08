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
import org.bukkit.inventory.ItemStack;

import java.util.Objects;
import java.util.function.Consumer;

public class InventoryConsumerItem extends InventoryItem implements Listener {

	private boolean areConsumerActivated = false,
	                itemNameStartsWith;
	private ItemConsumerBuilder item;

	public InventoryConsumerItem(ListenerManager listenerManager, InventorySlot slot, String invTitle, boolean titleStartsWith, boolean itemNameStartsWith, ItemConsumerBuilder item) {
		this(listenerManager, slot, invTitle, titleStartsWith, true, itemNameStartsWith, null);
		this.areConsumerActivated = true;
	}
	public InventoryConsumerItem(ListenerManager listenerManager, InventorySlot slot, String invTitle, boolean titleStartsWith, boolean areConsumerActivated, boolean itemNameStartsWith, ItemConsumerBuilder item) {
		super(listenerManager, slot, invTitle, titleStartsWith, false, itemNameStartsWith, item);
		this.itemNameStartsWith = itemNameStartsWith;
		this.areConsumerActivated = areConsumerActivated;
		listenerManager.registerListener(this);
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
								if((isTitleStartsWith() && e.getView().getTitle().startsWith(getInvTitle())) ||
										(!isTitleStartsWith() && e.getView().getTitle().equals(getInvTitle()))) {
									if(Objects.nonNull(e.getCurrentItem())) {
										if(Objects.nonNull(e.getCurrentItem().getItemMeta())) {
											if(Objects.nonNull(e.getCurrentItem().getType())) {
												if(e.getCurrentItem().getType() == getItem().getMaterial()) {
													if(Objects.nonNull(e.getCurrentItem().getItemMeta().getDisplayName())) {
														if(Objects.nonNull(getItem())) {
															if(Objects.nonNull(getItem().getDisplayName())) {
																if((itemNameStartsWith && e.getCurrentItem().getItemMeta().getDisplayName().startsWith(getItem().getDisplayName())) ||
																		(!itemNameStartsWith && e.getCurrentItem().getItemMeta().getDisplayName().equals(getItem().getDisplayName()))) {
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

	public boolean isConsumerActivated() {
		return areConsumerActivated;
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
