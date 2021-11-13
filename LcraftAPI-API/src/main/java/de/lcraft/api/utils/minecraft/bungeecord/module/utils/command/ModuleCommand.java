package de.lcraft.api.utils.minecraft.bungeecord.module.utils.command;

import de.lcraft.api.utils.minecraft.bungeecord.languages.filesystem.LanguagesManager;
import de.lcraft.api.utils.minecraft.bungeecord.module.Module;
import de.lcraft.api.utils.minecraft.bungeecord.module.logger.ModuleLogger;
import de.lcraft.api.utils.minecraft.bungeecord.module.logger.ModuleLoggerType;
import de.lcraft.api.utils.minecraft.bungeecord.permissions.PermsManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public abstract class ModuleCommand extends Command {

    private Module module;
    private boolean splitting;
    private LanguagesManager languagesManager;
    private PermsManager permsManager;
    private String description;

    protected ModuleCommand(final String command, final String desc, Module m, boolean splitting) throws IOException {
        super(command);
        this.description = desc;
        this.module = m;
        this.splitting = splitting;
        permsManager = new PermsManager();
        languagesManager = new LanguagesManager();
        this.description = desc;
    }

    public String translate(UUID uuid, String text) throws IOException {
        return languagesManager.getPlayer(uuid).translate(text);
    }
    public boolean hasPermissions(ProxiedPlayer p, String perm) {
        return permsManager.hasPermissions(p, perm);
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if(splitting) {
            if(commandSender != null && commandSender instanceof ProxiedPlayer) {
                onPlayerCommand((ProxiedPlayer) commandSender, strings);
            } else {
                onConsoleCommand(commandSender, strings);
            }
        } else {
            onCommand(commandSender, strings);
        }
    }

    public boolean onCommand(CommandSender s, String[] args) {return false;}
    public boolean onPlayerCommand(ProxiedPlayer p, String[] args) {return false;}
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
    public String getDescription() {
        return description;
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