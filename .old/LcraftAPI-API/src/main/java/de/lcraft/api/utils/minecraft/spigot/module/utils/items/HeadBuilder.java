package de.lcraft.api.utils.minecraft.spigot.module.utils.items;

import org.bukkit.Material;
import org.bukkit.inventory.meta.SkullMeta;


public class HeadBuilder extends ItemBuilder {

    public HeadBuilder(String name, int amount) {
        super(Material.PLAYER_HEAD, amount);
        ((SkullMeta)iMeta).setOwner(name);
    }
    public HeadBuilder(String name) {
        this(name, 1);
    }

    public HeadBuilder setOwner(String name) {
        ((SkullMeta)iMeta).setOwner(name);
        return this;
    }

}