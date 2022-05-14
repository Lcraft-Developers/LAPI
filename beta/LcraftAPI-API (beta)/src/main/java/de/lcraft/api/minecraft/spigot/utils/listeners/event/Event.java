package de.lcraft.api.minecraft.spigot.utils.listeners.event;

import de.lcraft.api.minecraft.spigot.utils.listeners.utils.Cancellable;

public abstract class Event implements Cancellable {
	private String name;

	public String getEventName() {
		if (name == null) {
			name = getClass().getSimpleName();
		}
		return name;
	}

}