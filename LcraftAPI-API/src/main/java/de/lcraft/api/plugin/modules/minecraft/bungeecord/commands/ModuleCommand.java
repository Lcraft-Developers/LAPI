package de.lcraft.api.plugin.modules.minecraft.bungeecord.commands;

import de.lcraft.api.plugin.logger.Logger;
import de.lcraft.api.plugin.logger.LoggerPlace;
import de.lcraft.api.plugin.logger.LoggerType;
import de.lcraft.api.plugin.modules.minecraft.bungeecord.Module;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import org.bukkit.entity.Player;

public abstract class ModuleCommand extends Command {

    private Module module;

    protected ModuleCommand(final String command, Module m) {
        super(command);
        this.module = m;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Module getModule() {
        return module;
    }

    public final Logger getLogger() {
        return module.getLogger();
    }

    @Override
    public void execute(CommandSender var1, String[] var2) {
        onCommand(var1, var2);
        if(var1 != null && var1 instanceof Player) {
            onPlayerCommand((Player) var1, var2);
        } else {
            onConsoleCommand(var1, var2);
        }
    }

    public abstract boolean onCommand(CommandSender s, String[] args);
    public boolean onPlayerCommand(Player p, String[] args) {return false;}
    public boolean onConsoleCommand(CommandSender s, String[] args) {return false;}

    @Override
    public String getName() {
        return super.getName();
    }

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