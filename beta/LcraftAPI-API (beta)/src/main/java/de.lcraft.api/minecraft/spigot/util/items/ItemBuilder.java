package de.lcraft.api.minecraft.spigot.util.items;

import de.lcraft.api.minecraft.spigot.manager.listeners.ListenerManager;
import net.md_5.bungee.api.plugin.Listener;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Consumer;

import java.util.ArrayList;

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

    public ItemBuilder setDisplayName(String name) {
        displayName = name;
        return this;
    }
    public ItemBuilder addLoreString(String... lore) {
        ArrayList<String> l = new ArrayList<>();
        for (String c : lore) {
            l.add(c);
        }
        addLore(l);
        return this;
    }
    public ItemBuilder addLore(ArrayList<String> lores) {
        for(String c : lores) {
            lore.add(c);
        }
        return this;
    }
    public void setRightClickConsumer(Consumer<InventoryClickEvent> rightClickConsumer) {
        this.rightClickConsumer = rightClickConsumer;
    }
    public void setLeftClickConsumer(Consumer<InventoryClickEvent> leftClickConsumer) {
        this.leftClickConsumer = leftClickConsumer;
    }
    public ItemStack build() {
        ItemStack i = new ItemStack(getMaterial(), getAmount());
        i.getItemMeta().setDisplayName(getDisplayName());
        i.getItemMeta().setLore(getLore());
        return i;
    }

    public ArrayList<String> getLore() {
        return lore;
    }
    public Material getMaterial() {
        return material;
    }
    public int getAmount() {
        return amount;
    }
    public String getDisplayName() {
        return displayName;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {

    }

}