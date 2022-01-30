package de.lcraft.api.minecraft.spigot.manager.command;

import de.lcraft.api.minecraft.spigot.player.LPlayer;
import de.lcraft.api.minecraft.spigot.manager.util.LanguagesManager;
import de.lcraft.api.minecraft.spigot.manager.util.PermsManager;
import de.lcraft.api.minecraft.spigot.player.LPlayerManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public abstract class Command extends org.bukkit.command.Command {

    private boolean splitting;
    private LanguagesManager languagesManager;
    private PermsManager permsManager;
    private String description;
    private ArrayList<SubCommand> subModuleCommands;
    private String command;
    private LPlayerManager lPlayerManager;

    public Command(String command, String desc, PermsManager permsManager, LPlayerManager lPlayerManager, LanguagesManager languagesManager, boolean splitting) {
        super(command, desc, "", new ArrayList<>());
        subModuleCommands = new ArrayList<>();
        this.description = desc;
        this.splitting = splitting;
        this.command = command;

        this.permsManager = permsManager;
        this.languagesManager = languagesManager;
        this.lPlayerManager = lPlayerManager;
    }

    public final String translate(UUID uuid, String text) {
        return languagesManager.getIDLanguage(languagesManager.getIDFromUUID(uuid)).translate(text);
    }
    public final boolean hasPermissions(LPlayer p, String perm) {
        return permsManager.hasPermissions(p, perm);
    }

    public final void addSubCommand(SubCommand subModuleCommand) {
        subModuleCommands.add(subModuleCommand);
    }
    public final SubCommand getSubCommand(String name) {
        for(SubCommand m : subModuleCommands) {
            if(m.getName().equalsIgnoreCase(name)) {
                return m;
            }
            continue;
        }
        return null;
    }
    public final boolean existsSubCommand(String name) {
        if(Objects.nonNull(getSubCommand(name))) {
            return true;
        }
        return false;
    }

    @Override
    public boolean execute(CommandSender commandSender, String var3, String[] strings) {
        if(Objects.nonNull(strings) && strings.length > 0) {
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
            if(Objects.nonNull(commandSender) && commandSender instanceof LPlayer) {
                onLPlayerCommand(getLPlayerManager().getLPlayerByUUID(((Player) commandSender).getUniqueId()), strings);
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

    public final String getDescription() {
        return description;
    }
	public final String getName() {
        return command;
    }
    public final LPlayerManager getLPlayerManager() {
        return lPlayerManager;
    }

}