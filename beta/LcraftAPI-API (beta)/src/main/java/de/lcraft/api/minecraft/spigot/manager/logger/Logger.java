package de.lcraft.api.minecraft.spigot.manager.logger;

import org.bukkit.Bukkit;

public abstract class Logger {

	public final void send(ModuleLoggerType type, String msg) {
		if(type == ModuleLoggerType.ERROR) {
			Bukkit.getConsoleSender().sendMessage("ERROR >> " + msg);
		} else if(type == ModuleLoggerType.WARNING) {
			Bukkit.getConsoleSender().sendMessage("WARNING >> " + msg);
		} else if(type == ModuleLoggerType.INFO) {
			Bukkit.getConsoleSender().sendMessage("INFO >> " + msg);
		} else if(type == ModuleLoggerType.NOTHING) {
			Bukkit.getConsoleSender().sendMessage(msg);
		}
	}

}
