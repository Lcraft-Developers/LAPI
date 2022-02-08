package de.lcraft.api.minecraft.spigot.utils.items;

import de.lcraft.api.minecraft.spigot.manager.utils.listeners.ListenerManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Objects;
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
        if(Objects.nonNull(e.getCurrentItem()) && Objects.nonNull(e.getCurrentItem().getItemMeta()) && Objects.nonNull(e.getCurrentItem().getItemMeta().getDisplayName())) {
            if(areConsumersActivated()) {
                if(Objects.nonNull(e.getAction())) {
                    if(e.getClick().isRightClick()) {
                        if(Objects.nonNull(getRightClickConsumer())) {
                            getRightClickConsumer().accept(e);
                        }
                    } else if(e.getClick().isLeftClick()) {
                        if(Objects.nonNull(getLeftClickConsumer())) {
                            getLeftClickConsumer().accept(e);
                        }
                    }
                }
            }
            e.setCancelled(isCancelEvent());
        }
    }

}