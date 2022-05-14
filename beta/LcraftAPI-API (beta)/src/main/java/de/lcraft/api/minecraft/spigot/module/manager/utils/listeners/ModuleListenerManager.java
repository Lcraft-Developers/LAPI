package de.lcraft.api.minecraft.spigot.module.manager.utils.listeners;

import de.lcraft.api.minecraft.spigot.module.manager.Module;
import de.lcraft.api.minecraft.spigot.utils.listeners.ListenerManager;

public class ModuleListenerManager extends ListenerManager {

	private final Module module;

	public ModuleListenerManager(Module module) {
		super(module.getPlugin());
		this.module = module;
	}
	public final Module getModule() {
		return module;
	}

}