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

public class InventoryIntegerOption implements Listener {

    private ItemBuilder itemMiddle, itemDown, itemUp;
    private int middleSlot;
    private Consumer<InventoryClickEvent> middleConsumer;
    private Consumer<InventoryClickEvent> upConsumer;
    private Consumer<InventoryClickEvent> downConsumer;
    private String title;

    public InventoryIntegerOption(ItemBuilder itemMiddle, ItemBuilder itemDown, ItemBuilder itemUp, int middleSlot, SpigotClass plugin, String invTitle) {
        this.itemMiddle = itemMiddle;
        this.itemDown = itemDown;
        this.itemUp = itemUp;
        this.middleSlot = middleSlot;
        Bukkit.getPluginManager().registerEvents(this, plugin);
        this.title = invTitle;
    }

    public Inventory useIt(Inventory inv, int middleSlot, Consumer<InventoryClickEvent> middleConsumer, Consumer<InventoryClickEvent> downConsumer, Consumer<InventoryClickEvent> upConsumer) {
        this.middleConsumer = middleConsumer;
        this.downConsumer = downConsumer;
        this.upConsumer = upConsumer;
        inv.setItem(middleSlot, itemMiddle.build());
        inv.setItem(middleSlot + 9, itemUp.build());
        inv.setItem(middleSlot - 9, itemDown.build());
        return inv;
    }
    public Inventory useIt(Inventory inv, Consumer<InventoryClickEvent> middleConsumer, Consumer<InventoryClickEvent> downConsumer, Consumer<InventoryClickEvent> upConsumer) {
        this.middleConsumer = middleConsumer;
        this.downConsumer = downConsumer;
        this.upConsumer = upConsumer;
        inv.setItem(middleSlot, itemMiddle.build());
        inv.setItem(middleSlot + 9, itemUp.build());
        inv.setItem(middleSlot - 9, itemDown.build());
        return inv;
    }

    @EventHandler
    public void onInteractEvent(InventoryClickEvent e) {
        if(e.getWhoClicked() != null) {
            if(e.getWhoClicked() instanceof Player) {
                if(e.getCurrentItem() != null) {
                    if(e.getClickedInventory() != null) {
                        if(e.getView().getTitle().equals(title) || e.getView().getTitle().equalsIgnoreCase(title) || e.getView().getTitle().startsWith(title) || e.getView().getTitle().endsWith(title)) {
                            if(e.getCurrentItem().getType() != null && e.getCurrentItem().getType() == itemDown.build().getType()) {
                                if(e.getCurrentItem().getItemMeta() != null) {
                                    if(e.getCurrentItem().getItemMeta().getDisplayName() != null) {
                                        if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemDown.build().getItemMeta().getDisplayName()) ||
                                                e.getCurrentItem().getItemMeta().getDisplayName().startsWith(itemDown.build().getItemMeta().getDisplayName()) ||
                                                e.getCurrentItem().getItemMeta().getDisplayName().endsWith(itemDown.build().getItemMeta().getDisplayName())) {
                                            downConsumer.accept(e);
                                        }
                                    }
                                }
                            } else if(e.getCurrentItem().getType() != null && e.getCurrentItem().getType() == itemMiddle.build().getType()) {
                                if(e.getCurrentItem().getItemMeta() != null) {
                                    if(e.getCurrentItem().getItemMeta().getDisplayName() != null) {
                                        if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemMiddle.build().getItemMeta().getDisplayName()) ||
                                                e.getCurrentItem().getItemMeta().getDisplayName().startsWith(itemMiddle.build().getItemMeta().getDisplayName()) ||
                                                e.getCurrentItem().getItemMeta().getDisplayName().endsWith(itemMiddle.build().getItemMeta().getDisplayName())) {
                                            middleConsumer.accept(e);
                                        }
                                    }
                                }
                            } else if(e.getCurrentItem().getType() != null && e.getCurrentItem().getType() == itemUp.build().getType()) {
                                if(e.getCurrentItem().getItemMeta() != null) {
                                    if(e.getCurrentItem().getItemMeta().getDisplayName() != null) {
                                        if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemUp.build().getItemMeta().getDisplayName()) ||
                                                e.getCurrentItem().getItemMeta().getDisplayName().startsWith(itemUp.build().getItemMeta().getDisplayName()) ||
                                                e.getCurrentItem().getItemMeta().getDisplayName().endsWith(itemUp.build().getItemMeta().getDisplayName())) {
                                            upConsumer.accept(e);
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
