package de.lcraft.api.minecraft.spigot.util.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class HeadBuilder extends ItemBuilder {

    private String owner;

    public HeadBuilder(String name, int amount) {
        super(Material.PLAYER_HEAD, amount);
        owner = name;
    }
    public HeadBuilder(String name) {
        this(name, 1);
    }

    @Override
    public ItemStack build() {
        ItemStack i = new ItemStack(getMaterial(), getAmount());
        i.getItemMeta().setDisplayName(getDisplayName());
        i.getItemMeta().setLore(getLore());
        ((SkullMeta) i.getItemMeta()).setOwner(getOwner());
        return i;
    }

    public HeadBuilder setOwner(String name) {
        owner = name;
        return this;
    }
    public String getOwner() {
        return owner;
    }

}