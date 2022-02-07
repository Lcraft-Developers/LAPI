package de.lcraft.api.minecraft.spigot.utils.items;

import de.lcraft.api.minecraft.spigot.manager.utils.listeners.ListenerManager;
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
        i.getItemMeta().setDisplayName(getDisplayName());
        i.getItemMeta().setLore(getLore());
        ((SkullMeta) i.getItemMeta()).setOwner(getOwner());
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