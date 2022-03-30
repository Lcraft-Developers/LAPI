package de.lcraft.api.minecraft.spigot.module.manager.utils.command;

import de.lcraft.api.minecraft.spigot.module.manager.Module;
import de.lcraft.api.minecraft.spigot.module.manager.configs.ModuleConfig;
import de.lcraft.api.minecraft.spigot.module.manager.utils.language.StandardMessages;
import de.lcraft.api.minecraft.spigot.utils.command.CommandManager;

public class ModuleCommandManager extends CommandManager {

	private final Module module;
	private final ModuleConfig moduleBukkitConfig;

	public ModuleCommandManager(StandardMessages standardMessages, Module module) {
		super(standardMessages);
		this.module = module;
		setModuleCommands(new ModuleConfig(getModule(), "commands.yml"));
		this.moduleBukkitConfig = new ModuleConfig(getModule(), "commands.yml");
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
	public ModuleConfig getModuleBukkitConfig() {
		return moduleBukkitConfig;
	}

}
