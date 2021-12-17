package de.lcraft.api.minecraft.bungeecord.manager.command;

import de.lcraft.api.minecraft.bungeecord.manager.Module;
import de.lcraft.api.minecraft.bungeecord.manager.logger.ModuleLogger;
import de.lcraft.api.minecraft.bungeecord.manager.logger.ModuleLoggerType;
import de.lcraft.api.minecraft.bungeecord.manager.utils.language.LanguagesManager;
import de.lcraft.api.minecraft.bungeecord.manager.utils.permissions.PermsManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import org.checkerframework.checker.units.qual.A;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public abstract class ModuleCommand extends Command {

    private Module module;
    private boolean splitting;
    private LanguagesManager languagesManager;
    private PermsManager permsManager;
    private String description;
    private ArrayList<SubModuleCommand> subModuleCommands;

    public ModuleCommand(String command, String desc, Module m, boolean splitting) throws IOException {
        super(command);
        subModuleCommands = new ArrayList<>();
        this.module = m;
        this.description = desc;
        this.splitting = splitting;

        permsManager = m.getPermsManager();
        languagesManager = m.getLanguagesManager();
    }

    public String translate(UUID uuid, String text) throws IOException {
        return languagesManager.getIDLanguage(languagesManager.getIDFromUUID(uuid)).translate(text);
    }
    public boolean hasPermissions(ProxiedPlayer p, String perm) {
        return permsManager.hasPermissions(p, perm);
    }

    public void addSubCommand(SubModuleCommand subModuleCommand) {
        subModuleCommands.add(subModuleCommand);
    }
    public SubModuleCommand getSubCommand(String name) {
        for(SubModuleCommand m : subModuleCommands) {
            if(m.getName().equalsIgnoreCase(name)) {
                return m;
            }
        }
        return null;
    }
    public boolean existsSubCommand(String name) {
        if(getSubCommand(name) != null) {
            return true;
        }
        return false;
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if(strings != null && strings.length > 0) {
            if(existsSubCommand(strings[0])) {
                String[] new_args = new String[strings.length - 1];
                for(int i = 1; i < strings.length; i++) {
                    new_args[i - 1] = strings[i];
                }
                getSubCommand(strings[0]).execute(commandSender, new_args);
            } else {
                split(commandSender, strings);
            }
        } else {
            split(commandSender, strings);
        }
    }
    public void split(CommandSender commandSender, String[] strings) {
        if(splitting) {
            if(commandSender != null && commandSender instanceof ProxiedPlayer) {
                onPlayerCommand((ProxiedPlayer) commandSender, strings);
            } else {
                onConsoleCommand(commandSender, strings);
            }
        }
        onCommand(commandSender, strings);
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
    public ModuleLogger getLogger() {
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