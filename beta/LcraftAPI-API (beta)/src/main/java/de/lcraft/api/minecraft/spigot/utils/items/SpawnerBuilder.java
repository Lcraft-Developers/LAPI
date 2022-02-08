package de.lcraft.api.minecraft.spigot.utils.items;

import de.lcraft.api.minecraft.spigot.manager.utils.listeners.ListenerManager;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SpawnerBuilder extends ItemBuilder {

	public SpawnerBuilder(ListenerManager manager, EntityType type, int amount) {
		super(manager, Material.SPAWNER, amount);
		String loreString = type.toString();
		loreString = loreString.substring(0, 1).toUpperCase() + loreString.substring(1).toLowerCase();
		loreString = loreString + " Spawner";

		addLoreString(loreString);
	}
	public SpawnerBuilder(ListenerManager manager, EntityType type) {
		this(manager, type, 1);
	}

	@Override
	public ItemStack build() {
		ItemStack i = new ItemStack(getMaterial(), getAmount());
		ItemMeta itemMeta = i.getItemMeta();
		itemMeta.setDisplayName(getDisplayName());
		itemMeta.setLore(getLore());
		i.setItemMeta(itemMeta);
		return i;
	}

}
