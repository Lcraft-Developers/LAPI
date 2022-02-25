package de.lcraft.api.minecraft.spigot.module.manager.utils.command;

import de.lcraft.api.minecraft.spigot.module.manager.Module;
import de.lcraft.api.minecraft.spigot.module.manager.configs.ModuleBukkitConfig;
import de.lcraft.api.minecraft.spigot.module.manager.utils.language.StandardMessages;
import de.lcraft.api.minecraft.spigot.module.manager.command.CommandManager;

public class ModuleCommandManager extends CommandManager {

	private Module module;
	private ModuleBukkitConfig moduleBukkitConfig;

	public ModuleCommandManager(StandardMessages standardMessages, Module module) {
		super(standardMessages);
		this.module = module;
		setModuleCommands(new ModuleBukkitConfig(getModule(), "commands.yml"));
		this.moduleBukkitConfig = new ModuleBukkitConfig(getModule(), "commands.yml");
	}
	public ModuleCommandManager(Module module) {
		this(module.getStandardMessages(), module);
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
