package de.lcraft.api.plugin.modules.commands;

import de.lcraft.api.plugin.logger.Logger;
import de.lcraft.api.plugin.logger.LoggerPlace;
import de.lcraft.api.plugin.logger.LoggerType;
import de.lcraft.api.plugin.modules.Module;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class ModuleCommand {

    private String cmd_displayName,
                   cmd_id,
                   cmd;
    private Module module;

    public ModuleCommand(Module module, String command, String id, String displayName) {
        this.cmd_displayName = displayName;
        this.cmd_id = id;
        this.cmd = command;
        this.module = module;
    }

    public final Logger getLogger() {
        return module.getLogger();
    }

    public abstract boolean onCommand(CommandSender s, String[] args);
    public boolean onPlayerCommand(Player p, String[] args) {return false;}
    public boolean onConsoleCommand(CommandSender s, String[] args) {return false;}

    public final void sendInfoMessage(String message, LoggerPlace loggerPlace) {
        getLogger().sendModule(LoggerType.INFO, loggerPlace, message);
    }

    public final void sendWarningMessage(String message, LoggerPlace loggerPlace) {
        getLogger().sendModule(LoggerType.WARNING, loggerPlace, message);
    }

    public final void sendErrorMessage(String message, LoggerPlace loggerPlace) {
        getLogger().sendModule(LoggerType.ERROR, loggerPlace, message);
    }

    public final void sendMessage(String message, LoggerPlace loggerPlace) {
        getLogger().sendModule(LoggerType.NOTHING, loggerPlace, message);
    }

}