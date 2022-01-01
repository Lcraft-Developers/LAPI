package de.lcraft.api.minecraft.spigot.manager.command.module;

import de.lcraft.api.minecraft.spigot.manager.Module;
import de.lcraft.api.minecraft.spigot.manager.command.Command;
import de.lcraft.api.minecraft.spigot.manager.logger.ModuleLogger;
import de.lcraft.api.minecraft.spigot.manager.logger.ModuleLoggerType;
import java.util.ArrayList;

public abstract class ModuleCommand extends Command {

	private Module module;
	private ArrayList<ModuleSubCommand> subModuleCommands;

	public ModuleCommand(String command, String desc, Module m, boolean splitting) {
		super(command, desc, m.getPermsManager(), m.getModuleManager().getPluginMain().getLPlayerManager(), m.getLanguagesManager(), splitting);
		this.module = m;
		subModuleCommands = new ArrayList<>();
	}

	public void addModuleSubCommand(ModuleSubCommand subModuleCommand) {
		subModuleCommands.add(subModuleCommand);
	}
	public ModuleSubCommand getModuleSubCommand(String name) {
		for(ModuleSubCommand m : subModuleCommands) {
			if(m.getName().equalsIgnoreCase(name)) {
				return m;
			}
		}
		return null;
	}
	public boolean existsModuleSubCommand(String name) {
		if(getSubCommand(name) != null) {
			return true;
		}
		return false;
	}

	public void setModule(Module module) {
		this.module = module;
	}
	public Module getModule() {
		return module;
	}
	public ModuleLogger getLogger() {
		return module.getLogger();
	}

	public void sendInfoMessage(String message) {
		getLogger().sendModule(ModuleLoggerType.INFO, message);
	}
	public void sendWarningMessage(String message) {
		getLogger().sendModule(ModuleLoggerType.WARNING, message);
	}
	public void sendErrorMessage(String message) {
		getLogger().sendModule(ModuleLoggerType.ERROR, message);
	}
	public void sendMessage(String message) {
		getLogger().sendModule(ModuleLoggerType.NOTHING, message);
	}

}
