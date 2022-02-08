package de.lcraft.api.minecraft.spigot.manager.utils.command;

import de.lcraft.api.minecraft.spigot.manager.Module;
import de.lcraft.api.minecraft.spigot.manager.configs.ModuleBukkitConfig;
import de.lcraft.api.minecraft.spigot.utils.command.CommandManager;

public class ModuleCommandManager extends CommandManager {

	private Module module;
	private ModuleBukkitConfig moduleBukkitConfig;

	public ModuleCommandManager(Module module) {
		super();
		this.module = module;
		setModuleCommands(new ModuleBukkitConfig(getModule(), "commands.yml"));
		this.moduleBukkitConfig = new ModuleBukkitConfig(getModule(), "commands.yml");
	}
	public final void reloadConfigs() {
		reloadConfigs(module.getPermsManager(),module.getLanguagesManager());
	}
	public Module getModule() {
		return module;
	}
	public ModuleBukkitConfig getModuleBukkitConfig() {
		return moduleBukkitConfig;
	}

}
