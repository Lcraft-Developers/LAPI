package de.lcraft.api.utils.minecraft.spigot.module.utils.inventory;

import de.lcraft.api.utils.minecraft.spigot.SpigotClass;

import java.util.HashMap;

public class InventoryManager {
	
	public static HashMap<String, Inventory> invs;
	
	public InventoryManager(SpigotClass plugin) {
		invs = new HashMap<>();
	}

	public void addInventory(Inventory inv) {
		invs.put(inv.getId(), inv);
	}
	public static HashMap<String, Inventory> getInvs() {
		return invs;
	}
}
