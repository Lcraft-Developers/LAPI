package de.lcraft.api.minecraft.spigot.utils.listeners.event;

public enum EventPriority {

	LOWEST(org.bukkit.event.EventPriority.LOWEST),
	LOW(org.bukkit.event.EventPriority.LOW),
	NORMAL(org.bukkit.event.EventPriority.NORMAL),
	HIGH(org.bukkit.event.EventPriority.HIGH),
	HIGHEST(org.bukkit.event.EventPriority.HIGHEST),
	MONITOR(org.bukkit.event.EventPriority.MONITOR);

	private final org.bukkit.event.EventPriority priority;

	private EventPriority(org.bukkit.event.EventPriority priority) {
		this.priority = priority;
	}

	public org.bukkit.event.EventPriority getPriority() {
		return priority;
	}
	public int getSlot() {
		return getPriority().getSlot();
	}

}
