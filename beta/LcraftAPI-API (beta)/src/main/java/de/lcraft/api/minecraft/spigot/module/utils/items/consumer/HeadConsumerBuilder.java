package de.lcraft.api.minecraft.spigot.module.utils.items.consumer;

import de.lcraft.api.minecraft.spigot.module.utils.inventory.BukkitInventoryValidator;
import de.lcraft.api.minecraft.spigot.module.utils.items.HeadBuilder;
import de.lcraft.api.minecraft.spigot.module.utils.items.ItemBuilder;
import de.lcraft.api.minecraft.spigot.utils.listeners.ListenerManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

import java.util.Objects;
import java.util.function.Consumer;

public class HeadConsumerBuilder extends HeadBuilder {

    private boolean cancelEvent;
    private Consumer<InventoryClickEvent> rightClickConsumer;
    private Consumer<InventoryClickEvent> leftClickConsumer;
    private final ListenerManager listenerManager;

    public HeadConsumerBuilder(String playerName, int amount, ListenerManager manager, Consumer<InventoryClickEvent> rightClickConsumer, Consumer<InventoryClickEvent> leftClickConsumer) {
        super(manager, playerName, amount);
        this.listenerManager = manager;
        this.leftClickConsumer = leftClickConsumer;
        this.rightClickConsumer = rightClickConsumer;
        this.cancelEvent = false;

        register();
    }
    public HeadConsumerBuilder(String playerName, ListenerManager manager, Consumer<InventoryClickEvent> rightClickConsumer, Consumer<InventoryClickEvent> leftClickConsumer) {
        this(playerName, 1, manager, rightClickConsumer, leftClickConsumer);
    }

    public void register() {
        listenerManager.registerListener(this);
    }
    public void unregister() {
        listenerManager.unregisterListener(this);
    }

    public final HeadConsumerBuilder setCancelEvent(boolean cancelEvent) {
        this.cancelEvent = cancelEvent;
        return this;
    }
    public final boolean isCancelEvent() {
        return cancelEvent;
    }

    public final void setRightClickConsumer(Consumer<InventoryClickEvent> rightClickConsumer) {
        this.rightClickConsumer = rightClickConsumer;
    }
    public final void setLeftClickConsumer(Consumer<InventoryClickEvent> leftClickConsumer) {
        this.leftClickConsumer = leftClickConsumer;
    }
    public final Consumer<InventoryClickEvent> getLeftClickConsumer() {
        return leftClickConsumer;
    }
    public final Consumer<InventoryClickEvent> getRightClickConsumer() {
        return rightClickConsumer;
    }
    public final boolean isRightConsumerActivated() {
        return Objects.nonNull(getRightClickConsumer());
    }
    public final boolean isLeftConsumerActivated() {
        return Objects.nonNull(getLeftClickConsumer());
    }

    public ListenerManager getListenerManager() {
        return listenerManager;
    }

    @EventHandler
    public final void onClick(InventoryClickEvent e) {
        BukkitInventoryValidator inventoryValidator = new BukkitInventoryValidator();
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