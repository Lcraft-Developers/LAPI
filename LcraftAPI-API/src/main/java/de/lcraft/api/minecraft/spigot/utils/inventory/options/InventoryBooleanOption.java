package de.lcraft.api.minecraft.spigot.utils.inventory.options;

import de.lcraft.api.minecraft.spigot.SpigotClass;
import de.lcraft.api.minecraft.spigot.utils.items.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.util.Consumer;

public class InventoryBooleanOption implements Listener {

    private ItemBuilder itemBuilder;
    private int middleSlot;
    private Consumer<InventoryClickEvent> middleConsumer;
    private String title;

    public InventoryBooleanOption(ItemBuilder itemMiddle, int middleSlot, SpigotClass plugin, String invTitle) {
        this.itemBuilder = itemMiddle;
        this.middleSlot = middleSlot;
        Bukkit.getPluginManager().registerEvents(this, plugin);
        this.title = title;
    }

    public Inventory useIt(Inventory inv, int middleSlot, Consumer<InventoryClickEvent> middleConsumer) {
        this.middleConsumer = middleConsumer;
        inv.setItem(middleSlot, itemBuilder.build());
        return inv;
    }
    public Inventory useIt(Inventory inv, Consumer<InventoryClickEvent> middleConsumer) {
        this.middleConsumer = middleConsumer;
        inv.setItem(middleSlot, itemBuilder.build());
        return inv;
    }

    public int getMiddleSlot() {
        return middleSlot;
    }
    public ItemBuilder getItemBuilder() {
        return itemBuilder;
    }
    public void setItemBuilder(ItemBuilder itemBuilder) {
        this.itemBuilder = itemBuilder;
    }
    public void setMiddleSlot(int middleSlot) {
        this.middleSlot = middleSlot;
    }

    @EventHandler
    public void onInteractEvent(InventoryClickEvent e) {
        if(e.getWhoClicked() != null) {
            if(e.getWhoClicked() instanceof Player) {
                if(e.getCurrentItem() != null) {
                    if(e.getClickedInventory() != null) {
                        if(e.getView().getTitle().equals(title) || e.getView().getTitle().equalsIgnoreCase(title) || e.getView().getTitle().startsWith(title) || e.getView().getTitle().endsWith(title)) {
                            if(e.getCurrentItem().getType() != null && e.getCurrentItem().getType() == itemBuilder.build().getType()) {
                                if(e.getCurrentItem().getItemMeta() != null) {
                                    if(e.getCurrentItem().getItemMeta().getDisplayName() != null) {
                                        if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemBuilder.build().getItemMeta().getDisplayName()) ||
                                                e.getCurrentItem().getItemMeta().getDisplayName().startsWith(itemBuilder.build().getItemMeta().getDisplayName()) ||
                                                e.getCurrentItem().getItemMeta().getDisplayName().endsWith(itemBuilder.build().getItemMeta().getDisplayName())) {
                                            middleConsumer.accept(e);
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

    public Consumer<InventoryClickEvent> getMiddleConsumer() {
        return middleConsumer;
    }

}
