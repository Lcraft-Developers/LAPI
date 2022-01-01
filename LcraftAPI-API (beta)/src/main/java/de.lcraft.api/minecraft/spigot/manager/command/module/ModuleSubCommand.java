package de.lcraft.api.minecraft.spigot.manager.command.module;

import de.lcraft.api.minecraft.spigot.manager.Module;

public abstract class ModuleSubCommand extends ModuleCommand {

	public ModuleSubCommand(String command, String desc, Module m, boolean splitting) {
		super(command, desc, m, splitting);
	}

}
