package de.lcraft.api.minecraft.spigot.util.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class ItemBuilder {

    private ArrayList<String> lore;
    private Material material;
    private int amount;
    private String displayName;

    public ItemBuilder(Material material, int amount) {
        this.material = material;
        this.amount = amount;

        lore = new ArrayList<>();
    }
    public ItemBuilder(Material m) {
        this(m, 1);
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

}