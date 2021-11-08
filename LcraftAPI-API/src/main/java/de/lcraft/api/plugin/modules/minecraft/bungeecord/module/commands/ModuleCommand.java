package de.lcraft.api.plugin.modules.minecraft.bungeecord.module.commands;

import de.lcraft.api.plugin.modules.minecraft.bungeecord.manager.logger.ModuleLogger;
import de.lcraft.api.plugin.modules.minecraft.bungeecord.manager.logger.ModuleLoggerType;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

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

    public final ModuleLogger getLogger() {
        return module.getLogger();
    }

    @Override
    public void execute(CommandSender var1, String[] var2) {
        onCommand(var1, var2);
        if(var1 != null && var1 instanceof ProxiedPlayer) {
            onPlayerCommand((ProxiedPlayer) var1, var2);
        } else {
            onConsoleCommand(var1, var2);
        }
    }

    public abstract boolean onCommand(CommandSender s, String[] args);
    public boolean onPlayerCommand(ProxiedPlayer p, String[] args) {return false;}
    public boolean onConsoleCommand(CommandSender s, String[] args) {return false;}

    @Override
    public String getName() {
        return super.getName();
    }

    public final void sendInfoMessage(String message) {
        getLogger().sendModule(ModuleLoggerType.INFO, message);
    }

    public final void sendWarningMessage(String message) {
        getLogger().sendModule(ModuleLoggerType.WARNING, message);
    }

    public final void sendErrorMessage(String message) {
        getLogger().sendModule(ModuleLoggerType.ERROR, message);
    }

    public final void sendMessage(String message) {
        getLogger().sendModule(ModuleLoggerType.NOTHING, message);
    }

}