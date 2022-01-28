package de.lcraft.api.minecraft.spigot.util.items;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public class SpawnerBuilder extends ItemBuilder {

	public SpawnerBuilder(EntityType type, int amount) {
		super(Material.SPAWNER, amount);
		String loreString = type.toString();
		loreString = loreString.substring(0, 1).toUpperCase() + loreString.substring(1).toLowerCase();
		loreString = loreString + " Spawner";

		addLoreString(loreString);
	}
	public SpawnerBuilder(EntityType type) {
		this(type, 1);
	}

	@Override
	public ItemStack build() {
		ItemStack i = new ItemStack(getMaterial(), getAmount());
		i.getItemMeta().setDisplayName(getDisplayName());
		i.getItemMeta().setLore(getLore());
		return i;
	}

}
