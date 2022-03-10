package de.lcraft.api.minecraft.spigot.module.manager.utils.command;

import de.lcraft.api.minecraft.spigot.module.manager.Module;
import de.lcraft.api.minecraft.spigot.module.manager.logger.ModuleLogger;
import de.lcraft.api.minecraft.spigot.module.manager.logger.ModuleLoggerType;
import de.lcraft.api.minecraft.spigot.module.manager.utils.language.StandardMessages;
import de.lcraft.api.minecraft.spigot.module.manager.command.Command;

import java.util.ArrayList;
import java.util.Objects;

public abstract class ModuleCommand extends Command {

	private Module module;
	private ArrayList<ModuleSubCommand> subModuleCommands;

	public ModuleCommand(StandardMessages standardMessages, String label, String desc, Module m, boolean splitting) {
		super(standardMessages, label, desc, m.getPermsManager(), m.getLanguagesManager(), m.getModuleManager().getPluginMain().getLPlayerManager(), splitting);
		this.module = m;
		subModuleCommands = new ArrayList<>();
	}
	public ModuleCommand(String label, String desc, Module m, boolean splitting) {
		super(new StandardMessages(m), label, desc, m.getPermsManager(), m.getLanguagesManager(), m.getModuleManager().getPluginMain().getLPlayerManager(), splitting);
		this.module = m;
	}

	public final void addModuleSubCommand(ModuleSubCommand subModuleCommand) {
		subModuleCommands.add(subModuleCommand);
	}
	public final ModuleSubCommand getModuleSubCommand(String name) {
		for(ModuleSubCommand m : subModuleCommands) {
			if(m.getName().equalsIgnoreCase(name)) {
				return m;
			}
			continue;
		}
		return null;
	}
	public final boolean existsModuleSubCommand(String name) {
		return Objects.nonNull(getSubCommand(name));
	}

	public final void setModule(Module module) {
		this.module = module;
	}
	public final Module getModule() {
		return module;
	}
	public final ModuleLogger getLogger() {
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
