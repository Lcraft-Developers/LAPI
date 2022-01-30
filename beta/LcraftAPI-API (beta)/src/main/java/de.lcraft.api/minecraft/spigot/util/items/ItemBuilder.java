package de.lcraft.api.minecraft.spigot.util.items;

import de.lcraft.api.minecraft.spigot.manager.listeners.ListenerManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Consumer;
import java.util.ArrayList;
import java.util.Objects;

public class ItemBuilder implements Listener {

    private ArrayList<String> lore;
    private Material material;
    private int amount;
    private String displayName;
    private Consumer<InventoryClickEvent> rightClickConsumer;
    private Consumer<InventoryClickEvent> leftClickConsumer;

    public ItemBuilder(ListenerManager manager, Material material, int amount) {
        this.material = material;
        this.amount = amount;
        lore = new ArrayList<>();
        manager.registerListener(this);
    }
    public ItemBuilder(ListenerManager manager, Material m) {
        this(manager, m, 1);
    }

    public final ItemBuilder setDisplayName(String name) {
        displayName = name;
        return this;
    }
    public final ItemBuilder addLoreString(String... lore) {
        ArrayList<String> l = new ArrayList<>();
        for (String c : lore) {
            l.add(c);
        }
        addLore(l);
        return this;
    }
    public final ItemBuilder addLore(ArrayList<String> lores) {
        for(String c : lores) {
            lore.add(c);
        }
        return this;
    }
    public final void setRightClickConsumer(Consumer<InventoryClickEvent> rightClickConsumer) {
        this.rightClickConsumer = rightClickConsumer;
    }
    public final void setLeftClickConsumer(Consumer<InventoryClickEvent> leftClickConsumer) {
        this.leftClickConsumer = leftClickConsumer;
    }
    public ItemStack build() {
        ItemStack i = new ItemStack(getMaterial(), getAmount());
        i.getItemMeta().setDisplayName(getDisplayName());
        i.getItemMeta().setLore(getLore());
        return i;
    }

    public final ArrayList<String> getLore() {
        return lore;
    }
    public final Material getMaterial() {
        return material;
    }
    public final int getAmount() {
        return amount;
    }
    public final String getDisplayName() {
        return displayName;
    }
    public final Consumer<InventoryClickEvent> getLeftClickConsumer() {
        return leftClickConsumer;
    }
    public final Consumer<InventoryClickEvent> getRightClickConsumer() {
        return rightClickConsumer;
    }

    @EventHandler
    public final void onClick(InventoryClickEvent e) {
        if(Objects.nonNull(e.getAction())) {
            if(e.getClick().isRightClick()) {
                getRightClickConsumer().accept(e);
            } else if(e.getClick().isLeftClick()) {
                getLeftClickConsumer().accept(e);
            }
        }
    }

}