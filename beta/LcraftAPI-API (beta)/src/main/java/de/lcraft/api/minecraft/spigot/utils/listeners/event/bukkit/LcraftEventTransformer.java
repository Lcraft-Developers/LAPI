package de.lcraft.api.minecraft.spigot.utils.listeners.event.bukkit;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class LcraftEventTransformer implements Listener {

	@EventHandler(priority = EventPriority.MONITOR)
	public void BlockBreakEvent(BlockBreakEvent event) {
		event.setCancelled(true);
		event.
	}

}
