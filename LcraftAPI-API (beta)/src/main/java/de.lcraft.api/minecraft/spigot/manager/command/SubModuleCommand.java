package de.lcraft.api.minecraft.spigot.manager.command;

import de.lcraft.api.minecraft.spigot.SpigotClass;
import de.lcraft.api.minecraft.spigot.manager.Module;

import java.io.IOException;

public abstract class SubModuleCommand extends ModuleCommand {

	public SubModuleCommand(String subCommand, String desc, Module m, SpigotClass spigotClass, boolean splitting) throws IOException {
		super(subCommand, desc, m, spigotClass, splitting);
	}

}
