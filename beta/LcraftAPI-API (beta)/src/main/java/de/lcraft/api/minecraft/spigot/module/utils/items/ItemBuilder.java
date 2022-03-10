package de.lcraft.api.minecraft.spigot.module.utils.items;

import de.lcraft.api.minecraft.spigot.module.manager.utils.listeners.ListenerManager;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemBuilder implements Listener {

    private final ArrayList<String> lore;
    private Material material;
    private final int amount;
    private String displayName;

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
    public void setMaterial(Material material) {
        this.material = material;
    }
    public ItemStack build() {
        ItemStack i = new ItemStack(getMaterial(), getAmount());
        ItemMeta itemMeta = i.getItemMeta();
        itemMeta.setDisplayName(getDisplayName());
        itemMeta.setLore(getLore());
        i.setItemMeta(itemMeta);
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

}