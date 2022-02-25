package de.lcraft.api.minecraft.spigot.module.utils.items;

import de.lcraft.api.minecraft.spigot.module.manager.utils.listeners.ListenerManager;
import de.lcraft.api.minecraft.spigot.module.utils.inventory.BukkitInventoryValidator;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

import java.util.function.Consumer;

public class ItemConsumerBuilder extends ItemBuilder {

    private boolean cancelEvent;
    private boolean areConsumersActivated;
    private Consumer<InventoryClickEvent> rightClickConsumer;
    private Consumer<InventoryClickEvent> leftClickConsumer;

    public ItemConsumerBuilder(ListenerManager manager, Material material, int amount, boolean areConsumersActivated, Consumer<InventoryClickEvent> rightClickConsumer, Consumer<InventoryClickEvent> leftClickConsumer) {
        super(manager, material, amount);
        manager.registerListener(this);
        this.leftClickConsumer = leftClickConsumer;
        this.rightClickConsumer = rightClickConsumer;
        this.areConsumersActivated = areConsumersActivated;
    }
    public ItemConsumerBuilder(ListenerManager manager, Material m, boolean areConsumersActivated, Consumer<InventoryClickEvent> rightClickConsumer, Consumer<InventoryClickEvent> leftClickConsumer) {
        this(manager, m, 1, areConsumersActivated, rightClickConsumer, leftClickConsumer);
    }

    public final void setRightClickConsumer(Consumer<InventoryClickEvent> rightClickConsumer) {
        this.rightClickConsumer = rightClickConsumer;
    }
    public final void setLeftClickConsumer(Consumer<InventoryClickEvent> leftClickConsumer) {
        this.leftClickConsumer = leftClickConsumer;
    }
    public final void setAreConsumersActivated(boolean areConsumersActivated) {
        this.areConsumersActivated = areConsumersActivated;
    }
    public final ItemConsumerBuilder setCancelEvent(boolean cancelEvent) {
        this.cancelEvent = cancelEvent;
        return this;
    }

    public final Consumer<InventoryClickEvent> getLeftClickConsumer() {
        return leftClickConsumer;
    }
    public final Consumer<InventoryClickEvent> getRightClickConsumer() {
        return rightClickConsumer;
    }
    public final boolean isCancelEvent() {
        return cancelEvent;
    }
    public final boolean areConsumersActivated() {
        return areConsumersActivated;
    }

    @EventHandler
    public final void onClick(InventoryClickEvent e) {
        BukkitInventoryValidator inventoryValidator = new BukkitInventoryValidator();
        if(areConsumersActivated()) {
            if(inventoryValidator.isLPlayer(e)) {
                if(inventoryValidator.existsInventoryAndHasInventoryTitle(e)) {
                    if(inventoryValidator.isInventoryType(e, InventoryType.CHEST)) {
                        if(inventoryValidator.existsItemWithItemMetaAndDisplayName(e.getCurrentItem())) {
                            if(inventoryValidator.isItemName(e.getCurrentItem(), getDisplayName(), true, true)) {
                                e.setCancelled(isCancelEvent());
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