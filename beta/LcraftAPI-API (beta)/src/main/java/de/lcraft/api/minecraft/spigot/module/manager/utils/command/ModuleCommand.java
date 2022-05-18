package de.lcraft.api.minecraft.spigot.module.manager.utils.command;

import de.lcraft.api.minecraft.spigot.module.manager.Module;
import de.lcraft.api.minecraft.spigot.module.manager.logger.ModuleLogger;
import de.lcraft.api.minecraft.spigot.module.manager.logger.ModuleLoggerType;
import de.lcraft.api.minecraft.spigot.module.manager.utils.language.StandardMessages;
import de.lcraft.api.minecraft.spigot.utils.command.Command;

import java.util.ArrayList;
import java.util.Objects;

public abstract class ModuleCommand extends Command {

	private Module module;

	public ModuleCommand(StandardMessages standardMessages, ModuleCommand parent, String label, String desc, Module m, boolean splitting) {
		super(standardMessages, parent, label, desc, m.getPermsManager(), m.getLanguagesManager(), m.getModuleManager().getPluginMain().getLPlayerManager(), splitting);
		this.module = m;
	}
	public ModuleCommand( ModuleCommand parent, String label, String desc, Module m, boolean splitting) {
		super(new StandardMessages(m), parent, label, desc, m.getPermsManager(), m.getLanguagesManager(), m.getModuleManager().getPluginMain().getLPlayerManager(), splitting);
		this.module = m;
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
