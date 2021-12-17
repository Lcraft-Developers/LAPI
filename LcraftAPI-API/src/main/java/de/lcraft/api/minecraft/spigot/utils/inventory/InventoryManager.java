package de.lcraft.api.minecraft.spigot.utils.inventory;

import de.lcraft.api.minecraft.spigot.SpigotClass;
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
