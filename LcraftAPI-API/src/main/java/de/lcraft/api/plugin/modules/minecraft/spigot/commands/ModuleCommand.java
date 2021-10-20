package de.lcraft.api.plugin.modules.minecraft.spigot.commands;

import de.lcraft.api.plugin.modules.minecraft.spigot.logger.Logger;
import de.lcraft.api.plugin.modules.minecraft.spigot.logger.LoggerPlace;
import de.lcraft.api.plugin.modules.minecraft.spigot.logger.LoggerType;
import de.lcraft.api.plugin.modules.minecraft.spigot.Module;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

public abstract class ModuleCommand extends BukkitCommand {

    private Module module;

    protected ModuleCommand(final String command, final String desc, final String usage, Module m) {
        super(command);
        this.description = desc;
        this.usageMessage = usage;
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
    public boolean execute(CommandSender var1, String var2, String[] var3) {
        onCommand(var1, var3);
        if(var1 != null && var1 instanceof Player) {
            return onPlayerCommand((Player) var1, var3);
        } else {
            return onConsoleCommand(var1, var3);
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