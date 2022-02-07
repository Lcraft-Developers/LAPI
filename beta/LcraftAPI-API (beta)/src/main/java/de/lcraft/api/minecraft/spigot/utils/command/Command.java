package de.lcraft.api.minecraft.spigot.utils.command;

import de.lcraft.api.minecraft.spigot.manager.utils.language.LanguagesManager;
import de.lcraft.api.minecraft.spigot.manager.utils.PermsManager;
import de.lcraft.api.minecraft.spigot.manager.utils.entities.LPlayer;
import de.lcraft.api.minecraft.spigot.manager.utils.LPlayerManager;
import lombok.Getter;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public abstract class Command extends org.bukkit.command.Command {

    @Getter
    private boolean splitting;
    @Getter
    private LanguagesManager languagesManager;
    @Getter
    private PermsManager permsManager;
    @Getter
    private String description;
    @Getter
    private ArrayList<SubCommand> subModuleCommands;
    @Getter
    private String command;
    @Getter
    private LPlayerManager lPlayerManager;

    public Command(String label, String desc, PermsManager permsManager, LPlayerManager lPlayerManager, LanguagesManager languagesManager, boolean splitting) {
        super(label, desc, "", new ArrayList<>());
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
    public final SubCommand getSubCommandByName(String name) {
        for(SubCommand m : subModuleCommands) {
            if(m.getName().equalsIgnoreCase(name)) {
                return m;
            }
            continue;
        }
        return null;
    }
    public final boolean existsSubCommand(String name) {
        if(Objects.nonNull(getSubCommandByName(name))) {
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
                getSubCommandByName(strings[0]).execute(commandSender, var3, new_args);
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

}