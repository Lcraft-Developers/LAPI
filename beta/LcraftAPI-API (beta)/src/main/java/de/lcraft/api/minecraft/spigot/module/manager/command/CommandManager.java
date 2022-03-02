package de.lcraft.api.minecraft.spigot.module.manager.command;

import de.lcraft.api.java_utils.configuration.Config;
import de.lcraft.api.java_utils.language.LanguagesManager;
import de.lcraft.api.minecraft.spigot.module.manager.utils.language.StandardMessages;
import de.lcraft.api.minecraft.spigot.module.manager.utils.permissions.PermsManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Objects;

public class CommandManager {

    private ArrayList<Command> modulesCmds;
    private Config moduleCommands;
    private StandardMessages standardMessages;

    public CommandManager(StandardMessages standardMessages) {
        this.modulesCmds = new ArrayList<>();
        this.moduleCommands = new Config("commands.yml");
        this.standardMessages = standardMessages;
    }

    public final void addCommand(Command executor, boolean canDisableInConfig) {
        if(canDisableInConfig) {
            if(moduleCommands.exists("commands." + executor.getName())) {
                if(moduleCommands.getBoolean("commands." + executor.getName())) {
                    addCommand(executor, false);
                }
            } else {
                moduleCommands.set("commands." + executor.getName(), true);
                addCommand(executor, true);
            }
        } else {
            try {
                final Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
                commandMapField.setAccessible(true);

                final CommandMap commandMap = ((CommandMap) commandMapField.get(Bukkit.getServer()));
                commandMap.register(executor.getName(), executor);
            } catch (final IllegalAccessException | NoSuchFieldException ex) {
                ex.printStackTrace();
            }
            modulesCmds.add(executor);
        }
    }
    public final void reloadConfigs(PermsManager permsManager, LanguagesManager languagesManager) {
        ArrayList<String> extras = new ArrayList<>();
        extras.add("admin");
        extras.add("*");
        if(Objects.nonNull(getAllPermissions()) && !getAllPermissions().isEmpty()) {
            for(String c : getAllPermissions()) {
                permsManager.logPermissionWithExtra(c, extras);
            }
        }

        if(Objects.nonNull(getAllTranslatedTexts()) && !getAllTranslatedTexts().isEmpty()) {
            for(String c : getAllTranslatedTexts()) {
                languagesManager.logText(c);
            }
        }
    }

    public final ArrayList<String> getAllTranslatedTexts() {
        ArrayList<String> allTranslations = new ArrayList<>();
        for(Command cmd : getModulesCmds()) {
            allTranslations.addAll(cmd.allUsedTranslatedText());
        }
        return allTranslations;
    }
    public final ArrayList<String> getAllPermissions() {
        ArrayList<String> allPermissions = new ArrayList<>();
        for(Command cmd : getModulesCmds()) {
            allPermissions.addAll(cmd.allUsedPermissions());
        }
        return allPermissions;
    }
    public final ArrayList<Command> getModulesCmds() {
        return modulesCmds;
    }
    public StandardMessages getStandardMessages() {
        return standardMessages;
    }

    public void setModuleCommands(Config moduleCommands) {
        this.moduleCommands = moduleCommands;
    }

}
