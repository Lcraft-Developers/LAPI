package de.lcraft.api.minecraft.spigot.utils.command;

import de.lcraft.api.minecraft.spigot.manager.utils.language.Language;
import de.lcraft.api.minecraft.spigot.manager.utils.language.LanguagesManager;
import de.lcraft.api.minecraft.spigot.manager.utils.listeners.ListenerManager;
import de.lcraft.api.minecraft.spigot.manager.utils.permissions.PermsManager;
import de.lcraft.api.minecraft.spigot.manager.utils.entities.LPlayer;
import de.lcraft.api.minecraft.spigot.manager.utils.LPlayerManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public abstract class Command extends org.bukkit.command.Command implements Listener {

    private boolean splitting;
    private LanguagesManager languagesManager;
    private PermsManager permsManager;
    private String description;
    private ArrayList<SubCommand> subModuleCommands;
    private String command;
    private LPlayerManager lPlayerManager;
    private ListenerManager listenerManager;

    public Command(String label, String desc, PermsManager permsManager, LPlayerManager lPlayerManager, LanguagesManager languagesManager, boolean splitting, ListenerManager listenerManager) {
        super(label, desc, "", new ArrayList<>());
        subModuleCommands = new ArrayList<>();
        this.description = desc;
        this.splitting = splitting;
        this.command = label;
        this.listenerManager = listenerManager;

        this.permsManager = permsManager;
        this.languagesManager = languagesManager;
        this.lPlayerManager = lPlayerManager;
    }

    public final String translate(UUID uuid, String text) {
        return getLanguagesManager().getIDLanguage(getLanguagesManager().getIDFromUUID(uuid)).translate(text);
    }
    public final boolean hasPermissions(LPlayer p, String perm) {
        return getPermsManager().hasPermissions(p, perm);
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
            if(Objects.nonNull(commandSender) && commandSender instanceof Player) {
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
    public LanguagesManager getLanguagesManager() {
        return languagesManager;
    }
    public PermsManager getPermsManager() {
        return permsManager;
    }
    public ListenerManager getListenerManager() {
        return listenerManager;
    }

}