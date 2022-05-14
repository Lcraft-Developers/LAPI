package de.lcraft.api.minecraft.spigot.module.utils.items;

import de.lcraft.api.minecraft.spigot.utils.listeners.ListenerManager;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class HeadBuilder extends ItemBuilder {

    private String owner;

    public HeadBuilder(ListenerManager manager, String name, int amount) {
        super(manager, Material.PLAYER_HEAD, amount);
        owner = name;
    }
    public HeadBuilder(ListenerManager manager, String name) {
        this(manager, name, 1);
    }

    @Override
    public ItemStack build() {
        ItemStack i = new ItemStack(getMaterial(), getAmount());
        SkullMeta itemMeta = (SkullMeta) i.getItemMeta();
        itemMeta.setDisplayName(getDisplayName());
        itemMeta.setLore(getLore());
        itemMeta.setOwner(getOwner());
        i.setItemMeta(itemMeta);
        return i;
    }

    public final HeadBuilder setOwner(String name) {
        owner = name;
        return this;
    }
    public final String getOwner() {
        return owner;
    }

}