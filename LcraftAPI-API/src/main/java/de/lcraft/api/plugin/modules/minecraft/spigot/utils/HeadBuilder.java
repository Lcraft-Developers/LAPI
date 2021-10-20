package de.lcraft.api.plugin.modules.minecraft.spigot.utils;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;


public class HeadBuilder {
    private static ItemStack i;
    private static SkullMeta iMeta;

    public HeadBuilder(String name, int amount) {
        i = new ItemStack(Material.PLAYER_HEAD, amount);
        iMeta = (SkullMeta) i.getItemMeta();
        iMeta.setOwner(name);
    }

    public HeadBuilder(String name) {
        this(name, 1);
    }

    public HeadBuilder setDisplayName(String name) {
        iMeta.setDisplayName(name);
        return this;
    }


    public HeadBuilder setOwner(String name) {
        iMeta.setOwner(name);
        return this;
    }

    public HeadBuilder setLoreString(String... lore) {
        setLore((ArrayList) Arrays.<String>asList(lore));
        return this;
    }

    public HeadBuilder setLore(ArrayList<String> lore) {
        iMeta.setLore(lore);
        return this;
    }

    public ItemStack build() {
        i.setItemMeta((ItemMeta) iMeta);
        return i;
    }
}