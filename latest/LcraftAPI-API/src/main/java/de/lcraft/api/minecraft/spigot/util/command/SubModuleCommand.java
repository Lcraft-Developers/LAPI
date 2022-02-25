package de.lcraft.api.minecraft.spigot.util.command;

import de.lcraft.api.minecraft.spigot.module.manager.Module;

import java.io.IOException;

public abstract class SubModuleCommand extends ModuleCommand {

	public SubModuleCommand(String subCommand, String desc, Module m, boolean splitting) throws IOException {
		super(subCommand, desc, m, splitting);
	}

}
