package de.lcraft.api.minecraft.spigot.manager.command.module;

import de.lcraft.api.minecraft.spigot.manager.Module;

public abstract class ModuleSubCommand extends ModuleCommand {

	public ModuleSubCommand(String subLabel, String desc, Module m, boolean splitting) {
		super(subLabel, desc, m, splitting);
	}

}
