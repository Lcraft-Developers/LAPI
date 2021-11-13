package de.lcraft.api.utils.minecraft.spigot.module.utils.command;

import de.lcraft.api.utils.minecraft.spigot.languages.filesystem.LanguagesManager;
import de.lcraft.api.utils.minecraft.spigot.module.logger.ModuleLogger;
import de.lcraft.api.utils.minecraft.spigot.module.logger.ModuleLoggerPlace;
import de.lcraft.api.utils.minecraft.spigot.module.logger.ModuleLoggerType;
import de.lcraft.api.utils.minecraft.spigot.module.Module;
import de.lcraft.api.utils.minecraft.spigot.permissions.PermsManager;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public abstract class ModuleCommand extends BukkitCommand {

    private Module module;
    private boolean splitting;
    private LanguagesManager languagesManager;
    private PermsManager permsManager;

    protected ModuleCommand(final String command, final String desc, final String usage, Module m, boolean splitting) throws IOException {
        super(command);
        this.description = desc;
        this.usageMessage = usage;
        this.module = m;
        this.splitting = splitting;
        permsManager = new PermsManager();
        languagesManager = new LanguagesManager();
    }

    public String translate(UUID uuid, String text) throws IOException {
        return languagesManager.getPlayer(uuid).translate(text);
    }
    public boolean hasPermissions(Player p, String perm) {
        return permsManager.hasPermissions(p, perm);
    }

    @Override
    public boolean execute(CommandSender var1, String var2, String[] var3) {
        if(splitting) {
            if(var1 != null && var1 instanceof Player) {
                return onPlayerCommand((Player) var1, var3);
            } else {
                return onConsoleCommand(var1, var3);
            }
        } else {
            return onCommand(var1, var3);
        }
    }
    public boolean onCommand(CommandSender s, String[] args) {return false;}
    public boolean onPlayerCommand(Player p, String[] args) {return false;}
    public boolean onConsoleCommand(CommandSender s, String[] args) {return false;}

    public abstract ArrayList<String> getAllPermissions(ArrayList<String> allPermissions);
    public abstract ArrayList<String> getAllTranslations(ArrayList<String> allTranslations);

    @Override
    public String getName() {
        return super.getName();
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

    public final void sendInfoMessage(String message, ModuleLoggerPlace moduleLoggerPlace) {
        getLogger().sendModule(ModuleLoggerType.INFO, moduleLoggerPlace, message);
    }
    public final void sendWarningMessage(String message, ModuleLoggerPlace moduleLoggerPlace) {
        getLogger().sendModule(ModuleLoggerType.WARNING, moduleLoggerPlace, message);
    }
    public final void sendErrorMessage(String message, ModuleLoggerPlace moduleLoggerPlace) {
        getLogger().sendModule(ModuleLoggerType.ERROR, moduleLoggerPlace, message);
    }
    public final void sendMessage(String message, ModuleLoggerPlace moduleLoggerPlace) {
        getLogger().sendModule(ModuleLoggerType.NOTHING, moduleLoggerPlace, message);
    }

}