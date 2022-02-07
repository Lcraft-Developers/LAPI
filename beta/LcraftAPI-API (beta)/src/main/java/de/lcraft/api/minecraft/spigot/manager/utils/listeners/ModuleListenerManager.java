package de.lcraft.api.minecraft.spigot.manager.utils.listeners;

import de.lcraft.api.minecraft.spigot.manager.Module;
import lombok.Getter;

public class ModuleListenerManager extends ListenerManager {

	@Getter
	private Module module;
	public ModuleListenerManager(Module module) {
		super(module.getPlugin());
		this.module = module;
	}

}