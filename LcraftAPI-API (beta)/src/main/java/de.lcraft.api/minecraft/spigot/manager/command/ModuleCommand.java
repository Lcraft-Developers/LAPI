package de.lcraft.api.minecraft.spigot.manager.command;

import de.lcraft.api.minecraft.spigot.SpigotClass;
import de.lcraft.api.minecraft.spigot.manager.Module;
import de.lcraft.api.minecraft.spigot.manager.logger.ModuleLogger;
import de.lcraft.api.minecraft.spigot.manager.logger.ModuleLoggerType;
import _old.LPlayer;
import de.lcraft.api.minecraft.spigot.manager.util.LanguagesManager;
import de.lcraft.api.minecraft.spigot.manager.util.PermsManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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
    private String command;
    private SpigotClass spigotClass;

    public ModuleCommand(String command, String desc, Module m, SpigotClass spigotClass, boolean splitting) {
        super(command, desc, "", new ArrayList<>());
        subModuleCommands = new ArrayList<>();
        this.module = m;
        this.description = desc;
        this.splitting = splitting;
        this.command = command;
        this.spigotClass = spigotClass;

        permsManager = m.getPermsManager();
        languagesManager = m.getLanguagesManager();
    }

    public String translate(UUID uuid, String text) throws IOException {
        return languagesManager.getIDLanguage(languagesManager.getIDFromUUID(uuid)).translate(text);
    }
    public boolean hasPermissions(LPlayer p, String perm) {
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
    public boolean execute(CommandSender commandSender, String var3, String[] strings) {
        if(strings != null && strings.length > 0) {
            if(existsSubCommand(strings[0])) {
                String[] new_args = new String[strings.length - 1];
                for(int i = 1; i < strings.length; i++) {
                    new_args[i - 1] = strings[i];
                }
                getSubCommand(strings[0]).execute(commandSender, var3, new_args);
            } else {
                split(commandSender, strings);
            }
        } else {
            split(commandSender, strings);
        }
        return false;
    }
    public void split(CommandSender commandSender, String[] strings) {
        if(splitting) {
            if(commandSender != null && commandSender instanceof LPlayer) {
                onLPlayerCommand(spigotClass.getLPlayerByUUID(((Player) commandSender).getUniqueId()), strings);
            } else {
                onConsoleCommand(commandSender, strings);
            }
        }
        onCommandExecute(commandSender, strings);
    }

    public boolean onCommandExecute(CommandSender s, String[] args) {return false;}
    public boolean onLPlayerCommand(LPlayer p, String[] args) {return false;}
    public boolean onConsoleCommand(CommandSender s, String[] args) {return false;}

    public abstract ArrayList<String> getAllPermissions(ArrayList<String> allPermissions);
    public abstract ArrayList<String> getAllTranslations(ArrayList<String> allTranslations);

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
	public String getName() {
        return command;
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