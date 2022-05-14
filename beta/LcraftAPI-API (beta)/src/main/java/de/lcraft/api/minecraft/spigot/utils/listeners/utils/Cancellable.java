package de.lcraft.api.minecraft.spigot.utils.listeners.utils;

public interface Cancellable {

	boolean isCancelled();
	void setCancelled(boolean cancel);

}
