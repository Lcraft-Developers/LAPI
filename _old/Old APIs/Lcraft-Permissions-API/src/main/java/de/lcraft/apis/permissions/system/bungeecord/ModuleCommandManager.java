package de.lcraft.apis.permissions.system.bungeecord;

import de.lcraft.apis.permissions.utils.bungeecord.ModuleCommand;
import java.util.ArrayList;

public class ModuleCommandManager {

    private ArrayList<ModuleCommand> commands;
    private de.lcraft.apis.languages.system.bungeecord.ModuleCommandManager commandManager;

    public ModuleCommandManager(de.lcraft.apis.languages.system.bungeecord.ModuleCommandManager commandManager) {
        this.commandManager = commandManager;
        commands = new ArrayList<>();
    }

    public void addCommand(ModuleCommand executor) {
        commands.add(executor);
        commandManager.addCommand(executor);
    }

    public ArrayList<String> getAllPermissions() {
        ArrayList<String> allPermissions = new ArrayList<>();
        for(ModuleCommand cmd : commands) {
            allPermissions = cmd.allPermissions(allPermissions);
        }
        return allPermissions;
    }

    public ArrayList<ModuleCommand> getCommands() {
        return commands;
    }
    public de.lcraft.apis.languages.system.bungeecord.ModuleCommandManager getCommandManager() {
        return commandManager;
    }

}
