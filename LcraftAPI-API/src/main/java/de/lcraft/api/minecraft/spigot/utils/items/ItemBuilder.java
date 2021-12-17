package de.lcraft.api.minecraft.spigot.utils.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.ArrayList;

public class ItemBuilder {

    public ItemStack i;
    public ItemMeta iMeta;

    public ItemBuilder(Material m, int amount) {
        i = new ItemStack(m, amount);
        iMeta = i.getItemMeta();
    }
    public ItemBuilder(Material m) {
        this(m, 1);
    }

    public ItemBuilder setDisplayName(String name) {
        iMeta.setDisplayName(name);
        return this;
    }
    public ItemBuilder setLoreString(String... lore) {
        ArrayList<String> l = new ArrayList<>();
        for (String c : lore) {
            l.add(c);
        }
        setLore(l);
        return this;
    }
    public ItemBuilder setLore(ArrayList<String> lore) {
        iMeta.setLore(lore);
        return this;
    }
    public ItemStack build() {
        i.setItemMeta(iMeta);
        return i;
    }

}