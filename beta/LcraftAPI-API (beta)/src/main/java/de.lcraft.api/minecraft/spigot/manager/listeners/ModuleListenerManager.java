package de.lcraft.api.minecraft.spigot.manager.listeners;

import de.lcraft.api.minecraft.spigot.manager.Module;

public class ModuleListenerManager extends ListenerManager {

	private Module module;

	public ModuleListenerManager(Module module) {
		super(module.getPlugin());
		this.module = module;
	}
	public final Module getModule() {
		return module;
	}

}